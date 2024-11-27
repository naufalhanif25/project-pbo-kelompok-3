
import javax.swing.*;
import java.awt.*;

public class DashboardPanelPelanggan extends JPanel {

    public DashboardPanelPelanggan() {
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
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\ICONPELANGGANRB.png", 100, 100); 
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
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        titleLabel.setForeground(new Color(44, 62, 80));
        gbc.gridx = 0;
        gbc.gridy = 1; 
        add(titleLabel, gbc);

        // Tombol Beli Barang
        JButton beliBarangButton = new JButton("Beli Barang");
        UIStyle.styleButton(beliBarangButton);
        gbc.gridx = 0;
        gbc.gridy = 2; 
        beliBarangButton.addActionListener(e -> BeliBarang());
        add(beliBarangButton, gbc);

        // Tombol Keranjang Barang
        JButton keranjangBarangButton = new JButton("Keranjang");
        UIStyle.styleButton(keranjangBarangButton);
        keranjangBarangButton.addActionListener(e -> Keranjang());
        gbc.gridx = 0;
        gbc.gridy = 3; 
        add(keranjangBarangButton, gbc);

        //Tombol Kembali KeLogin
        JButton kembaliloginButton = new JButton("Back");
        UIStyle.styleButton(kembaliloginButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        kembaliloginButton.addActionListener(e -> kembali());
        add(kembaliloginButton, gbc);

    }

    //Navigasi Keranjang
    private void Keranjang(){
        JFrame keranjang = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (keranjang != null) {
            keranjang.dispose();
            //Navigasi Tombol
            keranjang.setContentPane(new Keranjang());
            keranjang.dispose();
        }
    }


    private void BeliBarang(){
        JFrame belibarang = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (belibarang != null) {
            belibarang.dispose();
            //Navigasi Tombol
            belibarang.setContentPane(new Belibarang());
            belibarang.dispose();
        }
    }
    

    private void kembali(){
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            //Navigasi Tombol
            kembali.setContentPane(new Login());
            kembali.dispose();
        }
    }
}