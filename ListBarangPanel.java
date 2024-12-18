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
        int panelHeight = 670;
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
        scrollPane.setPreferredSize(new Dimension(550, 200));
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
        JButton backButton = new JButton("Kembali");
        UIStyle.styleButton(backButton);
        backButton.addActionListener(e -> kembali());
        gbc.gridy = 6;
        add(backButton, gbc);
    }

    // Update list barang
    public void updateBarang() {
        ArrayList<String> items = new ArrayList<>();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String idBarang = (String) tableModel.getValueAt(i, 0);
            String namaBarang = (String) tableModel.getValueAt(i, 1);
            String tipeBarang = (String) tableModel.getValueAt(i, 2);
            String stok = (String) tableModel.getValueAt(i, 3);
            String hargaBarang = (String) tableModel.getValueAt(i, 4);

            items.add(idBarang + "," + namaBarang + "," + tipeBarang + "," + stok + "," + hargaBarang);
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("txt\\Barang.txt"))) {
            for (String item : items) {
                writer.write(item);
                writer.newLine();
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Memuat data barang dari file
    private void loadBarang() {    
        if (tableModel.getRowCount() != 0) {
            updateBarang();
        }

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
        ArrayList<String> data = new ArrayList<>();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih barang yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
    
        // Ambil data dari baris yang dipilih
        String idBarangTable = tableModel.getValueAt(selectedRow, 0).toString();
    
        // Konfirmasi penghapusan
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Apakah Anda yakin ingin menghapus barang dengan ID " + idBarangTable + "?", 
            "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;
    
        try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Barang.txt"))) {
            String line;
        
            for (int index = 0; (line = reader.readLine()) != null; index++) {
                String[] parts = line.split(",");
                String idBarang = parts[0];

                data.add(line);

                if (idBarang.equals(idBarangTable)) {
                    data.remove(index);
                }
            }

            tulisBarang(data);
            loadBarang();

            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tulis Barang
    public void tulisBarang(ArrayList<String> items) {
        ArrayList<String> data = new ArrayList<>(items);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("txt\\Barang.txt"))) {
            for (String item : data) {
                writer.write(item);
                writer.newLine();
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
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