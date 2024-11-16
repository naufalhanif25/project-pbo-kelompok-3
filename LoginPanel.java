import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

public class LoginPanel extends JPanel {
    private JTextField username;
    private JPasswordField password;
    private JComboBox<String> roleBox;
    private JButton login;
    private JButton buatakun;

    public LoginPanel() {
        setLayout(null); 
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
        // Mengatur posisi komponen agar lebih fleksibel
        int panelWidth = 400;
        int panelHeight = 500;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        
        int currentY = y + 50;  

        // Title Label
        JLabel titleLabel = new JLabel("Sign in");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setBounds(650 + 80, 200, 240, 40);
        add(titleLabel);

        currentY += 60;

        // Role Label
        JLabel roleLabel = new JLabel("Pilih Role:");
        roleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        roleLabel.setForeground(Color.DARK_GRAY);
        roleLabel.setBounds(580 + 20, 250, 100, 30);
        add(roleLabel);

        // Role ComboBox
        String[] roles = {"Admin", "User"};
        roleBox = new JComboBox<>(roles);
        roleBox.setFont(new Font("Arial", Font.PLAIN, 14));
        roleBox.setBounds(580 + 120, 250, 180, 30);
        add(roleBox);

        currentY += 50;

        // Username Label
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        usernameLabel.setForeground(Color.DARK_GRAY);
        usernameLabel.setBounds(580 + 20, 300, 100, 30);
        add(usernameLabel);

        // Username 
        username = new JTextField(20);
        username.setFont(new Font("Arial", Font.PLAIN, 14));
        username.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        username.setBounds(580 + 120, 300, 180, 30);
        add(username);

        currentY += 50;

        // Password Label
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passwordLabel.setForeground(Color.DARK_GRAY);
        passwordLabel.setBounds(580 + 20, 350, 100, 30);
        add(passwordLabel);

        // Password 
        password = new JPasswordField(20);
        password.setFont(new Font("Arial", Font.PLAIN, 14));
        password.setBorder(new LineBorder(new Color(200, 200, 200), 1, true));
        password.setBounds(580 + 120, 350, 180, 30);
        add(password);

        currentY += 60;

        // Login Button
        login = new JButton("Login");
        login.setFont(new Font("Arial", Font.BOLD, 14));
        login.setBackground(new Color(58, 123, 245)); 
        login.setForeground(Color.WHITE);
        login.setBounds(700 + 20, 400, 130, 40);
        login.setFocusPainted(false);
        login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        login.addActionListener(e -> handleLogin());
        add(login);

        //
        JLabel info = new JLabel("Belum Punya Akun? Daftar Disni!");
        info.setFont(new Font("Arial", Font.BOLD, 16));
        info.setForeground(Color.DARK_GRAY);
        info.setBounds(630 + 20, 450, 270, 30);
        add(info);

        // Create Account Button
        buatakun = new JButton("Buat Akun Baru");
        buatakun.setFont(new Font("Arial", Font.PLAIN, 14));
        buatakun.setBackground(new Color(255, 102, 102)); 
        buatakun.setForeground(Color.WHITE);
        buatakun.setBounds(540 + 160, 500, 160, 40); 
        buatakun.setFocusPainted(false);
        buatakun.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        buatakun.addActionListener(e -> handleCreateAccount());
        add(buatakun);
    }

    private void handleLogin() {
        String lusername = username.getText();
        String lpassword = new String(password.getPassword());
        String role = (String) roleBox.getSelectedItem();

        // Validasi login
        if (lusername.isEmpty() || lpassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username dan Password harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("Admin".equals(role) && username.equals("admin") && password.equals("adminpass")) {
            JOptionPane.showMessageDialog(this, "Login berhasil sebagai Admin!", "Info", JOptionPane.INFORMATION_MESSAGE);
            // TODO: Navigasi ke halaman Admin
        } else if ("User".equals(role) && username.equals("user") && password.equals("userpass")) {
            JOptionPane.showMessageDialog(this, "Login berhasil sebagai User!", "Info", JOptionPane.INFORMATION_MESSAGE);
            // TODO: Navigasi ke halaman User
        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleCreateAccount() {
        // Mendapatkan frame saat ini dan menutupnya
        JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (currentFrame != null) {
            currentFrame.dispose();
        }    
        // Menambahkan panel CreateakunPanel ke frame baru
        add(new Createakun());
        setVisible(true);
    }
}
