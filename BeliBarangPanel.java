import javax.swing.*;
import java.awt.*;

public class BeliBarangPanel extends JPanel{
  //Untuk private
  private JComboBox<String> metodeBayarBox;

  public BeliBarangPanel(){
    setLayout(new GridBagLayout());
    setOpaque(true);
    setBackground(Color.DARK_GRAY);
    addComponents();

  }
  //Style Label
  private void styleLabel(JLabel Label) {
    Label.setFont(new Font("Arial", Font.BOLD, 16)); 
    Label.setForeground(Color.DARK_GRAY); 
}

  //Style Button
  private void styleButton(JButton button) {
    button.setFont(new Font("Arial", Font.BOLD, 14));
    button.setBackground(new Color(58, 123, 245));
    button.setForeground(Color.WHITE);
    button.setFocusPainted(false);
    button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    button.setPreferredSize(new Dimension(200, 40));
  }

  @Override
  protected void paintComponent(Graphics g){
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g.create();
    
    //Latar Belakang gradasi
    GradientPaint gradient = new GradientPaint (0,0, new Color(0,0,139),
     0, getHeight(), new Color(0,255,255));
     g2d.setPaint(gradient);
     g2d.fillRect(0, 0, getWidth(), getHeight());

     // Panel utama dengan efek rounded
     g2d.setColor(new Color(255, 255, 255, 230)); 
     int panelWidth = 400;
     int panelHeight = 500;
     int x = (getWidth() - panelWidth) / 2;
     int y = (getHeight() - panelHeight) / 2;
     g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

     g2d.dispose();
     
  }
  private void addComponents(){
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10); 
    gbc.fill = GridBagConstraints.HORIZONTAL;

    //Icon Logo
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

      
  //
   JLabel metodeBayarLabel = new JLabel("Metode Pembayaran:");
        styleLabel(metodeBayarLabel);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(metodeBayarLabel, gbc);

        // ComboBox untuk metode pembayaran
        metodeBayarBox = new JComboBox<>(new String[]{"QRIS", "BANK", "COD"});
        metodeBayarBox.setFont(new Font("Arial", Font.PLAIN, 14));
        metodeBayarBox.setPreferredSize(new Dimension(200, 35));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(metodeBayarBox, gbc);

        // Tombol Bayar
        JButton bayarButton = new JButton("Bayar");
        styleButton(bayarButton);
        bayarButton.addActionListener(e -> prosesPembayaran());
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(bayarButton, gbc);

        //Tombol Kembali KeLogin
        JButton kembaliloginButton = new JButton("Back");
        styleButton(kembaliloginButton);
        gbc.gridx = 0;
        gbc.gridy = 4;
        kembaliloginButton.addActionListener(e -> kembali());
        add(kembaliloginButton, gbc);
    }
    //
    private void kembali(){
      JFrame kembali = (JFrame) SwingUtilities.getWindowAncestor(this);
      if (kembali != null) {
          kembali.dispose();
          //Navigasi Tombol
          kembali.setContentPane(new Dashboard("Pelanggan"));
      }
  }

    // Logika untuk proses pembayaran
    private void prosesPembayaran() {
        String metode = (String) metodeBayarBox.getSelectedItem();
        JOptionPane.showMessageDialog(this,
                "Metode pembayaran yang dipilih: " + metode,
                "Informasi Pembayaran",
                JOptionPane.INFORMATION_MESSAGE);

        // Reset pilihan setelah pembayaran
        metodeBayarBox.setSelectedIndex(0);
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