import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeApp extends JFrame {
    private JLabel timeLabel;
    private JLabel timerLabel;
    private int timerValue;

    public TimeApp() {
        setTitle("Time Application");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(2, 1));

        timeLabel = new JLabel();
        timerLabel = new JLabel("Timer: 10");

        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(timeLabel);
        add(timerLabel);

        // Timer thread to decrease timer value every second
        new Thread(() -> {
            timerValue = 10; // Initial timer value
            while (timerValue >= 0) {
                try {
                    Thread.sleep(1000); // Wait for one second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timerValue--;
                SwingUtilities.invokeLater(() -> {
                    timerLabel.setText("Timer: " + timerValue);
                });
            }
        }).start();

        // Thread to update current time every second
        new Thread(() -> {
            while (true) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss a");
                String currentTime = dateFormat.format(new Date());
                SwingUtilities.invokeLater(() -> {
                    timeLabel.setText("Current Time: " + currentTime);
                });
                try {
                    Thread.sleep(1000); // Wait for one second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimeApp app = new TimeApp();
            app.setVisible(true);
        });
    }
}
