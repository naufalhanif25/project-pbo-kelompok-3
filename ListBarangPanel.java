import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;

public class ListBarangPanel extends JPanel {
    private JTable barangTable;
    private DefaultTableModel tableModel;

    public ListBarangPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
        loadBarang();
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
        int panelWidth = 600;
        int panelHeight = 650;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);
        g2d.dispose();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Icon
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = ImageUtils.loadImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\ListBarangRB.png", 100, 100);
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {
            logoLabel.setText("LOGO");
            UIStyle.styleLabel(logoLabel); // Apply label style
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(logoLabel, gbc);

        // Label Judul
        JLabel titleLabel = new JLabel("List Barang", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        gbc.gridy = 1;
        add(titleLabel, gbc);

        // Tabel Barang
        tableModel = new DefaultTableModel(new Object[]{"ID Barang", "Nama Barang", "Tipe Barang", "Stok", "Harga"}, 0);
        barangTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(barangTable);
        scrollPane.setPreferredSize(new Dimension(550, 300));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Tombol Muat Ulang
        JButton reloadButton = new JButton("Muat Ulang");
        UIStyle.styleButton(reloadButton); // Apply button style
        reloadButton.addActionListener(e -> loadBarang());
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(reloadButton, gbc);

        // Tombol Keluar
        JButton backButton = new JButton("Back");
        UIStyle.styleButton(backButton); // Apply button style
        backButton.addActionListener(e -> kembali());
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);
    }


    // Memuat data barang dari file
    private void loadBarang() {
        tableModel.setRowCount(0); // Reset tabel
        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat barang dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Fungsi untuk kembali ke dashboard
    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            kembali.setContentPane(new Dashboard("Admin"));
            kembali.revalidate();
        }
    }
}
