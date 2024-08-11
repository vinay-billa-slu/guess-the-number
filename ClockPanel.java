import javax.swing.*;
import java.awt.*;

public class ClockPanel extends JPanel {
    private double currentTimeInSeconds;

    public ClockPanel() {
        setPreferredSize(new Dimension(300, 300));
        currentTimeInSeconds = 0;
    }

    public void setTime(int time) {
        int newTimeInSeconds = ((time % 60) + 60) % 60; // Ensures value is between 0-59
        int currentSecondInMinute = (int) (currentTimeInSeconds % 60);

        if (newTimeInSeconds >= currentSecondInMinute) {
            currentTimeInSeconds += (newTimeInSeconds - currentSecondInMinute);
        } else {
            currentTimeInSeconds += (60 - currentSecondInMinute + newTimeInSeconds);
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int centerX = width / 2;
        int centerY = height / 2;
        int radius = Math.min(width, height) / 2 - 40;

        // Draw clock face
        g2d.setColor(Color.WHITE);
        g2d.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Draw time markers
        for (int i = 0; i < 12; i++) {
            double angle = Math.PI / 6 * i;
            int x1 = (int) (centerX + (radius - 5) * Math.sin(angle));
            int y1 = (int) (centerY - (radius - 5) * Math.cos(angle));
            int x2 = (int) (centerX + radius * Math.sin(angle));
            int y2 = (int) (centerY - radius * Math.cos(angle));
            g2d.drawLine(x1, y1, x2, y2);
        }

        // Draw second hand (always clockwise)
        double angle = 2 * Math.PI * (currentTimeInSeconds % 60) / 60 - Math.PI / 2;
        int handLength = (int) (radius * 0.8);
        int x = (int) (centerX + handLength * Math.cos(angle));
        int y = (int) (centerY + handLength * Math.sin(angle));
        g2d.setColor(Color.RED);
        g2d.drawLine(centerX, centerY, x, y);

        // Draw digital time display
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String timeString = String.format("%02d", (int) (currentTimeInSeconds % 60));
        FontMetrics fm = g2d.getFontMetrics();
        int textWidth = fm.stringWidth(timeString);
        g2d.drawString(timeString, centerX - textWidth / 2, centerY + radius + 30);

        // Draw "seconds" label
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        String secondsLabel = "seconds";
        fm = g2d.getFontMetrics();
        textWidth = fm.stringWidth(secondsLabel);
        g2d.drawString(secondsLabel, centerX - textWidth / 2, centerY + radius + 50);
    }
}
