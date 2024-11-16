
  import javax.swing.*;

public class Createakun extends JFrame {
    public Createakun() {
      // Membuat JFrame untuk Create Account
      JFrame buatakun = new JFrame("Create Account");
      buatakun.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      buatakun.setExtendedState(JFrame.MAXIMIZED_BOTH);
      buatakun.setSize(800, 600);
      buatakun.setLocationRelativeTo(null);

      // Menambahkan CreateakunPanel ke JFrame
      buatakun.add(new CreateakunPanel());
      buatakun.setVisible(true);
    }

  }

