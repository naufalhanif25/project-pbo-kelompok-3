public class QRISPayment implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Pembayaran melalui QRIS sebesar Rp" + amount + " berhasil diproses.");
    }
}
