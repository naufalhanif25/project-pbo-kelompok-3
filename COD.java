public class COD implements Pembayaran {
    @Override
    public void bayar(int jumlah) {
        System.out.println("Pembayaran melalui COD sebesar Rp" + jumlah + " berhasil diproses");
    }
}