import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerManager {
    private Timer gameTimer;
    private GameUIManager uiManager;
    private GuessThePriceGame game;

    public TimerManager(GameUIManager uiManager, GuessThePriceGame game) {
        this.uiManager = uiManager;
        this.game = game;
    }

    public void startTimer() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
        gameTimer = new Timer(1000, new ActionListener() {
            int timeLeft = GuessThePriceGame.TIME_LIMIT;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (timeLeft > 0) {
                    timeLeft--;
                    uiManager.updateTime(timeLeft);
                } else {
                    ((Timer) e.getSource()).stop();
                    game.endGame("Time's up!");
                }
            }
        });
        gameTimer.start();
    }

    public void stopTimer() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
    }
}
