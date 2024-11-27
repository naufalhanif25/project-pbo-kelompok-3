
import javax.swing.*;
import java.awt.*;

public class UIStyle {

    // Style untuk JLabel
    public static void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.DARK_GRAY);
    }

    // Style untuk JTextField
    public static void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textField.setPreferredSize(new Dimension(300, 35));
        textField.setMaximumSize(new Dimension(300, 35));
    }

    // Style untuk JButton
    public static void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(58, 123, 245));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 40));
    }
}