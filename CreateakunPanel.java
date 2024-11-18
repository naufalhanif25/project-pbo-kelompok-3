import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateakunPanel extends JPanel {
    private JComboBox<String> CroleBox;
    private JTextField Cusername;
    private JPasswordField Cpassword;

    public CreateakunPanel() {
        setLayout(null); // Menggunakan layout null untuk pengaturan manual
        setOpaque(true); // Menjaga panel tetap terlihat
        addComponents(); // Menambahkan komponen ke panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Latar belakang gradasi
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLACK, getWidth(), getHeight(), Color.DARK_GRAY);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Panel utama dengan efek rounded (opsional, hanya ditampilkan sebagai referensi posisi)
        g2d.setColor(new Color(255, 255, 255, 230));
        int panelWidth = 400;
        int panelHeight = 500;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

        g2d.dispose();
    }

private void addComponents() {
    // Title Label
    JLabel titleLabel = new JLabel("Create Account");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
    titleLabel.setForeground(new Color(50, 50, 50));
    titleLabel.setBounds(595 + 80, 185, 240, 40);
    add(titleLabel);

    // Role Label
    JLabel crole_id = new JLabel("Pilih Role:");
    crole_id.setFont(new Font("Arial", Font.BOLD, 16));
    crole_id.setForeground(Color.DARK_GRAY);
    crole_id.setBounds(580 + 20, 250, 100, 30);
    add(crole_id);

    // ComboBox Role
    String[] roles = {"Admin", "User"};
    CroleBox = new JComboBox<>(roles);
    CroleBox.setFont(new Font("Arial", Font.PLAIN, 14));
    CroleBox.setBounds(580 + 120, 250, 180, 30);
    add(CroleBox);

    // Username Label
    JLabel cusername_id = new JLabel("Username:");
    cusername_id.setFont(new Font("Arial", Font.BOLD, 16));
    cusername_id.setForeground(Color.DARK_GRAY);
    cusername_id.setBounds(580 + 20, 300, 100, 30);
    add(cusername_id);

    // TextField Username
    Cusername = new JTextField(20);
    Cusername.setFont(new Font("Arial", Font.PLAIN, 14));
    Cusername.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
    Cusername.setBounds(580 + 120, 300, 180, 30);
    add(Cusername);

    // Password Label
    JLabel cpassword_id = new JLabel("Password:");
    cpassword_id.setFont(new Font("Arial", Font.BOLD, 16));
    cpassword_id.setForeground(Color.DARK_GRAY);
    cpassword_id.setBounds(580 + 20, 350, 100, 30);
    add(cpassword_id);

    // PasswordField Password
    Cpassword = new JPasswordField(20);
    Cpassword.setFont(new Font("Arial", Font.PLAIN, 14));
    Cpassword.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
    Cpassword.setBounds(580 + 120, 350, 180, 30);
    add(Cpassword);

    // Create Account Button
    JButton cbutton = new JButton("Buat Akun");
    cbutton.setFont(new Font("Arial", Font.BOLD, 14));
    cbutton.setBackground(new Color(58, 123, 245));
    cbutton.setForeground(Color.WHITE);
    cbutton.setBounds(700 + 20, 400, 130, 40);
    cbutton.setFocusPainted(false);
    cbutton.addActionListener(e -> buatakun());
    cbutton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    add(cbutton);

    // Info Label
    JLabel info = new JLabel("Sudah punya akun? Login di sini!");
    info.setFont(new Font("Arial", Font.BOLD, 16));
    info.setForeground(Color.DARK_GRAY);
    info.setBounds(630 + 20, 450, 270, 30);
    add(info);

    // Back to Login Button
    JButton backButton = new JButton("Kembali Login");
    backButton.setFont(new Font("Arial", Font.PLAIN, 14));
    backButton.setBackground(new Color(255, 102, 102));
    backButton.setForeground(Color.WHITE);
    backButton.setBounds(540 + 160, 500, 160, 40);
    backButton.setFocusPainted(false);
    backButton.addActionListener(e -> kembaliLogin());

    backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    add(backButton);

}
    private void kembaliLogin(){
        JFrame login = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (login != null) {
            login.dispose();
        }
         add(new Login());
         setVisible(true);   
    }

         private void buatakun() {
            String username = Cusername.getText();
            String password = new String(Cpassword.getPassword());
            String role = (String) CroleBox.getSelectedItem();
    
            // Validasi Pembuatan Akun
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Harap isi semua kolom untuk membuat akun.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if ("Admin".equals(role) && username.equals("admin") && password.equals("adminpass")) {
                JOptionPane.showMessageDialog(this, "Akun berhasil dibuat sebagai Admin!", "Info", JOptionPane.INFORMATION_MESSAGE);
                //Navigasi ketika sudah membuat akun kedatabases sebagai Admin!
            } else if ("User".equals(role) && username.equals("user") && password.equals("userpass")) {
                JOptionPane.showMessageDialog(this, "Akun berhasil dibuat sebagai User!", "Info", JOptionPane.INFORMATION_MESSAGE);
                // Navigasi ketika sudah membuat akun kedatabases sebagai User!
            } else {
                JOptionPane.showMessageDialog(this, "Username atau password salah, atau akun belum terdaftar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
