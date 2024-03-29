package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Label extends JLabel {
    public Label(String text, int fontSize) {
        super(text);
        // Set the font color to white
        setForeground(Color.WHITE);
        
        // Create a custom font with specified size
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, fontSize);
        
        // Set the custom font
        setFont(font);
    }
}
