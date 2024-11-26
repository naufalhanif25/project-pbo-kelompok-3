import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;
import javax.swing.table.DefaultTableModel;

public class ListTransaksiPanel extends JPanel {
    private JTable transaksiTable;
    private DefaultTableModel tableModel;

    public ListTransaksiPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
        loadTransaksi();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        BackGroundWarna.drawGradientBackground(g2d, getWidth(), getHeight(), new Color(0, 0, 139), new Color(0, 255, 255));
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
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\ListTransaksiRB.png", 100, 100);
        logoLabel.setIcon(logoIcon != null ? logoIcon : new ImageIcon());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(logoLabel, gbc);

        // Label Judul
        JLabel titleLabel = new JLabel("List Transaksi", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
        gbc.gridy = 1;
        add(titleLabel, gbc);

        // Tabel Transaksi
        tableModel = new DefaultTableModel(new Object[]{"ID Transaksi", "UserName", "Tanggal", "Deskripsi", "Jumlah", "Metode Pembayaran"}, 0);
        transaksiTable = new JTable(tableModel); 
        JScrollPane scrollPane = new JScrollPane(transaksiTable);
        scrollPane.setPreferredSize(new Dimension(550, 300));
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Tombol Muat Ulang
        JButton reloadButton = new JButton("Muat Ulang");
        reloadButton.addActionListener(e -> loadTransaksi());
        UIStyle.styleButton(reloadButton);
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(reloadButton, gbc);

        // Tombol Keluar
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> kembali());
        UIStyle.styleButton(backButton);
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);
    }

    private void loadTransaksi() {
        tableModel.setRowCount(0);
        try (BufferedReader reader = new BufferedReader(new FileReader("txt\\Transaksi.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    tableModel.addRow(data);
                } else {
                    System.out.println("Baris Data Tidak Valid: " + line);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat transaksi dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        transaksiTable.revalidate();
        transaksiTable.repaint();
    }

    private void kembali() {
        JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (kembali != null) {
            kembali.dispose();
            kembali.setContentPane(new Dashboard("Admin"));
            kembali.revalidate();
        }
    }
}
