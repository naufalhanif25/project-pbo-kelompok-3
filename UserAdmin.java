import javax.swing.*;
import java.awt.*;

public class UserAdmin extends JFrame {

public UserAdmin() {
    setLayout(new BorderLayout());
    setBackground(new Color(0, 0, 0));
    SwingUtilities.invokeLater(() ->Background_id.updateBackground(this, "D:/PBO/UAS/project-pbo-kelompok-3/pict/UserAdmin.jpg"));
    setSize(800, 600);

    //Frame 
    setTitle("Login User Admin");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setUndecorated(false);
    setVisible(true);
    
    //Panggil Layout
    LoginPanel loginPanel = new LoginPanel();
    add(loginPanel, BorderLayout.CENTER);
    
  }
}
   
// Pesambung file.java
    // createakun.addActionListener(new ActionListener() {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         new Createakun(); 
    //         dispose(); 
    //     }
    //   });

    // button.addActionListener(new ActionListener() {
    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         String username_id = username.getText();
    //         String password_id = new String(password.getPassword());
    //         String pilihrole = (String) roleBox.getSelectedItem();
            

    //         // Logika untuk memeriksa apakah username dan password sudah ada di database
    //         if ("Admin".equals(pilihrole)) {
    //             if (username_id.equals("admin") && password_id.equals("adminpass")) {
    //                 JOptionPane.showMessageDialog(null, "Login Sebagai Admin Berhasil!");
    //                 // Fungsi Sebagai Admin

    //             } else {
    //                 JOptionPane.showMessageDialog(null, "Username atau password salah");
    //             }
    //         } else if ("User ".equals(pilihrole)) {
    //             if (username_id.equals("user") && password_id.equals("userpass")) {
    //                 JOptionPane.showMessageDialog(null, "Login Sebagai User Berhasil");
    //                 // Fungsi Sebagai User

    //             } else {
    //                 JOptionPane.showMessageDialog(null, "Username atau password salah");
    //             }
    //         }
    //     }
    // });
  

