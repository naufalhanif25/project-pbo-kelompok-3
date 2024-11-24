public class COD implements Pembayaran {
    @Override
    public void bayar(double total) {
        System.out.println("Pembayaran melalui COD sebesar Rp" + total + " berhasil diproses");
    }
}