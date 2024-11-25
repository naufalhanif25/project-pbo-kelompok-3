import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ListBarangPanel extends JPanel {
    private JTable barangTable;
    private DefaultTableModel tableModel;
    private HashMap<String, List<String[]>> TipeBarang; 

    public ListBarangPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        TipeBarang = new HashMap<>(); 
        addComponents(); 
        loadBarang(); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Latar belakang gradasi
        g2d.setPaint(new GradientPaint(0, 0, new Color(0, 0, 139), 0, getHeight(), new Color(0, 255, 255)));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Panel utama dengan efek rounded
        g2d.setColor(new Color(255, 255, 255, 230));
        int panelWidth = 600;
        int panelHeight = 750;
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
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        ImageIcon logoIcon = new ImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\ListBarangRB.png");
        if (logoIcon.getIconWidth() > 0) {
            logoLabel.setIcon(new ImageIcon(logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        } else {
            logoLabel.setText("LOGO");
        }
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
        add(scrollPane, gbc);

        // Pencarian Barang
        JLabel searchLabel = new JLabel("Cari Berdasarkan Tipe:");
        searchLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(searchLabel, gbc);

        JPanel searchPanel = new Search(tableModel, TipeBarang);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(searchPanel, gbc);
        // Tombol Muat Ulang
        JButton reloadButton = new JButton("Muat Ulang");
        
        reloadButton.addActionListener(e -> loadBarang());
        UIStyle.styleButton(reloadButton);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        add(reloadButton, gbc);

        // Tombol Keluar
        JButton backButton = new JButton("Back");
        UIStyle.styleButton(backButton);
        backButton.addActionListener(e -> kembali());
        gbc.gridy = 5;
        add(backButton, gbc);
    }

    // Memuat data barang dari file
    private void loadBarang() {
        tableModel.setRowCount(0); // Reset tabel
        TipeBarang.clear(); // Kosongkan HashMap
        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);

                // Masukkan data ke HashMap berdasarkan tipe barang
                TipeBarang.computeIfAbsent(data[2], k -> new ArrayList<>()).add(data);
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
        }
    }
}