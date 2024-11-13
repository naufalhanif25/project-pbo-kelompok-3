import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class UserAdmin extends JFrame {
  private JTextField username;
  private JPasswordField password;
  private JComboBox<String> roleBox;

  public UserAdmin() {
    setLayout(null);
    SwingUtilities.invokeLater(() ->Background_id.updateBackground(this, "D:/PBO/UAS/project-pbo-kelompok-3/pict/UserAdmin.jpg"));
    setSize(800, 600);

    //Frame 
    setTitle("Login User Admin");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    // Role
    JLabel role_id = new JLabel("Pilih Role :");
    role_id.setBounds(700, 200, 100, 30);
    role_id.setForeground(new Color(255, 255, 255));
    add(role_id);

    // Box Memilih Role
    String[] roles = {"Admin", "User "};
    roleBox = new JComboBox<>(roles);
    roleBox.setBounds(700, 230, 165, 30);
    add(roleBox);

    // Username
    JLabel username_id = new JLabel("Username");
    username_id.setBounds(700, 280, 100, 30);
    username_id.setForeground(new Color(255, 255, 255));
    add(username_id);

    username = new JTextField();
    username.setBounds(700, 310, 165, 30); 
    add(username);

    // Password
    JLabel password_id = new JLabel("Password"); 
    password_id.setBounds(700, 340, 100, 30);
    password_id.setForeground(new Color(255, 255, 255));
    add(password_id);

    password = new JPasswordField();
    password.setBounds(700, 370, 165, 30); 
    add(password);

    // Login
    JButton button = new JButton("Login");
    button.setBounds(700, 415, 165, 50);
    add(button);

    JLabel createakun_id = new JLabel("Belum Memiliki Akun?!");
    createakun_id.setBounds(700, 445, 150, 30);
    createakun_id.setForeground(new Color(255, 255, 255));
    add(createakun_id);

    JButton createakun = new JButton("Bikin akun disini!");
    createakun.setBounds(700, 470, 165, 50);
    add(createakun);

    // Pesambung file.java
    createakun.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            new Createakun(); 
            dispose(); 
        }
      });

    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username_id = username.getText();
            String password_id = new String(password.getPassword());
            String pilihrole = (String) roleBox.getSelectedItem();
            

            // Logika untuk memeriksa apakah username dan password sudah ada di database
            if ("Admin".equals(pilihrole)) {
                if (username_id.equals("admin") && password_id.equals("adminpass")) {
                    JOptionPane.showMessageDialog(null, "Login Sebagai Admin Berhasil!");
                    // Fungsi Sebagai Admin

                } else {
                    JOptionPane.showMessageDialog(null, "Username atau password salah");
                }
            } else if ("User ".equals(pilihrole)) {
                if (username_id.equals("user") && password_id.equals("userpass")) {
                    JOptionPane.showMessageDialog(null, "Login Sebagai User Berhasil");
                    // Fungsi Sebagai User

                } else {
                    JOptionPane.showMessageDialog(null, "Username atau password salah");
                }
            }
        }
    });
  }
}
   
  

