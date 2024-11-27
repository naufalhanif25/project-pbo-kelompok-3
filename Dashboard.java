
import javax.swing.*;


public class Dashboard extends JFrame {

    public Dashboard(String role) {
        setTitle("e-commerce");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Mengatur panel berdasarkan peran
        if (role.equalsIgnoreCase("Admin")) {
            setContentPane(new DashbroadPanelAdmin());
        } else if (role.equalsIgnoreCase("Pelanggan")) {
            setContentPane(new DashboardPanelPelanggan());
        } else {
            JOptionPane.showMessageDialog(this, "Role tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        setVisible(true);
        //Icon
        setIconImage(new ImageIcon("pict\\IconRB.png").getImage());
    }
}