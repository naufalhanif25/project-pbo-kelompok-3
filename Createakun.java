import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Createakun  extends JFrame {
    private JTextField Cusername;
    private JPasswordField Cpassword;
    private JComboBox<String> CroleBox;

    public Createakun(){
    setLayout(null);
    SwingUtilities.invokeLater(() ->Background_id.updateBackground(this, "D:/PBO/UAS/project-pbo-kelompok-3/pict/CreateAkun.jpg"));
    setSize(800, 600);

    // Frame 
    setTitle("Create Akun");
    setExtendedState(JFrame.MAXIMIZED_BOTH);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    //Pemilihan Role 
    JLabel crole_id = new JLabel("Pilih role yang anda mau!");
    crole_id.setBounds(700, 200, 150, 30);
    crole_id.setForeground(new Color(255, 255, 255));
    add(crole_id);

    String[] Croles = {"Admin", "User"};
    CroleBox = new JComboBox<>(Croles);
    CroleBox.setBounds(700, 230, 100, 30);
    add(CroleBox);

    // Label Pembuatan Usename
    JLabel cusername_id = new JLabel("Buat Username Kamu disini!");
    cusername_id.setBounds(700, 280, 250, 20);
    cusername_id.setForeground(new Color(255, 255, 255));
    add(cusername_id);

    // Create Username
    Cusername = new JTextField();
    Cusername.setBounds(700, 300, 150, 30);
    add(Cusername);

    //Label Pembuatan Password 
    JLabel cpassword_id = new  JLabel("Buat Password Kamu disini!");
    cpassword_id.setBounds(700, 330, 250, 30);
    cpassword_id.setForeground(new Color(255, 255, 255));
    add(cpassword_id);

    // Create Password
    Cpassword = new JPasswordField();
    Cpassword.setBounds(700, 360, 150, 30);
    add(Cpassword);

    // Tombol Create Akun
    JButton cbutton = new JButton("Buat Akun Anda");
    cbutton.setBounds(700, 400, 125, 30);
    add(cbutton);
    
    // Tombol Kemabali Ke Login
    JButton login = new JButton("Kembali Login");
    login.setBounds(700, 437, 125, 30);
    add(login);

    //Penyambung ke 
    login.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e){
        new UserAdmin();
        dispose();;
      }
    });

    //  Membuat Akun
    cbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      String cusername_id = Cusername.getText();
      String cpassword_id = new String(Cpassword.getPassword());
      String pilihrole = (String) CroleBox.getSelectedItem(); 
     
    // Fungsi Buat Akun


    }
    });
    

    }
}

