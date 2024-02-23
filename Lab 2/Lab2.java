import java.util.Scanner;

public class Lab2 {
    public static void main(String[] args) {
        // Input confession
        Scanner inp = new Scanner(System.in);
        System.out.print("Masukkan confession dalam bentuk kode (ketik 'selesai' untuk keluar): \n");

        String inpString; // Initialize variabel untuk input
        String outpString = ""; // Define variabel untuk output

        // Iterate setiap confession yang dipisahkan oleh new line
        while (true) {
            inpString = inp.nextLine();
            // Jika input adalah "selesai", break dari loop
            if (inpString.equals("selesai")) {
                break;
            }
            // Mengekstrak binary dari input
            String extracted = binaryExtract(inpString, 0);
            // Menambahkan hasil ekstraksi ke outpString
            outpString += (extracted + "\n");
        }

        // Output confession yang sudah diekstrak
        System.out.println("\nOutput: \n" + outpString);
        inp.close(); // Close scanner
    }

    // Recursive method untuk mengekstrak binary dari input
    public static String binaryExtract(String rawString, int index) {
        // Menghentikan rekursi jika index sudah melebihi panjang string atau rawString adalah null
        if (index >= rawString.length() || rawString == null) {
            return "";
        }
        // Mengekstrak binary sampai menemui karakter selain 0 atau 1
        String binary = "";
        while (index < rawString.length() && (rawString.charAt(index) == '0' || rawString.charAt(index) == '1')) {
            binary += rawString.charAt(index);
            index++;
        }
        // Memeriksa apakah ada binary string lain yang perlu diekstrak
        while (index < rawString.length() && rawString.charAt(index) != '0' && rawString.charAt(index) != '1') {
            index++;
        }
        // Jika ada, panggil rekursi
        if (index < rawString.length()) {
            return binaryToASCII(binary) + binaryExtract(rawString, index); // Menggunakan binaryToASCII
        } else {
            return binaryToASCII(binary); // Menggunakan binaryToASCII
        }
    }

    // Method untuk mengkonversi binary ke ASCII
    public static String binaryToASCII (String binary) {
        // Cek jika binary kosong
        if (binary.isEmpty()) {
            return "";
        }
        // Convert binary ke desimal
        int decimal = Integer.parseInt(binary, 2 /* Base 2 untuk binary */);
        // Convert desimal ke ASCII
        return Character.toString((char) decimal);
    }
}
