import javax.swing.*;
import java.awt.*;


public class Login extends JFrame {
  public Login() {
    setLayout(null);
    // Tombol
    JButton button = new JButton("LOGIN");
    button.setBounds(700, 370, 165,50);
    add(button);
    // Frame  
    setTitle("Sistem perbelanjaan");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    updateBackground();
  }

  private void updateBackground(){

    ImageIcon backgroundImage = new ImageIcon("D:/PBO/UAS/project-pbo-kelompok-3/pict/LOGIN.jpg");
    Image img = backgroundImage.getImage();
    Image newImg = img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
    backgroundImage = new ImageIcon(newImg);

    JLabel backgroundLabel = new JLabel(backgroundImage);
    backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());

    add(backgroundLabel);

    getContentPane().setComponentZOrder(backgroundLabel, getContentPane().getComponentCount()-1);
    repaint();
  }
   public static void main(String[] args) {
    Login frame = new Login();
    frame.setVisible(true); 
  }

}