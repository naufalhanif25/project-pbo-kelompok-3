import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class TulisTransaksi {
  public static void tulisTransaksi(String IdTransaksi, String UserName,String Tanggal,String Keterangan, String Jumlah, String MetodePembayaran){
    String data = String.join(",", IdTransaksi, UserName, Tanggal, Keterangan, Jumlah, MetodePembayaran);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("Transaksi.txt"))){
      writer.write(data);
      writer.newLine();
      JOptionPane.showMessageDialog(null, "Transaksi Berhasil Disimpan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e){
      JOptionPane.showMessageDialog(null, "Gagal menyimpan transaksi!", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
  
}
