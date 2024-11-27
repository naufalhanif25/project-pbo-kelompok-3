public class Bank implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Metode pembayaran: Bank Transfer");
        System.out.println("Pembayaran sebesar Rp" + amount + " harus ditransfer ke rekening kami.");
    }
}
