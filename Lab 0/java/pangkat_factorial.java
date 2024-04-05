import java.util.Scanner;

public class pangkat_factorial {
    public static void main(String[] args) {
        // taking input from user
        Scanner inp = new Scanner(System.in);
        System.out.println("Masukkan bilangan utama (n): ");
        int n = inp.nextInt();
        System.out.println("Masukkan pemangkatan (m): ");
        int m = inp.nextInt();
        inp.close();

        // factorial function
        int hasilFactorial = 1;
        int tempN = n;
        while (tempN >= 1) {
            hasilFactorial *= tempN;
            tempN--;
        }

        // exponential function
        int hasilPangkat = 1;
        for (int i = 0; i < m; i++) {
            hasilPangkat *= n;
        }

        // printing the results
        System.out.println("n factorial = " + hasilFactorial);
        System.out.println("n pangkat m = " + hasilPangkat);
    }
}
