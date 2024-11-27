import javax.swing.*;
import java.awt.*;

public class DashbroadPanelAdmin extends JPanel {
    public DashbroadPanelAdmin() {
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
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\ICONADMINRB.png", 100, 100); // Ganti path dengan logo Anda
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
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Memanfaatkan 2 kolom
        add(titleLabel, gbc);

        // Tombol Tambahkan Barang
        JButton tambahBarangButton = new JButton("Tambahkan Barang");
        UIStyle.styleButton(tambahBarangButton);
        //private void untuk tambahkanBarang
        tambahBarangButton.addActionListener(e -> tambahkanBarangAction());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        tambahBarangButton.setPreferredSize(new Dimension(300, 35));
        add(tambahBarangButton, gbc);

        // Tombol ListBarang
        JButton listBarangButton = new JButton("List Barang");
        UIStyle.styleButton(listBarangButton);
        //private void untuk listbarangAction
        listBarangButton.addActionListener(e -> listBarangAction());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        listBarangButton.setPreferredSize(new Dimension(300, 35));
        add(listBarangButton, gbc);

        // Tombol List Transaksi
        JButton listTransaksiButton = new JButton("List Transaksi");
        UIStyle.styleButton(listTransaksiButton);
        //private void untuk listTransaksiAction
        listTransaksiButton.addActionListener(e -> listTransaksiAction());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        listTransaksiButton.setPreferredSize(new Dimension(300, 35));
        add(listTransaksiButton, gbc);

        
        //Tombol Kembali Ke Laman Login
        JButton kembaliloginButton = new JButton("Back");
        UIStyle.styleButton(kembaliloginButton);
        gbc.gridx = 0;
        gbc.gridy = 5;
        //private void kembali
        kembaliloginButton.addActionListener(e -> kembali());
        add(kembaliloginButton, gbc);
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

    // Navigasi Dan Fitur untuk tombol Tambahkan Barang 
    private void tambahkanBarangAction() {
        JFrame tambahbarang = (JFrame)SwingUtilities.getWindowAncestor(this);
        if (tambahbarang != null) {
            tambahbarang.dispose();
            //Navigasi Tombol
            tambahbarang.setContentPane(new TambahBarang());
            tambahbarang.dispose();
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
            listbarang.dispose();
    }
    }

    // Action untuk tombol List Transaksi 
    private void listTransaksiAction() {
        JFrame listransaksi = (JFrame)SwingUtilities.getWindowAncestor(this);
        if (listransaksi != null) {
            listransaksi.dispose();
            //Navigasi Tombol
            listransaksi.setContentPane(new ListTransaksi());
            listransaksi.dispose();
        
        }
    }
}