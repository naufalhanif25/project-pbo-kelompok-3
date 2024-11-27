
import javax.swing.*;
import java.awt.*;

public class BankPanel extends JPanel {
    private double totalHarga;

    public BankPanel() {
        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
    }

    public BankPanel(double totalHarga) {
        this.totalHarga = totalHarga;

        setLayout(new GridBagLayout());
        setOpaque(true);
        setBackground(Color.DARK_GRAY);
        addComponents();
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
        int panelWidth = 320;
        int panelHeight = 360;
        int x = (getWidth() - panelWidth) / 2;
        int y = (getHeight() - panelHeight) / 2;
        g2d.fillRoundRect(x, y, panelWidth, panelHeight, 20, 20);

        g2d.dispose();
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Icon Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        ImageIcon logoIcon = ImageUtils.loadImageIcon("pict\\BANKRB.png", 120, 120); // Ganti path dengan logo Anda
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
        }
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridwidth = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        add(logoLabel, gbc);

        whiteSpace(1, 0);

        gbc = new GridBagConstraints();

        // Pesan Pembayaran
        JLabel messageLabel1 = new JLabel("Pembayaran melalui BANK");
        UIStyle.styleLabel(messageLabel1);
        gbc.gridx = 4;
        gbc.gridy = 2; // Posisi di bawah logo
        gbc.ipady = 8;
        gbc.gridwidth = 7;
        gbc.anchor = GridBagConstraints.CENTER;
        add(messageLabel1, gbc);

        JLabel messageLabel2 = new JLabel("sebesar Rp" + this.totalHarga + " berhasil");
        UIStyle.styleLabel(messageLabel2);
        gbc.gridy = 3; // Posisi di bawah pesan pertama
        gbc.ipady = 8;
        gbc.anchor = GridBagConstraints.CENTER;
        add(messageLabel2, gbc);

        gbc = new GridBagConstraints();
        
        whiteSpace(4, 20);

        JButton menuUtamaButton = new JButton("Menu Utama");
        UIStyle.styleButton(menuUtamaButton);
        gbc.gridx = 4;
        gbc.gridy = 5;
        gbc.gridwidth = 7;
        gbc.ipadx = -50;
        gbc.anchor = GridBagConstraints.CENTER; 
        menuUtamaButton.addActionListener(e -> pelanggan());
        add(menuUtamaButton, gbc);
    }

    private void pelanggan() {
        JFrame pelanggan= (JFrame) SwingUtilities.getWindowAncestor(this);
        if (pelanggan != null) {
            pelanggan.dispose();
            pelanggan.setContentPane(new Dashboard("Pelanggan"));
            pelanggan.revalidate();
            pelanggan.dispose();
        }
    }

    private void whiteSpace(int gridy, int ipady) {
        GridBagConstraints gbc = new GridBagConstraints();
        JLabel whiteSpace = new JLabel(" ");

        UIStyle.styleLabel(whiteSpace);
        gbc.gridx = 4;
        gbc.gridy = gridy;
        gbc.ipady = ipady;
        gbc.anchor = GridBagConstraints.CENTER;
        add(whiteSpace, gbc);
    }
}

