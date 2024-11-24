import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableCellEditor;

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

    private void styleLabel(JLabel Label) {
        Label.setFont(new Font("Arial", Font.BOLD, 16));
        Label.setForeground(Color.DARK_GRAY);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(58, 123, 245));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(200, 40));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Latar Belakang gradasi
        GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 139),
                0, getHeight(), new Color(0, 255, 255));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Panel utama dengan efek rounded
        g2d.setColor(new Color(255, 255, 255, 230));
        int panelWidth = 400;
        int panelHeight = 600;
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
        ImageIcon logoIcon = loadImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\iconRB.png", 100, 100); // Ganti path dengan logo Anda
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
        String[] columnNames = {"Pilih", "Nama Barang", "Jumlah"};
        tableModel = new DefaultTableModel(columnNames, 0);
        keranjangTable = new JTable(tableModel);
        keranjangTable.setRowHeight(30);

        // Menambahkan checkbox untuk kolom "Pilih"
        keranjangTable.getColumnModel().getColumn(0).setCellEditor(keranjangTable.getDefaultEditor(Boolean.class));
        keranjangTable.getColumnModel().getColumn(0).setCellRenderer(keranjangTable.getDefaultRenderer(Boolean.class));

        JScrollPane scrollPane = new JScrollPane(keranjangTable);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(scrollPane, gbc);

        // Tombol Bayar
        JButton bayarButton = new JButton("Bayar");
        styleButton(bayarButton);
        bayarButton.addActionListener(e -> prosesBayar());
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(bayarButton, gbc);

        // Tombol Kembali
        JButton kembaliButton = new JButton("Kembali");
        styleButton(kembaliButton);
        kembaliButton.addActionListener(e -> kembali());
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(kembaliButton, gbc);
    }

    private void loadKeranjang() {
        // Membaca file Keranjang.txt dan mengisi tabel
        try (BufferedReader reader = new BufferedReader(new FileReader("Keranjang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    String namaBarang = data[0].trim();
                    String jumlah = data[1].trim();
                    tableModel.addRow(new Object[]{false, namaBarang, jumlah}); // Kolom pertama untuk checkbox
                }
            }
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
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Boolean isSelected = (Boolean) tableModel.getValueAt(i, 0); // Kolom 0 adalah checkbox
            if (isSelected != null && isSelected) {
                String namaBarang = (String) tableModel.getValueAt(i, 1);
                String jumlah = (String) tableModel.getValueAt(i, 2);
                selectedItems.append(namaBarang).append(" (Jumlah: ").append(jumlah).append(")\n");
            }
        }
    
        if (selectedItems.length() > 0) {
            // Tampilkan konfirmasi pembayaran
            JOptionPane.showMessageDialog(this,
                    "Pembayaran untuk barang yang dipilih:\n" + selectedItems.toString(),
                    "Pembayaran",
                    JOptionPane.INFORMATION_MESSAGE);
    
            // Navigasi ke panel transaksi tanpa menutup jendela
            JFrame transaksi = (JFrame) SwingUtilities.getWindowAncestor(this); 
            if (transaksi != null) {
                transaksi.setContentPane(new Transaksi());  
                transaksi.revalidate(); 
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Pilih barang terlebih dahulu!",
                    "Peringatan",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            kembali.setContentPane(new Dashboard("Pelanggan"));
            kembali.revalidate();
        }
    }

    // Untuk Icon Logo
    private ImageIcon loadImageIcon(String path, int width, int height) {
        try {
            Image image = new ImageIcon(path).getImage();
            Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
