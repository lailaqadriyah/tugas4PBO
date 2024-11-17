import java.util.Scanner;

// Kelas Barang (Parent Class)
class Barang {
    protected String kodeBarang;
    protected String namaBarang;
    protected double hargaBarang;

    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }

    public double getHargaBarang() {
        return hargaBarang;
    }

    public void displayBarang() {
        System.out.println("Kode Barang : " + kodeBarang);
        System.out.println("Nama Barang : " + namaBarang);
        System.out.println("Harga Barang: Rp " + hargaBarang);
    }
}

// Kelas Transaksi (Child Class)
class Transaksi extends Barang {
    private String noFaktur;
    private int jumlahBeli;
    private double totalHarga;

    public Transaksi(String noFaktur, String kodeBarang, String namaBarang, double hargaBarang, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang);
        this.noFaktur = noFaktur;
        this.jumlahBeli = jumlahBeli;
        this.totalHarga = calculateTotal();
    }

    public double calculateTotal() {
        return jumlahBeli * hargaBarang;
    }

    public void displayTransaksi() {
        System.out.println("\n===== Detail Transaksi =====");
        System.out.println("No Faktur    : " + noFaktur);
        displayBarang();
        System.out.println("Jumlah Beli  : " + jumlahBeli);
        System.out.println("Total Harga  : Rp " + totalHarga);
    }
}

// Kelas Main (Program Utama)
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lanjut;

        do {
            try {
                // Input data transaksi
                System.out.print("Masukkan No Faktur: ");
                String noFaktur = scanner.nextLine();

                System.out.print("Masukkan Kode Barang: ");
                String kodeBarang = scanner.nextLine();

                System.out.print("Masukkan Nama Barang: ");
                String namaBarang = scanner.nextLine();

                System.out.print("Masukkan Harga Barang: ");
                double hargaBarang = scanner.nextDouble();

                if (hargaBarang <= 0) {
                    throw new IllegalArgumentException("Harga barang harus lebih dari 0!");
                }

                System.out.print("Masukkan Jumlah Beli: ");
                int jumlahBeli = scanner.nextInt();

                if (jumlahBeli <= 0) {
                    throw new IllegalArgumentException("Jumlah beli harus lebih dari 0!");
                }

                // Membuat objek transaksi
                Transaksi transaksi = new Transaksi(noFaktur, kodeBarang, namaBarang, hargaBarang, jumlahBeli);
                
                // Menampilkan detail transaksi
                transaksi.displayTransaksi();

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            } finally {
                scanner.nextLine(); // Clear buffer untuk menghindari masalah input berikutnya
            }

            // Menanyakan apakah pengguna ingin melanjutkan
            System.out.print("\nApakah Anda ingin memasukkan transaksi lain? (ya/tidak): ");
            lanjut = scanner.nextLine();

        } while (lanjut.equalsIgnoreCase("ya"));

        scanner.close();
        System.out.println("Terima kasih telah menggunakan program ini.");
    }
}
