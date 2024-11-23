import javax.swing.*;
import java.awt.*;

public class DashboardPanelPelanggan extends JPanel {

    public DashboardPanelPelanggan() {
        setLayout(new GridBagLayout());
        setOpaque(true);

        addComponents();
    }
    //Sytle Label


    //Sytle Buttom
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(52, 152, 219));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Latar belakang gradasi
        GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 139),
                0, getHeight(), new Color(0, 255, 255));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Panel utama
        g2d.setColor(new Color(255, 255, 255, 200));
        int panelWidth = 400;
        int panelHeight = 400;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

        // Border panel
        g2d.setColor(new Color(200, 200, 200));
        g2d.drawRoundRect(x, y, panelWidth, panelHeight, 20, 20);

        g2d.dispose();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Icon Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = loadImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\iconRB.png", 100, 100); 
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {
            logoLabel.setText("LOGO"); 
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
            logoLabel.setForeground(Color.WHITE);
        }
        gbc.gridx = 0;
        gbc.gridy = 0; 
        add(logoLabel, gbc);

        // Label judul
        JLabel titleLabel = new JLabel("Pelanggan Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 1; 
        add(titleLabel, gbc);

        // Tombol Beli Barang
        JButton beliBarangButton = new JButton("Beli Barang");
        styleButton(beliBarangButton);
        gbc.gridx = 0;
        gbc.gridy = 2; 
        beliBarangButton.addActionListener(e -> BeliBarang());
        add(beliBarangButton, gbc);

        // Tombol Keranjang Barang
        JButton keranjangBarangButton = new JButton("Keranjang");
        styleButton(keranjangBarangButton);
        gbc.gridx = 0;
        gbc.gridy = 3; 
        add(keranjangBarangButton, gbc);

        //Tombol Kembali KeLogin
        JButton kembaliloginButton = new JButton("Back");
        styleButton(kembaliloginButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        kembaliloginButton.addActionListener(e -> kembali());
        add(kembaliloginButton, gbc);

        // // Tombol Transaksi/Pembayaran
        // JButton transaksiButton = new JButton("Transaksi/Pembayaran");
        // styleButton(transaksiButton);
        // gbc.gridx = 0;
        // gbc.gridy = 4; 
        // add(transaksiButton, gbc);

        // // Action listener untuk Transaksi/Pembayaran
        // transaksiButton.addActionListener(e -> showTransaksiDialog());
    }

    private void BeliBarang(){
        JFrame belibarang = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (belibarang != null) {
            belibarang.dispose();
            //Navigasi Tombol
            belibarang.setContentPane(new Belibarang());
        }
    }
    

    private void kembali(){
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            //Navigasi Tombol
            kembali.setContentPane(new Login());
        }
    }

    // Menampilkan dialog untuk memilih metode pembayaran dalam transaksi
    // private void showTransaksiDialog() {
    //     String[] options = {"QRIS", "BANK", "COD"};
    //     int option = JOptionPane.showOptionDialog(this, "Pilih Metode Pembayaran", "Pembayaran",
    //             JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

    //     if (option != JOptionPane.CLOSED_OPTION) {
    //         switch (option) {
    //             case 0:
    //                 JOptionPane.showMessageDialog(this, "Anda memilih pembayaran melalui QRIS.");
    //                 break;
    //             case 1:
    //                 JOptionPane.showMessageDialog(this, "Anda memilih pembayaran melalui Bank.");
    //                 break;
    //             case 2:
    //                 JOptionPane.showMessageDialog(this, "Anda memilih pembayaran melalui COD.");
    //                 break;
    //         }
    //     }
    // }

    // Method untuk memuat logo dengan ukuran tertentu
    private ImageIcon loadImageIcon(String path, int width, int height) {
        try {
            ImageIcon icon = new ImageIcon(path);
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
