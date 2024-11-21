import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

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
        GradientPaint gradient = new GradientPaint(0, 0, Color.BLACK, getWidth(), getHeight(), Color.DARK_GRAY);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Panel utama dengan efek rounded (opsional, hanya untuk referensi posisi komponen)
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
        JLabel titleLabel = new JLabel("Create Account", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.DARK_GRAY);
        

        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridwidth = 2; 
        add(titleLabel, gbc);

        // Role Label
        JLabel crole_id = new JLabel("Pilih Role:");
        crole_id.setFont(new Font("Arial", Font.BOLD, 16));
        crole_id.setForeground(Color.DARK_GRAY);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(crole_id, gbc);

        // ComboBox Role
        String[] roles = {"Admin", "User"};
        CroleBox = new JComboBox<>(roles);
        CroleBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(CroleBox, gbc);

        // Username Label
        JLabel cusername_id = new JLabel("Username:");
        cusername_id.setFont(new Font("Arial", Font.BOLD, 16));
        cusername_id.setForeground(Color.DARK_GRAY);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(cusername_id, gbc);

        // TextField Username
        Cusername = new JTextField(20);
        Cusername.setFont(new Font("Arial", Font.PLAIN, 14));
        Cusername.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        Cusername.setPreferredSize(new Dimension(400, 35));
        Cusername.setMaximumSize(new Dimension(400, 35));
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(Cusername, gbc);

        // Password Label
        JLabel cpassword_id = new JLabel("Password:");
        cpassword_id.setFont(new Font("Arial", Font.BOLD, 16));
        cpassword_id.setForeground(Color.DARK_GRAY);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(cpassword_id, gbc);

        // PasswordField Password
        Cpassword = new JPasswordField(20);
        Cpassword.setFont(new Font("Arial", Font.PLAIN, 14));
        Cpassword.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        Cpassword.setPreferredSize(new Dimension(400, 35));
        Cpassword.setMaximumSize(new Dimension(400, 35));
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(Cpassword, gbc);

        // Create Account Button
        JButton cbutton = new JButton("Buat Akun");
        cbutton.setFont(new Font("Arial", Font.BOLD, 14));
        cbutton.setBackground(new Color(58, 123, 245));
        cbutton.setForeground(Color.DARK_GRAY);
        cbutton.addActionListener(e -> buatakun());
        Cpassword.setPreferredSize(new Dimension(300, 35));
        Cpassword.setMaximumSize(new Dimension(300, 35));
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        gbc.anchor = GridBagConstraints.CENTER; 
        add(cbutton, gbc);

        // Info Label
        JLabel info = new JLabel("Sudah punya akun? Login di sini!", SwingConstants.CENTER);
        info.setFont(new Font("Arial", Font.BOLD, 16));
        info.setForeground(Color.DARK_GRAY);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(info, gbc);

        // Back to Login Button
        JButton backButton = new JButton("Kembali Login");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setBackground(new Color(255, 102, 102));
        backButton.setForeground(Color.DARK_GRAY);
        backButton.addActionListener(e -> kembaliLogin());
        backButton.setPreferredSize(new Dimension(300, 35));
        backButton.setMaximumSize(new Dimension(300, 35));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(backButton, gbc);
    }

    private void kembaliLogin() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.setContentPane(new LoginPanel());
            parentFrame.revalidate();
        }
    }

    private void buatakun() {
        String username = Cusername.getText();
        String password = new String(Cpassword.getPassword());
        String role = (String) CroleBox.getSelectedItem();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap isi semua kolom untuk membuat akun.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Akun berhasil dibuat sebagai " + role + "!", "Info", JOptionPane.INFORMATION_MESSAGE);
            kembaliLogin();
        }
    }
}