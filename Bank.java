public class Bank implements Pembayaran {
    @Override
    public void bayar(int jumlah) {
        System.out.println("Pembayaran melalui Bank sebesar Rp" + jumlah + " berhasil diproses");
    }
}