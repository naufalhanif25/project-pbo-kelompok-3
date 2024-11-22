public class QRIS implements Pembayaran {
    @Override
    public void bayar(int jumlah) {
        System.out.println("Pembayaran melalui QRIS sebesar Rp" + jumlah + " berhasil diproses");
    }
}