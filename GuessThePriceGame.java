import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class GuessThePriceGame extends JFrame {
    public static final int MAX_ATTEMPTS = 10;
    public static final int TIME_LIMIT = 60;
    public static final int MIN_PRICE = 1;
    public static final int MAX_PRICE = 100;

    private ItemManager itemManager;
    private GameUIManager uiManager;
    private TimerManager timerManager;

    private int currentAttempts;

    public GuessThePriceGame() {
        itemManager = new ItemManager();
        uiManager = new GameUIManager(new SubmitButtonListener());
        timerManager = new TimerManager(uiManager, this);

        setTitle("Guess the Price");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        add(uiManager.getMainPanel(), BorderLayout.CENTER);
        pack();
        setSize(900, 700);
        setLocationRelativeTo(null);

        startNewRound();
    }

    private void startNewRound() {
        itemManager.startNewRound();
        currentAttempts = 0;
        uiManager.updateAttemptsLabel(currentAttempts);
        uiManager.displayItemImage(itemManager.getCurrentItemImage());
        uiManager.clearResultImage();
        timerManager.startTimer();
    }

    private void checkGuess(int guess) {
        currentAttempts++;
        uiManager.updateAttemptsLabel(currentAttempts);

        if (guess == itemManager.getCurrentPrice()) {
            uiManager.displayResultImage("correct.jpg");
            endGame("Congratulations! You guessed the correct price!");
        } else if (guess < itemManager.getCurrentPrice()) {
            uiManager.displayResultImage("low.jpg");
        } else {
            uiManager.displayResultImage("high.jpg");
        }

        if (currentAttempts >= MAX_ATTEMPTS) {
            endGame("You've run out of attempts. The correct price was $" + itemManager.getCurrentPrice());
        }
    }

    public void endGame(String message) {
        timerManager.stopTimer();
        JOptionPane.showMessageDialog(this, message);
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            startNewRound();
        } else {
            System.exit(0);
        }
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = uiManager.getGuess();
                if (guess < MIN_PRICE || guess > MAX_PRICE) {
                    throw new NumberFormatException();
                }
                checkGuess(guess);
                uiManager.clearGuessField();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GuessThePriceGame.this, "Please enter a valid number between " + MIN_PRICE + " and " + MAX_PRICE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GuessThePriceGame().setVisible(true));
    }
}
