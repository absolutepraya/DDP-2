import java.util.Scanner;

public class nilai {
    public static void main(String[] args) {
        // taking input from user
        Scanner inp = new Scanner(System.in);
        System.out.println("Masukkan Nama Mahasiswa: ");
        String nama = inp.nextLine();
        System.out.println("Masukkan Nilai Asli: ");
        double nilaiAsli = inp.nextDouble();
        System.out.println("Masukkan Durasi: ");
        double durasi = inp.nextDouble();
        inp.close();

        // declaring nilaiAkhir variable
        double nilaiAkhir;

        // condition to calculate nilaiAkhir
        if (durasi < 60) {
            nilaiAkhir = 1.2 * nilaiAsli;
        } else if (60 <= durasi && durasi <= 70) {
            nilaiAkhir = nilaiAsli;
        } else if (70 < durasi && durasi < 90) {
            nilaiAkhir = 0.75 * nilaiAsli;
        } else if (90 <= durasi && durasi <= 100) {
            nilaiAkhir = 0.5 * nilaiAsli;
        } else {
            nilaiAkhir = 0.2 * nilaiAsli;
        }

        // printing nilaiAkhir
        System.out.println(nama + " mendapatkan nilai akhir " + nilaiAkhir);
    }
}