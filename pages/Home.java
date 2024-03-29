package pages;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

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

    private JLabel addTime(){
        

        
        JLabel label= new Label("01:09:32", 100);
        return label;
    }


    public void showWindow(){
        

        frame.add(addTime());
        frame.setVisible(true);

    }
}
