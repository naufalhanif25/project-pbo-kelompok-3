import javax.swing.*;
import java.awt.*;

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
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLACK, getWidth(), getHeight(), Color.DARK_GRAY);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Panel utama dengan efek rounded
        g2d.setColor(new Color(255, 255, 255, 230)); 
        int panelWidth = 400;
        int panelHeight = 500;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);
        g2d.dispose();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Sign in");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Memanfaatkan 2 kolom
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Role Label
        JLabel roleLabel = new JLabel("Pilih Role:");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Kembali ke 1 kolom
        gbc.anchor = GridBagConstraints.LINE_END;
        add(roleLabel, gbc);

        // Role ComboBox
        String[] roles = {"Admin", "User"};
        roleBox = new JComboBox<>(roles);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(roleBox, gbc);

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(usernameLabel, gbc);

        // Username TextField
        username = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        username.setPreferredSize(new Dimension(400, 35));
        username.setMaximumSize(new Dimension(400, 35));
        add(username, gbc);

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gbc);

        // Password Field
        password = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        password.setPreferredSize(new Dimension(400, 35));
        password.setMaximumSize(new Dimension(400, 35));
        gbc.anchor = GridBagConstraints.LINE_START;
        add(password, gbc);

        // Login Button
        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.BOLD, 14));
        login.setBackground(new Color(58, 123, 245));
        login.setForeground(Color.WHITE);
        login.setFocusPainted(false);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        login.addActionListener(e -> tombollogin());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Memanfaatkan 2 kolom
        login.setPreferredSize(new Dimension(300, 35));
        login.setMaximumSize(new Dimension(300, 35)); 
        gbc.anchor = GridBagConstraints.CENTER;
        add(login, gbc);

        // Info Label
        JLabel infoLabel = new JLabel("Belum Punya Akun? Daftar Disini!");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        infoLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2; // Memanfaatkan 2 kolom
        gbc.anchor = GridBagConstraints.CENTER;
        add(infoLabel, gbc);

        // Create Account Button
        buatakun = new JButton("Buat Akun Baru");
        buatakun.setFont(new Font("Arial", Font.BOLD, 14));
        buatakun.setBackground(new Color(255, 102, 102));
        buatakun.setForeground(Color.WHITE);
        buatakun.setFocusPainted(false);
        buatakun.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buatakun.addActionListener(e -> tombolcreateakun());
        gbc.gridx = 0;
        gbc.gridy = 6;
        buatakun.setPreferredSize(new Dimension(300, 35));
        buatakun.setMaximumSize(new Dimension(300, 35));
        gbc.gridwidth = 2; // Memanfaatkan 2 kolom
        gbc.anchor = GridBagConstraints.CENTER;
        add(buatakun, gbc);
    }

    private void tombollogin() {
        String lusername = username.getText();
        String lpassword = new String(password.getPassword());
        String role = (String) roleBox.getSelectedItem();

        // Validasi login
        if (lusername.isEmpty() || lpassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("Admin".equals(role) && "admin".equals(lusername) && "adminpass".equals(lpassword)) {
            JOptionPane.showMessageDialog(this, "Login berhasil sebagai Admin!", "Info", JOptionPane.INFORMATION_MESSAGE);
            // TODO: Navigasi ke halaman Admin
        } else if ("User".equals(role) && "user".equals(lusername) && "userpass".equals(lpassword)) {
            JOptionPane.showMessageDialog(this, "Login berhasil sebagai User!", "Info", JOptionPane.INFORMATION_MESSAGE);
            // TODO: Navigasi ke halaman User
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tombolcreateakun() {
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (currentFrame != null) {
            currentFrame.dispose();
        }
        // Navigasi ke Createakun
        JFrame createAccountFrame = new JFrame("Create Akun");
        createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createAccountFrame.setSize(800, 600);
        createAccountFrame.add(new Createakun());
        createAccountFrame.setVisible(true);
    }
}
