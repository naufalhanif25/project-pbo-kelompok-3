public class BankPayment implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Pembayaran melalui Bank Transfer sebesar Rp" + amount + " berhasil diproses.");
    }
}
