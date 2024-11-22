public class COD implements Pembayaran {
    @Override
    public void pay(int amount) {
        System.out.println("Pembayaran melalui Cash on Delivery (COD) sebesar Rp" + amount + ".");
    }
}