public class COD implements PaymentMethod {
    @Override
    public void pay(int amount) {
        System.out.println("Metode pembayaran: COD (Cash on Delivery)");
        System.out.println("Pesanan Anda akan dikirimkan, dan pembayaran sebesar Rp" + amount + " dilakukan saat barang diterima.");
    }
}
