public class QRIS implements Pembayaran {
    @Override
    public void pay(int amount) {
        System.out.println("Pembayaran melalui QRIS sebesar Rp" + amount + " berhasil diproses.");
    }
}