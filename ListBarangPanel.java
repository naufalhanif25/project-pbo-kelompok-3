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
    private KeranjangPanel keranjangPanel;

    public ListBarangPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        TipeBarang = new HashMap<>();
        keranjangPanel = new KeranjangPanel(); 
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
        ImageIcon logoIcon = new ImageIcon("pict\\ListBarangRB.png");
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

        //Delete Barang
        JButton deleteButton = new JButton("Hapus Barang");
        UIStyle.styleButton(deleteButton);
        deleteButton.addActionListener(e -> hapusBarang());
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        add(deleteButton, gbc);
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
        gbc.gridy = 6;
        add(backButton, gbc);
    }

    // Memuat data barang dari file
    private void loadBarang() {
        tableModel.setRowCount(0); // Reset tabel
        TipeBarang.clear(); // Kosongkan HashMap
        try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                tableModel.addRow(data);

                // Masukkan data ke HashMap berdasarkan tipe barang
                TipeBarang.computeIfAbsent(data[2], k -> new ArrayList<>()).add(data);
            }

            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat barang dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Hapus Barang
    private void hapusBarang() {
        int selectedRow = barangTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        // Ambil data dari baris yang dipilih
        String idBarang = tableModel.getValueAt(selectedRow, 0).toString();
    
        // Konfirmasi penghapusan
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Apakah Anda yakin ingin menghapus barang dengan ID " + idBarang + "?", 
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
    
        // Memuat data dari file ke dalam ArrayList
        ArrayList<String> listDomain = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                listDomain.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data dari file!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // Membuat daftar target yang akan dihapus
        ArrayList<String> listTarget = new ArrayList<>();
        for (String item : listDomain) {
            if (item.startsWith(idBarang + ",")) {
                listTarget.add(item);
            }
        }
    
        // Menghapus item menggunakan metode hapusList
        keranjangPanel.hapusList(listDomain, listTarget);


        // Menulis ulang daftar yang sudah dihapus ke file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("txt\\Barang.txt"))) {
            for (String item : listDomain) {
                writer.write(item);
                writer.newLine();
            }
            JOptionPane.showMessageDialog(this, "Barang berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadBarang(); // Memperbarui tabel
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menulis ulang data ke file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
        
    // Fungsi untuk kembali ke dashboard
    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            kembali.setContentPane(new Dashboard("Admin"));
            kembali.dispose();
        }
    }
}