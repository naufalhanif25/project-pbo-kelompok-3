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
        int panelHeight = 500;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

        g2d.dispose();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Label Judul
        JLabel titleLabel = new JLabel("Daftar Barang", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.DARK_GRAY);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Tabel Barang
        tableModel = new DefaultTableModel(new Object[]{"ID Barang", "Nama Barang", "Stok", "Harga"}, 0);
        barangTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(barangTable);
        scrollPane.setPreferredSize(new Dimension(550, 300));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(scrollPane, gbc);

        // Tombol Muat Ulang
        JButton reloadButton = new JButton("Muat Ulang");
        styleButton(reloadButton);
        reloadButton.addActionListener(e -> loadBarang());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(reloadButton, gbc);

        // Tombol Keluar
        JButton backButton = new JButton("Kembali");
        styleButton(backButton);
        backButton.addActionListener(e -> kembali());
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(backButton, gbc);
    }

    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(58, 123, 245));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(150, 40));
    }

    private void loadBarang() {
        tableModel.setRowCount(0); // Reset tabel
        try (BufferedReader reader = new BufferedReader(new FileReader("barang.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(","); // Asumsi data dipisahkan dengan koma
                tableModel.addRow(data);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat barang dari file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void kembali() {
        JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (parentFrame != null) {
            parentFrame.dispose();
            parentFrame.setContentPane(new Dashboard("Admin"));
            parentFrame.revalidate();
        }
    }
}
