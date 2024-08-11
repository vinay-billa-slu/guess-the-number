import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameUIManager {
    private JLabel itemImageLabel;
    private JLabel resultImageLabel;
    private JLabel attemptsLabel;
    private JTextField guessTextField;
    private JButton submitButton;
    private ClockPanel clockPanel;
    private JPanel mainPanel;

    public GameUIManager(ActionListener submitAction) {
        initializeUI(submitAction);
    }

    private void initializeUI(ActionListener submitAction) {
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 248, 255));  // Alice Blue

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(new Color(240, 248, 255)); // Match mainPanel background
        itemImageLabel = new JLabel();
        centerPanel.add(itemImageLabel, BorderLayout.CENTER);

        resultImageLabel = new JLabel();
        centerPanel.add(resultImageLabel, BorderLayout.EAST);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setBackground(new Color(224, 255, 255));  // Light Cyan
        guessTextField = new JTextField(10);
        guessTextField.setBorder(BorderFactory.createLineBorder(new Color(32, 178, 170)));  // Light Sea Green border
        submitButton = new JButton("Submit Guess");
        submitButton.setBackground(new Color(32, 178, 170));  // Light Sea Green
        submitButton.setForeground(Color.BLACK);
        submitButton.setFocusPainted(false);
        submitButton.addActionListener(submitAction);

        inputPanel.add(new JLabel("Your Guess: $"));
        inputPanel.add(guessTextField);
        inputPanel.add(submitButton);

        attemptsLabel = new JLabel("Attempts: 0/" + GuessThePriceGame.MAX_ATTEMPTS);
        attemptsLabel.setForeground(new Color(47, 79, 79));  // Dark Slate Gray
        inputPanel.add(attemptsLabel);

        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        clockPanel = new ClockPanel();
        clockPanel.setBackground(new Color(175, 238, 238));  // Pale Turquoise
        mainPanel.add(clockPanel, BorderLayout.NORTH);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void displayItemImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        itemImageLabel.setIcon(new ImageIcon(scaledImage));
    }

    public void displayResultImage(String imagePath) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        resultImageLabel.setIcon(new ImageIcon(scaledImage));
    }

    public void updateAttemptsLabel(int attempts) {
        attemptsLabel.setText("Attempts: " + attempts + "/" + GuessThePriceGame.MAX_ATTEMPTS);
    }

    public void clearResultImage() {
        resultImageLabel.setIcon(null);
    }

    public int getGuess() throws NumberFormatException {
        return Integer.parseInt(guessTextField.getText());
    }

    public void clearGuessField() {
        guessTextField.setText("");
    }

    public void updateTime(int timeLeft) {
        clockPanel.setTime(timeLeft);
    }
}
