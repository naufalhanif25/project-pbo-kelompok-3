public class Invoice {
    private Keranjang keranjang;
    private String pembayaran;

    public Invoice(Keranjang keranjang, String pembayaran) {
        this.keranjang = keranjang;
        this.pembayaran = pembayaran;
    }

    public void showInvoice() {
        System.out.println("======= Invoice =======");
        keranjang.showKeranjang();
        System.out.println("Total Harga: Rp" + keranjang.calculateHarga());
        System.out.println("Pembayaran: " + pembayaran);
    }
}