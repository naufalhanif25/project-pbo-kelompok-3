import javax.swing.*;
import java.awt.*;

public class InvoicePanel extends JPanel{
  //Private
  public InvoicePanel(){
    setLayout(new GridBagLayout());
    setOpaque(true);
    setBackground(Color.DARK_GRAY);
    addComponents();

  }
  //Style Label
  protected void styleLabel(JLabel Label) {
    Label.setFont(new Font("Arial", Font.BOLD, 16)); 
    Label.setForeground(Color.DARK_GRAY); 
}

  //Style Button
  protected void styleButton(JButton button) {
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
        ImageIcon logoIcon = loadImageIcon("pict\\TransaksiRB.png", 100, 100); // Ganti path dengan logo Anda
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        } 
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);


  }
  
  //Untuk Icon Logo
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