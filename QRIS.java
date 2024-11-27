public class QRIS implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Metode pembayaran: QRIS");
        System.out.println("Silakan scan QRIS untuk membayar sebesar Rp" + amount);
    }
}
