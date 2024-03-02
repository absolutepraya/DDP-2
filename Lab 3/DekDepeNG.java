import java.io.*;
import java.util.StringTokenizer;

/**
 * DekDepeNG
 */
public class DekDepeNG {
    
    private static InputReader in = new InputReader(System.in);
    private static OutputStream outputStream = System.out;
    private static PrintWriter out = new PrintWriter(outputStream); 
    private static Dosen[] listDosen;
    private static Siswa[] listSiswa;
    private static MataKuliah[] listMataKuliah;

    public static void main(String[] args) {
        // Input jumlah dosen, diikuti pembuatan listDosen dan listMataKuliah
        int jumlahDosen = in.nextInt();
        listDosen = new Dosen[jumlahDosen];
        listMataKuliah = new MataKuliah[jumlahDosen];

        // Iterasi input jumlah dosen
        for (int i = 0; i < jumlahDosen; i++){
            // Input ID dosen, mata kuliah, dan kapasitas
            String idDosen = in.next();
            String kodeMatkul = in.next();
            int kapasitas = in.nextInt();

            // Membuat object MataKuliah
            MataKuliah mataKuliah = new MataKuliah(kodeMatkul, kapasitas);
            listMataKuliah[i] = mataKuliah; // Menambahkan object MataKuliah ke listMataKuliah

            // Membuat object Dosen
            Dosen dosen = new Dosen(idDosen, mataKuliah);
            listDosen[i] = dosen; // Menambahkan object Dosen ke listDosen
        }
        
        // Input jumlah siswa, diikuti pembuatan listSiswa
        int jumlahSiswa = in.nextInt();
        listSiswa = new Siswa[jumlahSiswa];

        // Iterasi input jumlah siswa
        for (int i = 0; i < jumlahSiswa; i++){
            // Input NPM
            String npm = in.next();

            // Membuat object Siswa
            Siswa siswa = new Siswa(npm);
            listSiswa[i] = siswa; // Menambahkan object Siswa ke listSiswa
        }

        // Input jumlah perintah yang akan dijalankan
        int jumlahPerintah = in.nextInt();

        // Iterasi input jumlah perintah
        for(int i = 0; i < jumlahPerintah; i++){
            // Input perintah
            String perintah = in.next();

            // Switch sebagai control flow perintah
            switch (perintah) {
                case "BERINILAI": {
                    // Input ID dosen, NPM, dan nilai
                    String idDosen = in.next();
                    String npm = in.next();
                    int nilai = in.nextInt();

                    // Memanggil fungsi beriNilai
                    beriNilai(idDosen, npm, nilai);
                    break;
                }

                case "CEKNILAI": {
                    // Input NPM
                    String npm = in.next();

                    // Memanggil fungsi cekNilai
                    cekNilai(npm);
                    break;
                }

                case "AMBILMATKUL": {
                    // Input NPM dan kode mata kuliah
                    String npm = in.next();
                    String kodeMatkul = in.next();

                    // Memanggil fungsi ambilMatkul untuk siswa yang memiliki NPM sesuai input dan mata kuliah yang memiliki kode sesuai input
                    for (Siswa siswa : listSiswa) {
                        if (siswa.getNpm().equals(npm)) {
                            for (MataKuliah mataKuliah : listMataKuliah) {
                                if (mataKuliah.getKodeMatkul().equals(kodeMatkul)) {
                                    out.println(siswa.ambilMatkul(mataKuliah));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        System.out.println();
        out.close();
    }

    public static void beriNilai(String idDosen, String npm, int nilai) {
        // Memanggil fungsi beriNilai pada class Dosen
        String output = "";
        for (Dosen dosen : listDosen) {
            if (dosen.getIdDosen().equals(idDosen)) {
                output = dosen.beriNilai(npm, nilai);
                break;
            }
        }
        out.println(output); 
    }

    public static void cekNilai(String npm) {
        // Mencari siswa dengan NPM sesuai input
        Siswa siswa = null;
        for (Siswa s : listSiswa) {
            if (s.getNpm().equals(npm)) {
                siswa = s;
                break;
            }
        }
        out.println(siswa.tampilkanNilai());
    }

    
/* ——————————————————————————————————————————————————————————————————————— */


    // taken from https://codeforces.com/submissions/Petr
    // together with PrintWriter, these input-output (IO) is much faster than the usual Scanner(System.in) and System.out
    // please use these classes to avoid your fast algorithm gets Time Limit Exceeded caused by slow input-output (IO)
    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}