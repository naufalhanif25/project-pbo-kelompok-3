import javax.swing.*;
import java.awt.*;

public class TransaksiPanel extends JPanel {

    private JComboBox<String> metodeComboBox;

    public TransaksiPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
    }

    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Latar Belakang gradasi
        GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 139),
                0, getHeight(), new Color(0, 255, 255));
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

        // Icon Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = loadImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\iconRB.png", 100, 100); // Ganti path dengan logo Anda
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        // Label Metode Pembayaran
        JLabel metodeLabel = new JLabel("Metode Pembayaran", SwingConstants.CENTER);
        styleLabel(metodeLabel);
        metodeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(metodeLabel, gbc);

        // ComboBox untuk memilih metode pembayaran
        String[] metodePembayaran = {"QRIS", "BANK", "COD"};
        metodeComboBox = new JComboBox<>(metodePembayaran);  // Variabel kelas
        metodeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(metodeComboBox, gbc);

        // Tombol Cetak Invoice
        JButton invoiceloginButton = new JButton("Cetak Struk");
        styleButton(invoiceloginButton);
        gbc.gridx = 0;
        gbc.gridy = 3;

        // Mengambil nilai yang dipilih dari ComboBox di dalam lambda expression
        invoiceloginButton.addActionListener(e -> {
            String metodePembayaranDipilih = (String) metodeComboBox.getSelectedItem();  // Menggunakan nama variabel yang berbeda
            invoice(metodePembayaranDipilih);  // Mengirimkan nilai metode pembayaran ke metode invoice()
        });
        add(invoiceloginButton, gbc);

        // Tombol Kembali Ke Login
        JButton kembaliloginButton = new JButton("Back");
        styleButton(kembaliloginButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        kembaliloginButton.addActionListener(e -> kembali());
        add(kembaliloginButton, gbc);
    }

    private void invoice(String metodePembayaran) {
        // Navigasi ke Invoice dengan metode pembayaran yang dipilih
        JFrame transaksiFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (transaksiFrame != null) {
            transaksiFrame.dispose();
            new Invoice(metodePembayaran);  
        }
    }

    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            // Navigasi Tombol
            kembali.setContentPane(new Keranjang());
            kembali.revalidate();
        }
    }

    // Untuk Icon Logo
    private ImageIcon loadImageIcon(String path, int width, int height) {
        try {
            Image image = new ImageIcon(path).getImage();
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Style Button (untuk tombol "Back")
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(58, 123, 245));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 40));
    }
}
