public class Barang {
    private String id;
    private String nama;
    private int harga;

    public Barang(String id, String nama, int harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + nama + ", Harga: Rp" + harga;
    }
}
