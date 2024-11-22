public class Product {
    private int id;
    private String name;
    private int price; // Harga seharusnya int, bukan double

    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price; // Pastikan pengembalian tipe adalah int
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nama: " + name + ", Harga: Rp" + price;
    }
}
