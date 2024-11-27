public class InvoiceF {
  private KeranjangF keranjang;
  private String pembayaran;

  public InvoiceF(KeranjangF keranjang, String pembayaran) {
      this.keranjang = keranjang;
      this.pembayaran = pembayaran;
  }

  // Get Pembayaran
  public String getPembayaran() {
      return pembayaran;
  }

  // Method untuk Menampilkan Invoice
  public void showInvoice() {
      // Menampilkan invoice dengan metode pembayaran
      System.out.println("========== Invoice ==========");
      System.out.println("User: " + keranjang.getUserId());
      System.out.println("Metode Pembayaran: " + getPembayaran());
      System.out.println("Total: " + getTotalHarga());

      // Tampilkan QRIS/Bank/COD sesuai dengan pembayaran yang dipilih
      if (getPembayaran().equals("Bank")) {
          System.out.println("Pembayaran dilakukan melalui Bank.");
          // Simulasi pembayaran melalui Bank
          BankF bank = new BankF();
          bank.bayar(getTotalHarga());
      } else if (getPembayaran().equals("COD")) {
          System.out.println("Pembayaran dilakukan dengan COD (Cash On Delivery).");
          // Simulasi pembayaran melalui COD
          CODF cod = new CODF();
          cod.bayar(getTotalHarga());
      } else if (getPembayaran().equals("QRIS")) {
          System.out.println("Pembayaran dilakukan melalui QRIS.");
          // Simulasi pembayaran melalui QRIS
          QRISF qris = new QRISF();
          qris.bayar(getTotalHarga());
      } else {
          System.out.println("Pembayaran tidak valid.");
      }
  }

  private double getTotalHarga() {
      // Menghitung total harga berdasarkan daftar barang yang dipilih
      double total = 0.0;
      for (Barang barang : keranjang.getListBarang()) {
          total += barang.getHarga();
      }
      return total;
  }
}
