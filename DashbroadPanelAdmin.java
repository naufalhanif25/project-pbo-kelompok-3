import javax.swing.*;
import java.awt.*;

public class DashbroadPanelAdmin extends JPanel {
    public DashbroadPanelAdmin() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        addComponents();
    }
    

    // Style Button
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
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

        // Latar belakang gradasi
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

        //Icon Logo
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


        // Title Label
        JLabel titleLabel = new JLabel("Admin Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Memanfaatkan 2 kolom
        add(titleLabel, gbc);

        // Tombol Tambahkan Barang
        JButton tambahBarangButton = new JButton("Tambahkan Barang");
        styleButton(tambahBarangButton);
        tambahBarangButton.addActionListener(e -> tambahkanBarangAction());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        tambahBarangButton.setPreferredSize(new Dimension(300, 35));
        add(tambahBarangButton, gbc);

        // Tombol ListBarang
        JButton listBarangButton = new JButton("List Barang");
        styleButton(listBarangButton);
        listBarangButton.addActionListener(e -> listBarangAction());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        listBarangButton.setPreferredSize(new Dimension(300, 35));
        add(listBarangButton, gbc);

        // Tombol List Transaksi
        JButton listTransaksiButton = new JButton("List Transaksi");
        styleButton(listTransaksiButton);
        listTransaksiButton.addActionListener(e -> listTransaksiAction());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        listTransaksiButton.setPreferredSize(new Dimension(300, 35));
        add(listTransaksiButton, gbc);

        
        //Tombol Kembali Ke Laman Login
        JButton kembaliloginButton = new JButton("Back");
        styleButton(kembaliloginButton);
        gbc.gridx = 0;
        gbc.gridy = 5;
        kembaliloginButton.addActionListener(e -> kembali());
        add(kembaliloginButton, gbc);
    }

    private void kembali(){
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            //Navigasi Tombol
            kembali.setContentPane(new Login());
        }
    }

    //Untuk mengatur Icon
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

    // Navigasi Dan Fitur untuk tombol Tambahkan Barang 
    private void tambahkanBarangAction() {
        JFrame tambahbarang = (JFrame)SwingUtilities.getWindowAncestor(this);
        if (tambahbarang != null) {
            tambahbarang.dispose();
            //Navigasi Tombol
            tambahbarang.setContentPane(new TambahBarang());
        }
        //JOptionPane.showMessageDialog(this, "Fitur Tambahkan Barang belum diimplementasikan.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // Action untuk tombol List Barang 
    private void listBarangAction() {
        JFrame listbarang = (JFrame)SwingUtilities.getWindowAncestor(this);
        if (listbarang != null) {
            listbarang.dispose();
            //Navigasi Tombol
            listbarang.setContentPane(new ListBarang());
    }
    }

    // Action untuk tombol List Transaksi 
    private void listTransaksiAction() {
        JOptionPane.showMessageDialog(this, "Fitur List Transaksi belum diimplementasikan.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }
}

