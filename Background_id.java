import javax.swing.*;
import java.awt.*;

public class Background_id {
    public static void updateBackground(JFrame frame, String imagePath) {
        ImageIcon backgroundImage = new ImageIcon(imagePath);
        Image img = backgroundImage.getImage();
        Image newImg = img.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        backgroundImage = new ImageIcon(newImg);

        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

        
        frame.getLayeredPane().add(backgroundLabel); 

        frame.repaint();
    }
}
    