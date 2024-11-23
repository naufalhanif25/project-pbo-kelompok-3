import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TambahBarangPanel extends JPanel {
  private JTextField namaBarangField;
  private JTextField stokField;
  private JTextField hargaField;
  private JTextField idBarangField;
  

  public TambahBarangPanel() {
      setLayout(new GridBagLayout());
      setOpaque(true);
      setBackground(Color.DARK_GRAY);

      addComponents();
  }
  //Stle Label
  private void styleLabel(JLabel Label) {
    Label.setFont(new Font("Arial", Font.BOLD, 16)); 
    Label.setForeground(Color.DARK_GRAY); 
}


  //Sytle TextField
  private void styleTextField(JTextField textField) {
    textField.setFont(new Font("Arial", Font.BOLD, 14));
    textField.setForeground(Color.BLACK);
    textField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    textField.setPreferredSize(new Dimension(300, 35));
    textField.setMaximumSize(new Dimension(300, 35));
  }

  //Sytle Buttom
  private void styleButton(JButton button) {
    button.setFont(new Font("Arial", Font.PLAIN, 14));
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

      // Latar belakang gradasi
      GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 0, 139),
              0, getHeight(), new Color(0, 255, 255));
      g2d.setPaint(gradient);
      g2d.fillRect(0, 0, getWidth(), getHeight());

      // Panel utama dengan efek rounded
      g2d.setColor(new Color(255, 255, 255, 230));
      int panelWidth = 475;
      int panelHeight = 550;
      int x = (getWidth() - panelWidth) / 2;
      int y = (getHeight() - panelHeight) / 2;
      g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

      g2d.dispose();
  }
    // Nambahin Componenen
  private void addComponents() {
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(10, 10, 10, 10);
      gbc.fill = GridBagConstraints.HORIZONTAL;

      // Judul Panel
      JLabel titleLabel = new JLabel("Tambah Barang", SwingConstants.CENTER);
      titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
      titleLabel.setForeground(Color.DARK_GRAY);
      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.gridwidth = 2;
      add(titleLabel, gbc);

      //Id Barang
      JLabel idBarangLabel = new JLabel("Id Barang:");
      styleLabel(idBarangLabel);
      gbc.gridx = 0;
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      add(idBarangLabel, gbc);

      idBarangField = new JTextField(20);
      styleTextField(idBarangField);
      gbc.gridx = 1;
      gbc.gridy = 1;
      gbc.gridwidth = 1;
      add(idBarangField, gbc);


      // Nama Barang
      JLabel namaBarangLabel = new JLabel("Nama Barang:");
      styleLabel(namaBarangLabel);
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 1;
      add(namaBarangLabel, gbc);

      namaBarangField = new JTextField(20);
      styleTextField(namaBarangField);
      gbc.gridx = 1;
      gbc.gridy = 2;
        
      add(namaBarangField, gbc);

      // Stok Barang
      JLabel stokLabel = new JLabel("Stok:");
      styleLabel(stokLabel);
      gbc.gridx = 0;
      gbc.gridy = 3;
      add(stokLabel, gbc);

      stokField = new JTextField(20);
      styleTextField(stokField);
      gbc.gridx = 1;
      gbc.gridy = 3;
      add(stokField, gbc);

      // Harga Jual
      JLabel hargaLabel = new JLabel("Harga Jual:");
      styleLabel(hargaLabel);
      gbc.gridx = 0;
      gbc.gridy = 4;
      gbc.gridwidth = 1;
      add(hargaLabel, gbc);

      hargaField = new JTextField(20);
      styleTextField(hargaField);
      gbc.gridx = 1;
      gbc.gridy = 4;
      add(hargaField, gbc);

      // Tombol Tambahkan Barang
      JButton tambahBarangButton = new JButton("Tambahkan Barang");
      styleButton(tambahBarangButton);
      tambahBarangButton.addActionListener(e -> tambahkanBarang());
      gbc.gridx = 0;
      gbc.gridy = 5;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      add(tambahBarangButton, gbc);

      JButton kembaliloginButton = new JButton("Back");
        styleButton(kembaliloginButton);
        gbc.gridx = 0;
        gbc.gridy = 6;
        kembaliloginButton.addActionListener(e -> kembali());
        add(kembaliloginButton, gbc);
  }

  private void kembali(){
    JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
    if (kembali != null) {
        kembali.dispose();
        //Navigasi Tombol
        kembali.setContentPane(new Dashboard("Admin"));
        kembali.revalidate();
    }
}

  private void tambahkanBarang() {
        // Mengambil data dari form
        String idBarang = idBarangField.getText().trim();
        String namaBarang = namaBarangField.getText().trim();
        String stok = stokField.getText().trim();
        String harga = hargaField.getText().trim();

        // Validasi input
        if (idBarang.isEmpty() || namaBarang.isEmpty() || stok.isEmpty() || harga.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Harap isi semua kolom!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int stokInt = Integer.parseInt(stok);
            double hargaDouble = Double.parseDouble(harga);

            // Menulis data ke file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Barang.txt", true))) {
                writer.write(idBarang + "," + namaBarang + "," + stokInt + "," + hargaDouble);
                writer.newLine();
            }

            // Reset form setelah berhasil
            idBarangField.setText("");
            namaBarangField.setText("");
            stokField.setText("");
            hargaField.setText("");

            JOptionPane.showMessageDialog(this, "Barang berhasil ditambahkan!", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Stok dan harga harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Gagal menulis ke file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
  }
}