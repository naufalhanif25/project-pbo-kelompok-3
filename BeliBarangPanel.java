import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class BeliBarangPanel extends JPanel {
    private JTable barangTable;
    private DefaultTableModel tableModel;
    private ArrayList<String> keranjang;
    private HashMap<String, List<String[]>> TipeBarang; 
    private ArrayList<Barang> daftarBarang = new ArrayList<>();

    //class belibarangpanel
    public BeliBarangPanel() {
        keranjang = new ArrayList<>();
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

        // Latar Belakang gradasi
        BackGroundWarna.drawGradientBackground(g2d, getWidth(), getHeight(),
        new Color(0, 0, 139), new Color(0, 255, 255));

        // Panel utama dengan efek rounded
        g2d.setColor(new Color(255, 255, 255, 230));
        int panelWidth = 900;
        int panelHeight = 700;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        //Agar Kotak Tidak Lancip
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

        //Untuk Menutup
        g2d.dispose();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Icon Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\BeliBarangRB.png", 100, 100);
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
        //layout manager
        revalidate(); 
        repaint();    


        // Label Search
        JLabel searchLabel = new JLabel("Cari Berdasarkan Tipe:");
        searchLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        add(searchLabel, gbc);
        // Panel search
        JPanel searchPanel = new Search(tableModel, TipeBarang);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        add(searchPanel, gbc);

        // Label (Keranjang & Bayar)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);

        //Tombol Add keranjang
        JButton keranjangButton = new JButton("Add Keranjang");
        UIStyle.styleButton(keranjangButton);
        keranjangButton.addActionListener(e -> tambahKeKeranjang());
        buttonPanel.add(keranjangButton);

        //Tombol Bayar
        JButton bayarButton = new JButton("Bayar");
        UIStyle.styleButton(bayarButton);
        //Membuat Private Void prosesPembayaranz
        bayarButton.addActionListener(e -> prosesPembayaran());
        buttonPanel.add(bayarButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Tombol Kembali
        JButton kembaliButton = new JButton("Back");
        UIStyle.styleButton(kembaliButton);
        //Membuat Private Void Kembali
        kembaliButton.addActionListener(e -> kembali());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 10, 0);
        add(kembaliButton, gbc);

    }
    private void loadBarang() {
        tableModel.setRowCount(0);  // Menghapus data lama dari tabel
        daftarBarang.clear();       // Menghapus barang yang sudah ada di ArrayList
    
        try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    try {
                        String idBarang = data[0];
                        String namaBarang = data[1];
                        String tipeBarang = data[2];
                        int stok = Integer.parseInt(data[3]);
                        double harga = Double.parseDouble(data[4]);
    
                        // Memanggil file Barang
                        Barang barang = new Barang(idBarang, namaBarang, tipeBarang, harga, stok);
                        daftarBarang.add(barang);  // Menambahkan barang ke ArrayList
                        
                        // Menambahkan data barang ke tabel
                        tableModel.addRow(new Object[]{idBarang, namaBarang, tipeBarang, stok, harga, 1, false});
                        
                        // Menambahkan data tipe barang ke HashMap
                        TipeBarang.computeIfAbsent(tipeBarang, k -> new ArrayList<>()).add(data);
                    } catch (NumberFormatException e) {
                        System.err.println("Format data salah: " + line);
                    }
                } else {
                    System.err.println("Baris tidak valid: " + line);
                }
            }

            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat barang dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tambahKeKeranjang() {
        keranjang.clear();  // Reset keranjang
        StringBuilder dataKeranjang = new StringBuilder();
    
        // Loop untuk setiap row di tabel
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 6);  
            if (isSelected != null && isSelected) {
                Double totalHarga = 0.0;
                String namaBarang = (String) tableModel.getValueAt(i, 1);  
                int jumlah = (int) tableModel.getValueAt(i, 5);   
                Double hargaBarang = (Double) tableModel.getValueAt(i, 4); 
                
                totalHarga = hargaBarang * jumlah;
    
                // Menambahkan nama barang dan jumlah ke keranjang
                keranjang.add(namaBarang + " (Jumlah: " + jumlah + ")");
    
                // Menambahkan barang ke data keranjang untuk ditulis ke file
                dataKeranjang.append(namaBarang).append(",").append(jumlah).append(",").append(totalHarga).append("\n");
            }
        }
    
        if (!keranjang.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("txt\\Keranjang.txt", true))) {
                writer.write(dataKeranjang.toString());
                writer.flush();
    
                // Tampilkan pesan konfirmasi
                JOptionPane.showMessageDialog(this,
                        "Barang berhasil ditambahkan ke keranjang!\n" + String.join("\n", keranjang),
                        "Keranjang", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Gagal menulis ke file keranjang!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Keranjang kosong! Pilih barang terlebih dahulu.", "Keranjang", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void prosesPembayaran() {
        StringBuilder selectedItems = new StringBuilder();
        boolean pembayaranBerhasil = false;
        ArrayList<Double> dataHarga = new ArrayList<>();
    
        // Menyaring barang yang dipilih berdasarkan checkbox
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 6);  
            Double total = 0.0;
            if (isSelected != null && isSelected) {
                String namaBarang = (String) tableModel.getValueAt(i, 1); 
                Double hargaBarang = (Double) tableModel.getValueAt(i, 4);
                int jumlah = (int) tableModel.getValueAt(i, 5);            
                int stok = (int) tableModel.getValueAt(i, 3);  
                
                total += hargaBarang * jumlah;
    
                // Memastikan jumlah yang diminta tidak melebihi stok
                if (jumlah <= stok) {
                    // Update stok di tabel
                    tableModel.setValueAt(stok - jumlah, i, 3);
                    
                    // Simpan barang yang dipilih untuk ditampilkan di dialog
                    selectedItems.append(namaBarang).append(" (Jumlah: ").append(jumlah).append(")\n");
                    pembayaranBerhasil = true;
                } else {
                    JOptionPane.showMessageDialog(this, "Stok barang " + namaBarang + " tidak cukup.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            dataHarga.add(total);
        }
    
        if (pembayaranBerhasil) {
            saveBarangToFile();  // Simpan perubahan stok barang ke file
    
            // Tampilkan konfirmasi pembayaran
            JOptionPane.showMessageDialog(this, "Pembayaran berhasil untuk barang:\n" + selectedItems.toString(),
                    "Pembayaran Sukses", JOptionPane.INFORMATION_MESSAGE);

            double totalHarga = 0.0;

            for (double tempHarga : dataHarga) {
                totalHarga += tempHarga;
            }
    
            // Navigasi ke halaman transaksi
            JFrame transaksi = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (transaksi != null) {
                transaksi.dispose();
                transaksi.setContentPane(new Transaksi(totalHarga));
                transaksi.revalidate();
                //Untuk Menutup
                transaksi.dispose();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tidak ada barang yang dipilih untuk pembayaran!", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    //Private saveBarangToFile
    private void saveBarangToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("txt\\Barang.txt"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String idBarang = (String) tableModel.getValueAt(i, 0);
                String namaBarang = (String) tableModel.getValueAt(i, 1);
                String tipeBarang = (String) tableModel.getValueAt(i, 2);
                int stok = (int) tableModel.getValueAt(i, 3);  // Update stok
                double harga = (double) tableModel.getValueAt(i, 4);
    
                writer.write(idBarang + "," + namaBarang + "," + tipeBarang + "," + stok + "," + harga + "\n");
            }
            writer.flush();
            writer.close();
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
        //Untuk Menutup
        kembali.dispose();
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