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
        GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 139),
                0, getHeight(), new Color(0, 255, 255));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

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
        ImageIcon logoIcon = loadImageIcon("D:\\PBO\\UAS\\project-pbo-kelompok-3\\pict\\ListBarangRB.png", 100, 100);
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {
            logoLabel.setText("LOGO");
            styleLabel(logoLabel); // Apply label style
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
        styleButton(reloadButton); // Apply button style
        reloadButton.addActionListener(e -> loadBarang());
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(reloadButton, gbc);

        // Tombol Keluar
        JButton backButton = new JButton("Back");
        styleButton(backButton); // Apply button style
        backButton.addActionListener(e -> kembali());
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(backButton, gbc);
    }

    // Styling untuk Label
    private void styleLabel(JLabel label) {
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(Color.DARK_GRAY);
    }

    // Styling untuk Button
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setBackground(new Color(58, 123, 245));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 40));
    }

    // Styling untuk TextField
    private void styleTextField(JTextField textField) {
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setForeground(Color.BLACK);
        textField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        textField.setPreferredSize(new Dimension(300, 35));
        textField.setMaximumSize(new Dimension(300, 35));
    }

    // Mengambil gambar dengan ukuran yang diinginkan
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
