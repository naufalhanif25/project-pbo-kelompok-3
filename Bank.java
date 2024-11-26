public class Bank implements Pembayaran {
    @Override
    public void bayar(double total) {
        System.out.println("Pembayaran melalui Bank sebesar Rp" + total + " berhasil diproses");
    }
}