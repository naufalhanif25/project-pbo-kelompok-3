import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class BeliBarangPanel extends JPanel {
    private JComboBox<String> metodeBayarBox;
    private JTable barangTable;
    private DefaultTableModel tableModel;
    private ArrayList<String> keranjang; // Untuk menyimpan barang yang dipilih

    public BeliBarangPanel() {
        keranjang = new ArrayList<>();
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

        // Latar Belakang gradasi
        BackGroundWarna.drawGradientBackground(g2d, getWidth(), getHeight(),
        new Color(0, 0, 139), new Color(0, 255, 255));

        // Panel utama dengan efek rounded
        g2d.setColor(new Color(255, 255, 255, 230));
        int panelWidth = 900;
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

        // Icon Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = ImageUtils.loadImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\iconRB.png", 100, 100);
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        // Judul Panel
        JLabel titleLabel = new JLabel("Beli Barang", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridy = 1;
        add(titleLabel, gbc);

        // Tabel Barang
        tableModel = new DefaultTableModel(new Object[]{"ID Barang", "Nama Barang", "Tipe Barang", "Stok", "Harga", "Jumlah", "Pilih"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 5) return Integer.class; // Kolom Jumlah
                if (columnIndex == 6) return Boolean.class; // Kolom Pilih (checkbox)
                return String.class;
            }
        };
        barangTable = new JTable(tableModel);
        barangTable.setRowHeight(30);

        // Editor untuk kolom Jumlah 
        barangTable.getColumnModel().getColumn(5).setCellEditor(new SpinnerEditor());

        JScrollPane scrollPane = new JScrollPane(barangTable);
        scrollPane.setPreferredSize(new Dimension(800, 300));

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scrollPane, gbc);
        revalidate(); 
        repaint();    

        // Panel Tombol (Keranjang & Bayar)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        JButton keranjangButton = new JButton("Add Keranjang");
        UIStyle.styleButton(keranjangButton);
        keranjangButton.addActionListener(e -> tambahKeKeranjang());
        buttonPanel.add(keranjangButton);

        JButton bayarButton = new JButton("Bayar");
        UIStyle.styleButton(bayarButton);
        bayarButton.addActionListener(e -> prosesPembayaran());
        buttonPanel.add(bayarButton);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Tombol Kembali
        JButton kembaliButton = new JButton("Back");
        UIStyle.styleButton(kembaliButton);
        kembaliButton.addActionListener(e -> kembali());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 10, 0);
        add(kembaliButton, gbc);
    }
    private void loadBarang() {
        tableModel.setRowCount(0); // Reset tabel
        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // Validasi jumlah kolom
                    try {
                        String idBarang = data[0];
                        String namaBarang = data[1];
                        String tipeBarang = data[2];
                        int stok = Integer.parseInt(data[3]);
                        double harga = Double.parseDouble(data[4]);

                        // Tambahkan data ke tabel
                        tableModel.addRow(new Object[]{idBarang, namaBarang, tipeBarang, stok, harga, 1, false});
                    } catch (NumberFormatException e) {
                        System.err.println("Format data salah: " + line);
                    }
                } else {
                    System.err.println("Baris tidak valid: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat barang dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tambahKeKeranjang() {
        keranjang.clear(); // Reset keranjang
        StringBuilder dataKeranjang = new StringBuilder();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 6); 
            if (isSelected != null && isSelected) {
                String namaBarang = (String) tableModel.getValueAt(i, 1);
                int jumlah = (int) tableModel.getValueAt(i, 5); 
                keranjang.add(namaBarang + " (Jumlah: " + jumlah + ")");

                // Tambahkan ke data keranjang (untuk ditulis ke file)
                dataKeranjang.append(namaBarang).append(",").append(jumlah).append("\n");
            }
        }

        

        if (!keranjang.isEmpty()) {
            // Tulis data keranjang ke file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Keranjang.txt", true))) {
                writer.write(dataKeranjang.toString());
                writer.flush();

                // Tampilkan pesan konfirmasi
                JOptionPane.showMessageDialog(this,
                        "Barang berhasil ditambahkan ke keranjang!\n" +
                                "Barang:\n" + String.join("\n", keranjang),
                        "Keranjang",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Gagal menulis ke file keranjang!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Keranjang kosong! Pilih barang terlebih dahulu.",
                    "Keranjang",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void prosesPembayaran() {
        StringBuilder selectedItems = new StringBuilder();
        boolean pembayaranBerhasil = false;
    
        // Menyaring barang yang dipilih berdasarkan checkbox yang dicentang
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 6); 
            if (isSelected != null && isSelected) {
                String namaBarang = (String) tableModel.getValueAt(i, 1); 
                int jumlah = (int) tableModel.getValueAt(i, 5); 
                int stok = (int) tableModel.getValueAt(i, 3); 
    
                if (jumlah <= stok) {
                    // Update stok
                    tableModel.setValueAt(stok - jumlah, i, 3); 
    
                    selectedItems.append(namaBarang).append(" (Jumlah: ").append(jumlah).append(")\n");
                    pembayaranBerhasil = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Stok barang " + namaBarang + " tidak cukup.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }
    
        // Jika ada barang yang dipilih dan pembayaran berhasil
        if (pembayaranBerhasil) {
            saveBarangToFile();
    
            // Tampilkan konfirmasi pembayaran
            JOptionPane.showMessageDialog(this,
                    "Pembayaran berhasil untuk barang:\n" + selectedItems.toString(),
                    "Pembayaran Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
    
            // Navigasi ke halaman transaksi
            JFrame transaksi = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (transaksi != null) {
                transaksi.setContentPane(new Transaksi());
                transaksi.revalidate();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Tidak ada barang yang dipilih untuk pembayaran!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    //Private saveBarangToFile
    private void saveBarangToFile() {
        // Menyimpan perubahan stok kembali ke file barang.txt
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("barang.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String idBarang = (String) tableModel.getValueAt(i, 0);
                String namaBarang = (String) tableModel.getValueAt(i, 1);
                String tipeBarang = (String) tableModel.getValueAt(i, 2);
                int stok = (int) tableModel.getValueAt(i, 3); // Stok yang baru
                double harga = (double) tableModel.getValueAt(i, 4);
    
                writer.write(idBarang + "," + namaBarang + "," + tipeBarang + "," + stok + "," + harga + "\n");
            }
            writer.flush(); // Menyimpan perubahan
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan perubahan stok!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
        kembali.dispose();
        //Navigasi Tombol
        kembali.setContentPane(new Dashboard("Pelanggan"));
        kembali.revalidate();
    }
}
    // Nulis Ke transaksi
    private void simpanTransaksiKeFile(String namaPembeli, String barangDibeli, String metodePembayaran, double totalHarga) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transaksi.txt", true))) {
            // Generate ID transaksi berdasarkan timestamp (misalnya TRX + timestamp)
            String idTransaksi = "TRX" + System.currentTimeMillis();
            
            // Ambil tanggal transaksi
            String tanggalTransaksi = java.time.LocalDateTime.now().toString(); // Format: YYYY-MM-DDTHH:MM:SS
            
            // Format untuk mencatat transaksi
            String transaksi = String.format("%s, %s, %s, %s, %s, %s, %.2f\n",
                    idTransaksi, tanggalTransaksi, namaPembeli, barangDibeli, "", metodePembayaran, totalHarga);
            
            // Tulis transaksi ke file
            writer.write(transaksi);
            writer.flush();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencatat transaksi ke file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Editor untuk kolom Jumlah
    class SpinnerEditor extends AbstractCellEditor implements TableCellEditor {
        private JSpinner spinner;

        public SpinnerEditor() {
            spinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1)); // Min: 1, Max: 100, Step: 1
            spinner.setPreferredSize(new Dimension(50, 20));
        }

        @Override
        public Object getCellEditorValue() {
            return spinner.getValue();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            spinner.setValue(value); 
            return spinner;
        }
    }
}
