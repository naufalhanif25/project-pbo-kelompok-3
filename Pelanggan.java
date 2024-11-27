public class Pelanggan {
    private String username;
    private Keranjang keranjang = new Keranjang();

    public Pelanggan(String username) {
        this.username = username;
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }

    @Override
    public String toString() {
        return "Pelanggan: " + username;
    }
}

