
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TambahBarangPanel extends JPanel {
  private JTextField namaBarangField;
  private JTextField stokField;
  private JTextField hargaField;
  private JTextField idBarangField;
  private JComboBox<String> tipeBarangComboBox;

  

  public TambahBarangPanel() {
      setLayout(new GridBagLayout());
      setOpaque(true);
      setBackground(Color.DARK_GRAY);

      addComponents();
  }

  @Override
  protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g.create();

      // Latar belakang gradasi
      BackGroundWarna.drawGradientBackground(g2d, getWidth(), getHeight(),
        new Color(0, 0, 139), new Color(0, 255, 255));

      // Panel utama dengan efek rounded
      g2d.setColor(new Color(255, 255, 255, 230));
      int panelWidth = 475;
      int panelHeight = 580;
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

      //Icon Image
      JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\BasketIconRB.png", 100, 100); 
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } else {
            logoLabel.setText("LOGO"); 
            logoLabel.setFont(new Font("Arial", Font.BOLD, 24));
            logoLabel.setForeground(Color.WHITE);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; 
        add(logoLabel, gbc);

      // Judul Panel
      JLabel titleLabel = new JLabel("Tambah Barang", SwingConstants.CENTER);
      titleLabel.setFont(new Font("Consolas", Font.BOLD, 30));
      titleLabel.setForeground(Color.DARK_GRAY);
      gbc.gridy = 1;
      add(titleLabel, gbc);

      //Id Barang
      JLabel idBarangLabel = new JLabel("Id Barang:");
       UIStyle.styleLabel(idBarangLabel);
      gbc.gridx = 0;
      gbc.gridy = 2;
      gbc.gridwidth = 1;
      add(idBarangLabel, gbc);

      idBarangField = new JTextField(20);
      UIStyle.styleTextField(idBarangField);
      gbc.gridx = 1;
      gbc.gridy = 2;
      add(idBarangField, gbc);


      // Nama Barang
      JLabel namaBarangLabel = new JLabel("Nama Barang:");
      UIStyle.styleLabel(namaBarangLabel);
      gbc.gridx = 0;
      gbc.gridy = 3;
      add(namaBarangLabel, gbc);

      namaBarangField = new JTextField(20);
      UIStyle.styleTextField(namaBarangField);
      gbc.gridx = 1;
      gbc.gridy = 3;
      add(namaBarangField, gbc);

      // Tipe Barang 

      JLabel tipeBarangLabel = new JLabel("Tipe Barang:");
      UIStyle.styleLabel(tipeBarangLabel);
      gbc.gridx = 0;
      gbc.gridy = 4;
      add(tipeBarangLabel, gbc);

      tipeBarangComboBox = new JComboBox<>(new String[]{"Elektronik", "Pakaian", "Makanan", "Peralatan","Perabotan","Mainan","Kosmetik","Buku","Aksesoris","Kendaraan"});
      tipeBarangComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
      tipeBarangComboBox.setPreferredSize(new Dimension(300, 35));
      gbc.gridx = 1;
      gbc.gridy = 4;
      add(tipeBarangComboBox, gbc);

      // Stok Barang
      JLabel stokLabel = new JLabel("Stok:");
      UIStyle.styleLabel(stokLabel);
      gbc.gridx = 0;
      gbc.gridy = 5;
      add(stokLabel, gbc);

      stokField = new JTextField(20);
      UIStyle.styleTextField(stokField);
      gbc.gridx = 1;
      gbc.gridy = 5;
      add(stokField, gbc);

      // Harga Jual
      JLabel hargaLabel = new JLabel("Harga Jual:");
      UIStyle.styleLabel(hargaLabel);
      gbc.gridx = 0;
      gbc.gridy = 6;
      add(hargaLabel, gbc);

      hargaField = new JTextField(20);
      UIStyle.styleTextField(hargaField);
      gbc.gridx = 1;
      gbc.gridy = 6;
      add(hargaField, gbc);

      // Tombol Tambahkan Barang
      JButton tambahBarangButton = new JButton("Tambahkan Barang");
      UIStyle.styleButton(tambahBarangButton);
      tambahBarangButton.addActionListener(e -> tambahkanBarang());
      gbc.gridx = 0;
      gbc.gridy = 7;
      gbc.gridwidth = 2;
      gbc.anchor = GridBagConstraints.CENTER;
      add(tambahBarangButton, gbc);

      JButton kembaliloginButton = new JButton("Back");
      UIStyle.styleButton(kembaliloginButton);
        gbc.gridx = 0;
        gbc.gridy = 8;
        kembaliloginButton.addActionListener(e -> kembali());
        add(kembaliloginButton, gbc);
  }

  //Navigasi 
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
  String tipeBarang = (String) tipeBarangComboBox.getSelectedItem();
  String stok = stokField.getText().trim();
  String harga = hargaField.getText().trim();

  // Validasi input
  if (idBarang.isEmpty() || namaBarang.isEmpty() || tipeBarang.isEmpty() || stok.isEmpty() || harga.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Harap isi semua kolom!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
  }

  try {
      int stokInt = Integer.parseInt(stok);
      double hargaDouble = Double.parseDouble(harga);

      // Menulis data ke file
      try (BufferedWriter writer = new BufferedWriter(new FileWriter("txt\\Barang.txt", true))) {
          writer.write(idBarang + "," + namaBarang + "," + tipeBarang + "," + stokInt + "," + hargaDouble);
          writer.newLine();
      }

      // Reset form setelah berhasil
      idBarangField.setText("");
      namaBarangField.setText("");
      tipeBarangComboBox.setSelectedIndex(0);
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