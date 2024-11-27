import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.util.Iterator;
import java.util.ArrayList;

public class KeranjangPanel extends JPanel {
    private JTable keranjangTable;
    private DefaultTableModel tableModel;

    public KeranjangPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
        loadKeranjang();
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
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\BeliBarangRB.png", 100, 100); // Ganti path dengan logo Anda
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        //Judul 
        JLabel titleLabel = new JLabel("Keranjang", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 20));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(titleLabel, gbc);

        // Tabel Keranjang dengan checkbox
        String[] columnNames = {"Pilih", "Nama Barang", "Jumlah", "Total Harga"};
        tableModel = new DefaultTableModel(columnNames, 0);
        keranjangTable = new JTable(tableModel);
        keranjangTable.setRowHeight(30);

        // Menambahkan checkbox  
        keranjangTable.getColumnModel().getColumn(0).setCellEditor(keranjangTable.getDefaultEditor(Boolean.class));
        keranjangTable.getColumnModel().getColumn(0).setCellRenderer(keranjangTable.getDefaultRenderer(Boolean.class));

        JScrollPane scrollPane = new JScrollPane(keranjangTable);
        scrollPane.setPreferredSize(new Dimension(350, 200));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scrollPane, gbc);

        // Tombol Bayar
        JButton bayarButton = new JButton("Checkout");
        UIStyle.styleButton(bayarButton);
        //private void prosesBayar
        bayarButton.addActionListener(e -> prosesBayar());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(bayarButton, gbc);

        // Tombol Hapus keranjang
        JButton hapusButton = new JButton("Hapus Keranjang");
        UIStyle.styleButton(hapusButton);
        //public void hapusBarang
        hapusButton.addActionListener(e -> hapusBarang());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(hapusButton, gbc);

        // Tombol Kembali
        JButton kembaliButton = new JButton("Kembali");
        UIStyle.styleButton(kembaliButton);
        //private void Kembali
        kembaliButton.addActionListener(e -> kembali());
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(kembaliButton, gbc);
    }

    private void loadKeranjang() {
        ReadLog log = new ReadLog();
        String username = log.readFile();

        if (tableModel != null) {
            tableModel.setRowCount(0);
        }
        // Membaca file Keranjang.txt dan mengisi tabel
        try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Keranjang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String tempName = data[0];

                if (data.length == 4 && tempName.equals(username)) {
                    String namaBarang = data[1].trim();
                    int jumlah = Integer.parseInt(data[2].trim());
                    double totalHarga = Double.parseDouble(data[3].trim());
                    tableModel.addRow(new Object[]{false, namaBarang, jumlah, totalHarga}); 
                }
            }

            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data keranjang!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prosesBayar() {
        // Menyaring barang yang dipilih berdasarkan checkbox yang dicentang
        StringBuilder selectedItems = new StringBuilder();
        ArrayList<String> items = new ArrayList<>();
        Double totalHarga = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0); // Kolom 0 adalah checkbox
            if (isSelected != null && isSelected) {
                String namaBarang = (String) tableModel.getValueAt(i, 1);
                int jumlah = (int) tableModel.getValueAt(i, 2);
                double harga = (double) tableModel.getValueAt(i, 3);
                totalHarga += harga;
                selectedItems.append(namaBarang).append(",").append(jumlah).append(",").append(harga).append("\n");
                String tempKeranjang = namaBarang + "," + jumlah + "," + harga;
                
                items.add(tempKeranjang);
            }
        }
    
        if (items.size() > 0) {
            // Tampilkan konfirmasi pembayaran
            JOptionPane.showMessageDialog(this,
                    "Pembayaran untuk barang yang dipilih:\n" + selectedItems.toString(),
                    "Pembayaran",
                    JOptionPane.INFORMATION_MESSAGE);
    
            // Navigasi ke panel transaksi tanpa menutup jendela
            JFrame transaksi = (JFrame) SwingUtilities.getWindowAncestor(this); 
            if (transaksi != null) {
                ArrayList<String> data = new ArrayList<>();

                try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Keranjang.txt"))) {
                    String line;
        
                    while ((line = reader.readLine()) != null) {
                        data.add(line);
                    }
                    
                    hapusList(items, data);

                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                //Navigasi Transaksi
                transaksi.dispose();
                transaksi.setContentPane(new Transaksi(totalHarga));  
                transaksi.revalidate(); 
                transaksi.dispose();
            }
        } 
        else {
            JOptionPane.showMessageDialog(this,
                    "Pilih barang terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void hapusBarang() {
        ArrayList<String> items = new ArrayList<>();
        ReadLog log = new ReadLog();
        Double totalHarga = 0.0;
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0); // Kolom 0 adalah checkbox
            if (isSelected != null && isSelected) {
                String username = log.readFile();
                String namaBarang = (String) tableModel.getValueAt(i, 1);
                int jumlah = (int) tableModel.getValueAt(i, 2);
                double harga = (double) tableModel.getValueAt(i, 3);
                totalHarga += harga;
                String tempKeranjang = username + "," + namaBarang + "," + jumlah + "," + harga;
                
                items.add(tempKeranjang);
            }
        }
    
        if (items.size() > 0) {    
            // Navigasi ke panel transaksi tanpa menutup jendela
            JFrame transaksi = (JFrame) SwingUtilities.getWindowAncestor(this); 
            if (transaksi != null) {
                ArrayList<String> data = new ArrayList<>();

                try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Keranjang.txt"))) {
                    String line;
        
                    while ((line = reader.readLine()) != null) {
                        data.add(line);
                    }
                    
                    hapusList(items, data);

                    reader.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } 
        else {
            JOptionPane.showMessageDialog(this,
                    "Pilih barang terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    public void hapusList(ArrayList<String> listDomain, ArrayList<String> listTarget) {
        ArrayList<String> newItems = new ArrayList<>();
        Iterator<String> iterator = listDomain.iterator();

        while (iterator.hasNext()) {
            String line = iterator.next();

            if (listTarget.contains(line)) {
                listTarget.remove(line);
                iterator.remove();
                
                if (newItems.size() > 0) {
                    newItems.clear();
                }

                for (String item : listTarget) {
                    newItems.add(item);
                }
            }
        }

        hapusKeranjang(newItems);
    }

    public void hapusKeranjang(ArrayList<String> items) {
        ArrayList<String> data = new ArrayList<>(items);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("txt\\Keranjang.txt"))) {
            for (String item : data) {
                writer.write(item);
                writer.newLine();
            }

            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //
        loadKeranjang();
    }
    //Navigasi Kembali
    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            kembali.setContentPane(new Dashboard("Pelanggan"));
            kembali.revalidate();
            kembali.dispose();
        }
    }
}