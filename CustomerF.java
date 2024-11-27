public class CustomerF {
    private String id;
    private String pass;
    private KeranjangF keranjang;

    // Konstruktor dengan 2 parameter id dan pass
    public CustomerF(String id, String pass) {
        this.id = id;
        this.pass = pass;
        this.keranjang = new KeranjangF(id);
    }

    // Konstruktor dengan hanya 1 parameter id, pass akan menggunakan default kosong
    public CustomerF(String id) {
        this.id = id;
        this.pass = "";  // Default password, bisa diubah sesuai kebutuhan
        this.keranjang = new KeranjangF(id);
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    // Method untuk menambahkan barang ke keranjang
    public void addBarang(Barang barang) {
        keranjang.addBarang(barang);
    }

    // Method untuk checkout dan menghasilkan invoice
    public void checkout(String pembayaran) {
        InvoiceF invoice = new InvoiceF(keranjang, pembayaran);  // Menggunakan InvoiceF
        invoice.showInvoice();  // Menampilkan invoice
    }
}
