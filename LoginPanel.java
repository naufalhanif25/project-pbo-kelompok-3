
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class LoginPanel extends JPanel {
    private JTextField username;
    private JPasswordField password;
    private JComboBox<String> roleBox;
    private JButton login;
    private JButton buatakun;

    public LoginPanel() {
        setLayout(new GridBagLayout()); 
        setOpaque(true);
        addComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Latar belakang gradasi
        BackGroundWarna.drawGradientBackground(g2d, getWidth(), getHeight(),
        new Color(0, 0, 139), new Color(0, 255, 255));

        // Panel utama dengan efek rounded
        g2d.setColor(new Color(255, 255, 255, 230)); 
        int panelWidth = 470;
        int panelHeight = 600;
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
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\LoginRB.png", 100, 100);
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        // Title Label
        JLabel titleLabel = new JLabel("Sign in", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Role Label
        JLabel roleLabel = new JLabel("Pilih Role:");
        UIStyle.styleLabel(roleLabel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1; 
        gbc.anchor = GridBagConstraints.LINE_END;
        add(roleLabel, gbc);

        // Role ComboBox
        String[] roles = {"Admin", "Pelanggan"};
        roleBox = new JComboBox<>(roles);
        roleBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(roleBox, gbc);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        UIStyle.styleLabel(usernameLabel);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(usernameLabel, gbc);

        // Username TextField
        username = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        UIStyle.styleTextField(username);
        add(username, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        UIStyle.styleLabel(passwordLabel);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        // Password Field
        password = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        UIStyle.styleTextField(password);
        gbc.anchor = GridBagConstraints.LINE_START;
        add(password, gbc);

        // Login Button
        login = new JButton("Login");
        UIStyle.styleButton(login);
        login.addActionListener(e -> tombollogin());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        add(login, gbc);

        // Info Label
        JLabel infoLabel = new JLabel("Belum Punya Akun? Daftar Disini!",SwingConstants.CENTER);
        UIStyle.styleLabel(infoLabel);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER;
        add(infoLabel, gbc);

        // Create Account Button
        buatakun = new JButton("Buat Akun Baru");
        UIStyle.styleButton(buatakun);
        buatakun.addActionListener(e -> tombolcreateakun());
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        add(buatakun, gbc);
    }

    private void tombollogin() {
        String lusername = username.getText();
        String lpassword = new String(password.getPassword());
        String role = (String) roleBox.getSelectedItem();
    
        // Validasi login berdasarkan file
        if (lusername.isEmpty() || lpassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            boolean loginValid = validateLogin(role, lusername, lpassword);
            if (loginValid) {
                JOptionPane.showMessageDialog(this, "Login berhasil sebagai " + role + "!", "Info", JOptionPane.INFORMATION_MESSAGE);
                // Navigasi ke Dashboard dengan role yang sesuai
                JFrame Dashboard = (JFrame) SwingUtilities.getWindowAncestor(this);
                if (Dashboard != null) {
                    Dashboard.dispose(); 
                    // Navigasi 
                    Dashboard.setContentPane(new Dashboard(role));
                }
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateLogin(String role, String username, String password) {
        String fileName = (role.equals("Admin")) ? "txt\\Admin.txt" : "txt\\Pelanggan.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(","); 
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat membaca file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void tombolcreateakun() {
        JFrame FrameBikinAkun = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (FrameBikinAkun != null) {
            FrameBikinAkun.dispose();
            // Navigasi Tombol
            FrameBikinAkun.setContentPane(new Createakun());
        }
    }
}