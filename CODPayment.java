public class CODPayment implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Pembayaran melalui Cash on Delivery (COD) sebesar Rp" + amount + ".");
    }
}
