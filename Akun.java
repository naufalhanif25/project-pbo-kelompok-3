class Akun {
    private String id;
    private String password;

    // Konstruktor untuk membuat akun baru
    public Akun(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // Metode login untuk memeriksa kecocokan ID dan password
    public boolean login(String id, String password) {
        return this.id.equals(id) && this.password.equals(password);
    }

    // Metode untuk mengubah password akun
    public void setIdPass(String password) {
        this.password = password;
    }

    // Metode untuk membuat akun baru
    public static void createAkun(String id, String password) {
        // Menyimpan akun baru ke dalam daftar atau database dapat ditambahkan di sini
        System.out.println("Akun berhasil dibuat: " + id);
    }

    // Metode untuk menghapus akun
    public static void deleteAkun(String id, String password) {
        // Penghapusan akun, logika untuk menghapus data akun
        System.out.println("Akun berhasil dihapus: " + id);
    }

    // Getter untuk ID
    public String getId() {
        return id;
    }

    // Getter untuk password
    public String getPassword() {
        return password;
    }

    // Setter untuk ID
    public void setId(String id) {
        this.id = id;
    }

    // Setter untuk password
    public void setPassword(String password) {
        this.password = password;
    }
}
