import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Login extends JFrame {
  public Login() {
    setLayout(null);
    setSize(800, 600);
    

    // Tombol
    JButton button = new JButton("Ayo Mulai");
    button.setBounds(700, 370, 165,50);
    add(button);
    // Frame  
    setTitle("Sistem perbelanjaan");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setVisible(true);
    SwingUtilities.invokeLater(() -> Background_id.updateBackground(this, "D:/PBO/UAS/project-pbo-kelompok-3/pict/LOGIN.jpg"));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
  

  // Action Listener untuk tombol
  button.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        new UserAdmin(); // Membuka jendela UserAdmin
        dispose(); // Menutup jendela Login
    }
  });
}
}