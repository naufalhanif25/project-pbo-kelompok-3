
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class CreateakunPanel extends JPanel {
    private JComboBox<String> CroleBox;
    private JTextField Cusername;
    private JPasswordField Cpassword; 

    public CreateakunPanel() {
        setLayout(new GridBagLayout()); 
        setOpaque(true);
        setBackground(Color.DARK_GRAY); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin antar komponen
        gbc.fill = GridBagConstraints.HORIZONTAL;

        addComponents();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Latar belakang gradasi
        BackGroundWarna.drawGradientBackground(g2d, getWidth(), getHeight(),
        new Color(0, 0, 139), new Color(0, 255, 255));

        // Panel utama dengan efek rounded (opsional, hanya untuk referensi posisi komponen)
        g2d.setColor(new Color(255, 255, 255, 230));
        int panelWidth = 450;
        int panelHeight = 550;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

        g2d.dispose();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //Icon 
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = ImageUtils.loadImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\CreateAkunIconRB.png", 100, 100); 
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {
            logoLabel.setText("LOGO"); 
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
            logoLabel.setForeground(Color.WHITE);
        }
        gbc.gridx = 0;
        gbc.gridy = 0; 
        gbc.gridwidth = 2;
        add(logoLabel, gbc);

        // Title Label
        JLabel titleLabel = new JLabel("Create Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0; 
        gbc.gridy = 1; 
        gbc.gridwidth = 2; 
        add(titleLabel, gbc);

        // Role Label
        JLabel crole_id = new JLabel("Pilih Role:");
        UIStyle.styleLabel(crole_id);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(crole_id, gbc);

        // ComboBox Role
        String[] roles = {"Admin", "Pelanggan"};
        CroleBox = new JComboBox<>(roles);
        CroleBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(CroleBox, gbc);

        // Username Label
        JLabel cusername_id = new JLabel("Username:");
        UIStyle.styleLabel(cusername_id);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        add(cusername_id, gbc);

        // TextField Username
        Cusername = new JTextField(20);
        UIStyle.styleTextField(Cusername);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(Cusername, gbc);

        // Password Label
        JLabel cpassword_id = new JLabel("Password:");
        UIStyle.styleLabel(cpassword_id);
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(cpassword_id, gbc);

        // PasswordField Password
        Cpassword = new JPasswordField(20);
        UIStyle.styleTextField(Cpassword);
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(Cpassword, gbc);

        // Create Account Button
        JButton cbutton = new JButton("Buat Akun");
        UIStyle.styleButton(cbutton);
        cbutton.addActionListener(e -> createakun());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        add(cbutton, gbc);

        // Info Label
        JLabel info = new JLabel("Sudah punya akun? Login di sini!", SwingConstants.CENTER);
        UIStyle.styleLabel(info);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(info, gbc);

        // Back to Login Button
        JButton backButton = new JButton("Kembali Login");
        UIStyle.styleButton(backButton);
        backButton.addActionListener(e -> kembaliLogin());
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(backButton, gbc);
    }
    //Navigasi buttom Ke Login
    private void kembaliLogin() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            //Navigasi Tombol
            kembali.setContentPane(new Login());
            kembali.revalidate();
        }
    }

    private void createakun() {
        String username = Cusername.getText();
        String password = new String(Cpassword.getPassword());
        String role = (String) CroleBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap isi semua kolom untuk membuat akun.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Menulis ke file sesuai dengan role
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(role + ".txt", true))) {
                writer.write(username + "," + password);
                writer.newLine();
                JOptionPane.showMessageDialog(this, "Akun berhasil dibuat sebagai " + role + "!", "Info", JOptionPane.INFORMATION_MESSAGE);
                kembaliLogin();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan akun.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}