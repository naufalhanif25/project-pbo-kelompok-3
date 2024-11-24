import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.net.URL;

public class QrisPanel extends JPanel {

    private JLabel qrCodeLabel; // Label untuk menampilkan QR Code

    public QrisPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
    }

    // Style Label
    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.DARK_GRAY);
    }

    // Style Button
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(58, 123, 245));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 40));
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
        ImageIcon logoIcon = loadImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\iconRB.png", 100, 100); // Ganti path dengan logo Anda
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        // Label untuk Informasi QRIS
        JLabel titleLabel = new JLabel("Pembayaran QRIS", SwingConstants.CENTER);
        styleLabel(titleLabel);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Tombol Bayar QRIS
        JButton bayarButton = new JButton("Bayar Dengan QRIS");
        styleButton(bayarButton);
        bayarButton.addActionListener(e -> bayarDenganQRIS());
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(bayarButton, gbc);

        // Tombol Kembali
        JButton kembaliButton = new JButton("Kembali Ke Dashboard");
        styleButton(kembaliButton);
        kembaliButton.addActionListener(e -> kembali());
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(kembaliButton, gbc);

        // Label untuk menampilkan QR Code
        qrCodeLabel = new JLabel();
        qrCodeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        qrCodeLabel.setPreferredSize(new Dimension(300, 300)); 
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(qrCodeLabel, gbc);
    }

    private void bayarDenganQRIS() {
        // Menampilkan QR code dari Python
        String pythonFilePath = "D:\\PBO\\UAS\\project-pbo-kelompok-3\\QR\\qrCode.py"; 
        String command = "python \"" + pythonFilePath + "\""; 

        try {
            // Menjalankan skrip Python
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();

            // Ambil gambar QR Code hasil dari Python (disimpan sebagai file gambar sementara)
            File qrImageFile = new File("D:\\PBO\\UAS\\project-pbo-kelompok-3\\QR\\qr_image.png"); // Path file gambar QR code
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

    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            kembali.setContentPane(new Dashboard("Pelanggan"));
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
}
