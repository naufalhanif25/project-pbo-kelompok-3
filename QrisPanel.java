import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class QrisPanel extends JPanel {

    private JLabel qrCodeLabel; 
    private double totalHarga;

    public QrisPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
    }

    public QrisPanel(double totalHarga) {
        this.totalHarga = totalHarga;
        
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        
        // Latar Belakang gradasi
        BackGroundWarna.drawGradientBackground(g2d, getWidth(), getHeight(),
        new Color(0, 0, 139), new Color(0, 255, 255));

        // Panel utama dengan efek rounded
        g2d.setColor(new Color(255, 255, 255, 230)); 
        int panelWidth = 450;
        int panelHeight = 650;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

        g2d.dispose();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding untuk setiap komponen
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Icon Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\QRISRB.png", 100, 100); // Ganti path dengan logo Anda
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        // Label untuk Informasi QRIS
        JLabel titleLabel = new JLabel("Pembayaran melalui QRIS", SwingConstants.CENTER);
        UIStyle.styleLabel(titleLabel);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        JLabel hargaLabel = new JLabel("Sebesar Rp" + totalHarga, SwingConstants.CENTER);
        UIStyle.styleLabel(hargaLabel);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(hargaLabel, gbc);

        // Tombol Bayar QRIS
        JButton bayarButton = new JButton("Bayar Dengan QRIS");
        UIStyle.styleButton(bayarButton);
        bayarButton.addActionListener(e -> bayarDenganQRIS());
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(bayarButton, gbc);

        // Tombol Kembali
        JButton kembaliButton = new JButton("Kembali Ke Dashboard");
        UIStyle.styleButton(kembaliButton);
        kembaliButton.addActionListener(e -> kembali());
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(kembaliButton, gbc);

        // Label untuk menampilkan QR Code
        qrCodeLabel = new JLabel();
        qrCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qrCodeLabel.setPreferredSize(new Dimension(300, 300)); 
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(qrCodeLabel, gbc);
    }

    private void bayarDenganQRIS() {
        // Menampilkan QR code dari Python
        String pythonFilePath = "qr\\qrCode.py"; 
        String command = "python \"" + pythonFilePath + "\""; 

        try {
            // Menjalankan skrip Python
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            // Ambil gambar QR Code hasil dari Python (disimpan sebagai file gambar sementara)
            File qrImageFile = new File("qr\\qr_image.png"); // Path file gambar QR code
            if (qrImageFile.exists()) {
                BufferedImage qrImage = ImageIO.read(qrImageFile);

                // Menyesuaikan ukuran gambar agar sesuai dengan ukuran panel
                Image scaledImage = qrImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);

                ImageIcon qrCodeIcon = new ImageIcon(scaledImage);
                qrCodeLabel.setIcon(qrCodeIcon); // Set icon QR code di label
            } else {
                JOptionPane.showMessageDialog(this, "QR Code tidak ditemukan", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menjalankan Python", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    //Navigasi Kembali
    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            kembali.setContentPane(new Dashboard("Pelanggan"));
            kembali.revalidate();
            kembali.dispose();
        }
    }

}