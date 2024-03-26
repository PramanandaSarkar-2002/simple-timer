import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeApp extends JFrame {
    private JLabel myTaskLabel;
    private JLabel timerLabel;
    private JLabel timeLabel;
    private int hours;
    private int minutes;
    private int seconds;

    public TimeApp() {
        setTitle("Time Application");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 1));
        getContentPane().setBackground(Color.BLACK); // Set background color to black

        myTaskLabel = new JLabel("My Task");
        myTaskLabel.setForeground(Color.WHITE); // Set text color to white
        myTaskLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set font and size
        myTaskLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timerLabel = new JLabel();
        timerLabel.setForeground(Color.WHITE); // Set text color to white
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        timeLabel = new JLabel();
        timeLabel.setForeground(Color.WHITE); // Set text color to white
        timeLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Set font and size
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(myTaskLabel);
        add(timerLabel);
        add(timeLabel);

        // 1 hour initially

        // Timer thread to decrease timer value every second
        new Thread(() -> {
            int totalSeconds = 10; 
            while (totalSeconds >= 0) {
                hours = totalSeconds / 3600;
                minutes = (totalSeconds % 3600) / 60;
                seconds = totalSeconds % 60;

                SwingUtilities.invokeLater(() -> {
                    timerLabel.setText("Timer: " + String.format("%02d", hours) + ":" +
                            String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                });

                try {
                    Thread.sleep(1000); // Wait for one second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                totalSeconds--;

                // Play sound when timer reaches zero
                if (totalSeconds == 0) {
                    playSound("notification.wav");
                    sayTime();
                }
            }
        }).start();

        // Thread to update current time every second
        new Thread(() -> {
            while (true) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd MMMM");
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

    // Method to play sound
    private void playSound(String soundFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFile));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to say time using text-to-speech
    private void sayTime() {
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        try {
            Runtime.getRuntime().exec("say The time is " + time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TimeApp app = new TimeApp();
            app.setVisible(true);
        });
    }
}
