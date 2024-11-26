import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TransaksiPanel extends JPanel {

    private JComboBox<String> metodeComboBox;
    private ArrayList<Barang> daftarBarang = new ArrayList<>();

    public TransaksiPanel() {
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
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\iconRB.png", 100, 100); // Ganti path dengan logo Anda
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
        UIStyle.styleLabel(metodeLabel);
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
        UIStyle.styleButton(invoiceloginButton);
        gbc.gridx = 0;
        gbc.gridy = 3;

        // Mengambil nilai yang dipilih dari ComboBox di dalam lambda expression
        invoiceloginButton.addActionListener(e -> {
            //Valid Login
            System.out.println("Tombol Cetak Struk ditekan!");
            // Mengambil metode pembayaran yang dipilih
            String metodePembayaranDipilih = (String) metodeComboBox.getSelectedItem();

        // Data Transaksi
            String IdTransaksi = "T" + System.currentTimeMillis();  
            String UserName = "Pelanggan";  
            String Tanggal = java.time.LocalDate.now().toString();
            String Keterangan = "Sukses";

        // Hitung total harga
            double totalHarga = hitungTotalHarga();
            System.out.println(totalHarga);
            String Jumlah = String.format("%.2f", totalHarga);

        // Menyimpan transaksi ke file
        TulisTransaksi.tulisTransaksi(IdTransaksi, UserName, Tanggal, Keterangan, Jumlah, metodePembayaranDipilih);

        // Navigasi ke halaman invoice setelah transaksi disimpan
        invoice(metodePembayaranDipilih);
    });
    add(invoiceloginButton, gbc);

        // Tombol Kembali Ke Login
        JButton kembaliloginButton = new JButton("Back");
        UIStyle.styleButton(kembaliloginButton);
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

    private double hitungTotalHarga() {
        double total = 0.0;

        System.out.println(daftarBarang);
        for (Barang barang : daftarBarang) {
            System.out.println("Ini barang: " + barang);
            int jumlah = barang.getStok(); 
            double hargaPerUnit = barang.getHarga(); 
            total += jumlah * hargaPerUnit;
        }
    
        return total;
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

}
