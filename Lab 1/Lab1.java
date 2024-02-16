import java.util.Scanner;

public class Lab1 {
    public static void main(String[] args) {
        // Deklarasi variabel
        int jumlahMatkul;
        int sksMatkul;
        double nilaiMatkul;
        double ipSemester;
        double ipKumulatif;

        Scanner inp = new Scanner(System.in);

        // Validasi jumlah mata kuliah menggunakan while loop
        while (true) {
            // Input jumlah mata kuliah
            System.out.println("Masukkan jumlah mata kuliah: ");
            jumlahMatkul = inp.nextInt();

            // Validasi jumlah mata kuliah
            if (jumlahMatkul < 0) {
                System.out.println("Jumlah mata kuliah yang diambil tidak dapat negatif, silahkan isi kembali.\n");
                continue;
            }
        
            inp.nextLine(); // Clear buffer
            System.out.println();
            break;
        }

        // Deklarasi variabel-variabel mutu dan SKS, yang total dan yang lulus
        double mutu = 0.0;
        double sks = 0.0;
        double mutuLulus = 0.0;
        double sksLulus = 0.0;

        // Input mata kuliah menggunakan for loop
        for (int i = 0; i < jumlahMatkul; i++) {
            // Input nama mata kuliah
            System.out.println("Masukkan nama mata kuliah ke-" + (i + 1) + ": ");
            String matkul = inp.nextLine();

            // While loop untuk validasi jumlah SKS
            while (true) {
                // Input jumlah SKS
                System.out.println("Masukkan jumlah SKS: ");
                sksMatkul = inp.nextInt();
                // Validasi jumlah SKS
                if (sksMatkul <= 0) {
                    System.out.println("Jumlah SKS mata kuliah yang diambil tidak dapat negatif atau 0, silahkan isi kembali.");
                    continue;
                }
                break;
            }

            // While loop untuk validasi nilai mata kuliah
            while (true) {
                // Input nilai mata kuliah
                System.out.println("Masukkan nilai: ");
                nilaiMatkul = inp.nextDouble();
                // Validasi nilai mata kuliah
                if (nilaiMatkul < 0 || nilaiMatkul > 100) {
                    System.out.println("Nilai mata kuliah yang diambil tidak dapat negatif atau lebih dari 100, silahkan isi kembali.");
                    continue;
                }
                break;
            }

            // Deklarasi variabel nilai huruf dan mutu mata kuliah untuk dihitung setelah ini
            String nilaiHuruf;
            double mutuMatkul;

            // Menghitung mutu dan SKS
            sks += sksMatkul;
            if (nilaiMatkul >= 85) { // A
                mutuMatkul = 4.0 * sksMatkul;
                mutu += mutuMatkul;
                mutuLulus += mutuMatkul;
                sksLulus += sksMatkul;
                nilaiHuruf = "A";
            } else if (nilaiMatkul >= 80) { // A-
                mutuMatkul = 3.7 * sksMatkul;
                mutu += mutuMatkul;
                mutuLulus += mutuMatkul;
                sksLulus += sksMatkul;
                nilaiHuruf = "A-";
            } else if (nilaiMatkul >= 75) { // B+
                mutuMatkul = 3.3 * sksMatkul;
                mutu += mutuMatkul;
                mutuLulus += mutuMatkul;
                sksLulus += sksMatkul;
                nilaiHuruf = "B+";
            } else if (nilaiMatkul >= 70) { // B
                mutuMatkul = 3.0 * sksMatkul;
                mutu += mutuMatkul;
                mutuLulus += mutuMatkul;
                sksLulus += sksMatkul;
                nilaiHuruf = "B";
            } else if (nilaiMatkul >= 65) { // B-
                mutuMatkul = 2.7 * sksMatkul;
                mutu += mutuMatkul;
                mutuLulus += mutuMatkul;
                sksLulus += sksMatkul;
                nilaiHuruf = "B-";
            } else if (nilaiMatkul >= 60) { // C+
                mutuMatkul = 2.3 * sksMatkul;
                mutu += mutuMatkul;
                mutuLulus += mutuMatkul;
                sksLulus += sksMatkul;
                nilaiHuruf = "C+";
            } else if (nilaiMatkul >= 55) { // C
                mutuMatkul = 2.0 * sksMatkul;
                mutu += mutuMatkul;
                mutuLulus += mutuMatkul;
                sksLulus += sksMatkul;
                nilaiHuruf = "C";
            } else if (nilaiMatkul >= 40) { // D
                mutuMatkul = sksMatkul;
                mutu += mutuMatkul;
                nilaiHuruf = "D";
            } else { // E
                mutuMatkul = 0.0;
                nilaiHuruf = "E";
            }
            
            // Menampilkan rangkuman mata kuliah
            System.out.println("Nilai huruf mata kuliah " + matkul + " adalah " + nilaiHuruf + " dengan mutu " + String.format("%.2f",mutuMatkul) + "\n");
            
            inp.nextLine(); // Clear buffer
        }

        // Menghitung IP Kumulatif, jika sks = 0, maka IPS = 0
        ipSemester = (sks == 0) ? 0 : mutu / sks;
        // Menghitung IP Kumulatif, jika sksLulus = 0, maka IPK = 0
        ipKumulatif = (sksLulus == 0) ? 0 : mutuLulus / sksLulus;

        // Menampilkan semua hasil perhitungan
        System.out.println("Jumlah mutu: " + String.format("%.2f", mutu));
        System.out.println("Jumlah SKS daimbil: " + sks);
        System.out.println("IP Semester: " + String.format("%.2f",ipSemester));
        System.out.println("Jumlah mutu lulus: " + String.format("%.2f",mutuLulus));
        System.out.println("Jumlah SKS lulus: " + sksLulus);
        System.out.println("IP Kumulatif: " + String.format("%.2f",ipKumulatif));

        inp.close();
    }
}
