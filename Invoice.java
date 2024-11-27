
import javax.swing.*;

public class Invoice extends JFrame {
    private String metodePembayaran;

    public Invoice(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;  // Menyimpan metode pembayaran yang dipilih

        setTitle("e-commerce");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 600);
        setLocationRelativeTo(null);

        //Icon
        setIconImage(new ImageIcon("pict\\IconRB.png").getImage());

        // Memanggil panel berdasarkan metode pembayaran yang dipilih
        JPanel paymentPanel = null;
        if (this.metodePembayaran.equals("QRIS")) {
            paymentPanel = new QrisPanel();  
        } else if (this.metodePembayaran.equals("BANK")) {
            paymentPanel = new BankPanel();  
        } else if (this.metodePembayaran.equals("COD")) {
            paymentPanel = new CodPanel();  
        }

        if (paymentPanel != null) {
            add(paymentPanel);  
        }

        setVisible(true);
    }
}