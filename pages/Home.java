package pages;


import java.awt.AWTException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;

import javax.swing.JFrame;
import javax.swing.JLabel;

import components.Label;

public class Home {
    JFrame frame ;

    public Home(){
        // create a new jframe, and pack it
        frame = new JFrame();
        frame.setTitle("Simple-Timer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        // make the frame half the height and width
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = (int)screenSize.height;
        int width = (int) screenSize.width/3;
        frame.setSize(width, height);

        // here's the part where i center the jframe on screen
        frame.setLocationRelativeTo(null);

        //set background Color
        frame.getContentPane().setBackground(new Color(0x222222));
    }

    private void addApplicationImage(){
        if (SystemTray.isSupported()) {
            // Load the image for the tray icon
            Image image = Toolkit.getDefaultToolkit().getImage("logo.jpg"); // Replace "path_to_your_icon.png" with the actual path to your icon
            
            // Create the tray icon
            TrayIcon trayIcon = new TrayIcon(image, "Tooltip"); // Replace "Tooltip" with the tooltip text
            
            // Get the system tray
            SystemTray tray = SystemTray.getSystemTray();
            System.out.println("images created");
            
            try {
                // Add the tray icon to the system tray
                tray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("TrayIcon could not be added.");
            }
        } else {
            // System tray is not supported
            System.err.println("System tray is not supported.");
        }
    }


    private JLabel addTime(){
        

        
        JLabel label= new Label("01:09:32", 100);
        return label;
    }


    public void showWindow(){
        addApplicationImage();

        frame.add(addTime());
        frame.setVisible(true);

    }
}
