import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

public class SofitaGrader {
    static Scanner sc = new Scanner(System.in);
    static File direktoriUtama = new File(".");

    public static void main(String[] args) {
        try {
            System.out.println("Welcome to SOFITA GRADER!");
            while (true) {
                printWelcomingMsg();
                System.out.print("Input: ");
                int actionCode = sc.nextInt();
                switch (actionCode) {
                    case 1:
                        buatQuiz();
                        break;
                    case 2:
                        jawabQuiz();
                        break;
                    case 3:
                        nilaiQuiz();
                        break;
                    case 10:
                        System.out.println("Terima kasih sudah memakai SOFITA GRADER!");
                        sc.close();
                        return;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Terjadi kesalahan saat menjalankan program");
        }
    }
    
    public static void printWelcomingMsg() {
        System.out.println("=".repeat(64));
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Buat Quiz baru");
        System.out.println("[2] Input Jawaban Quiz");
        System.out.println("[3] Nilai Jawaban Quiz");
        System.out.println("[10] Exit");
        System.out.println("=".repeat(64));
    }

    public static void buatQuiz() {
        System.out.println("\n---BUAT QUIZ---");

        // Create a new folder for the quiz
        File folderQuiz = makeFile();
        if (folderQuiz == null) {
            return;
        }

        // Create the answer key for the quiz
        makeKJ(folderQuiz);
    }

    public static void jawabQuiz() {
        System.out.print("\n---JAWAB QUIZ---");
        
        // Choose the folder to access
        File folderQuiz;
        try {
            folderQuiz = aksesFolder();
        } catch (InvalidQuizException e) {
            System.out.println(e.getMessage());
            return;
        }
        if (folderQuiz == null) {
            return;
        }

        // Input the answers for the quiz
        makeJawaban(folderQuiz);
    }

    public static void nilaiQuiz() {
        System.out.print("\n---NILAI QUIZ---");

        try {
            // Choose the folder to access
            File pilihFolder = aksesFolder();
            if (pilihFolder == null) {
                return;
            }
    
            // Find the previous rekap nilai file and delete it
            File rekapSebelumnya = findFile(pilihFolder, String.format("Nilai Rekap %s.txt", pilihFolder.getName()));
            if (rekapSebelumnya != null){
                rekapSebelumnya.delete();
                System.out.println("\n-------------------------------------");
                System.out.println("| ! Nilai Rekap akan di-overwrite ! |");
                System.out.println("-------------------------------------");
            }
        
            File[] files = pilihFolder.listFiles();
            if (files.length == 1) {
                System.out.println("\nBelum ada yang input jawaban");
                return;
            }
            System.out.printf("Isi Rekap Nilai %s:\n", pilihFolder.getName());
        
            File kjQuiz = findFile(pilihFolder, String.format("KJ %s.txt", pilihFolder.getName()));
        
            BufferedWriter writer = null;
            try {
                // Create the file for the rekap nilai
                File rekapNilai = new File(pilihFolder, String.format("Nilai Rekap %s.txt", pilihFolder.getName()));
                writer = new BufferedWriter(new FileWriter(rekapNilai));
    
                // Iterate through the files in the folder to grade the students' answers
                for (File file : files) {
                    if (file.getName().equals(rekapNilai.getName()) || file.getName().equals(kjQuiz.getName())) {
                        continue;
                    }
                    // Count the number of matching lines between the student's answers and the answer key
                    int matchingLines = countMatchingLines(file, kjQuiz);
                    // Calculate the student's score and create the string to write to the rekap nilai file
                    double score = (double) matchingLines / hitungSoal(kjQuiz) * 100;
                    String rekapPerLine = file.getName().replace(".txt", ": ") + String.format("%.2f", score) + "%";
                    System.out.println(rekapPerLine);
                    // Write the student's name and score to the rekap nilai file
                    writer.write(rekapPerLine);
                    writer.newLine();
                }
                System.out.println();
            } catch (IOException e) {
                System.out.println("Terjadi kesalahan saat menghitung nilai");
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    System.out.println("Terjadi kesalahan saat menutup file");
                }
            }
        } catch (InvalidQuizException e) {
            System.out.println(e.getMessage());
            return;
        }
    }
    
///////////////////////////////////////////////////////////////////////////////

    public static File makeFile() {
        String inputNama;
        while (true) {
            System.out.print("Masukkan nama folder baru: ");
            sc.nextLine();
            inputNama = sc.nextLine();
            File contents[] = direktoriUtama.listFiles();

            for (File file : contents) {
                if (file.getName().equals(inputNama)){
                    return file;
                }
            }

            // Use while + if to validate folder creation (if there is an unwanted symbol in the folder name)
            if (inputNama.contains("/") || inputNama.contains("\\") || inputNama.contains(":") || inputNama.contains("*") || inputNama.contains("?") || inputNama.contains("\"") || inputNama.contains("<") || inputNama.contains(">") || inputNama.contains("|")) {
                System.out.println("Input tidak valid. Masukkan nama folder yang valid.\n");
                System.out.print("Masukkan nama folder baru: ");
                inputNama = sc.nextLine();
            } else {
                break;
            }
        }
        File folderBaru = new File(inputNama);
        folderBaru.mkdir();
        System.out.printf("Berhasil buat folder dengan nama %s\n\n", inputNama);
        return folderBaru;
    }

    public static void makeKJ(File folderQuiz) {
        // HINT: You might want to utilize File.createNewFile() and BufferedWriter to write the answers to a file

        // Number of problems input for the quiz
        System.out.println("Silahkan input KJ untuk " + folderQuiz.getName());
        System.out.print("Jumlah soal: ");
        int jumlahSoal = sc.nextInt();
        sc.nextLine();

        // File name for the answer key
        String kjFileName = String.format("KJ %s.txt", folderQuiz.getName());
        File kjFile = new File(folderQuiz, kjFileName);

        // Iterating through the number of problems to input the answers
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(kjFile));
            String jawaban;
            for (int i = 1; i <= jumlahSoal; i++) {
                // Input validation for the answer key
                while (true) {
                    System.out.printf("%d. ", i);
                    jawaban = sc.nextLine();
                    if (jawaban.equals("A") || jawaban.equals("B") || jawaban.equals("C") || jawaban.equals("D")) {
                        break;
                    } else {
                        System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                        continue;
                    }
                }
                // Writing the answer key to the file
                writer.write(i + ". " + jawaban);
                writer.newLine();
            }
            writer.close();
            System.out.println("Berhasil buat file " + kjFileName + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membuat KJ");
        }
    }

    public static void makeJawaban(File folderQuiz) {
        // Input the name of the student
        System.out.print("\nMasukkan nama murid: ");
        String namaMurid = sc.nextLine();
        System.out.println("Masukkan jawaban:");

        // Find out how many questions are in the quiz
        File soalFile = findFile(folderQuiz, String.format("KJ %s.txt", folderQuiz.getName()));
        int jumlahSoal;
        try {
            jumlahSoal = hitungSoal(soalFile);
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menghitung soal");
            return;
        }

        // Find the previous answer file and delete it
        File jawabanSebelumnya = findFile(folderQuiz, String.format("%s.txt", namaMurid));
        if (jawabanSebelumnya != null) {
            jawabanSebelumnya.delete();
            System.out.println("-------------------------------------");
            System.out.println("|  !  Jawaban akan di-overwrite  !  |");
            System.out.println("-------------------------------------");
        }

        // Create the file, with the file name being the student's name
        File jawabanFile = new File(folderQuiz, String.format("%s.txt", namaMurid));

        // Iterating through the number of problems to input the answers
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(jawabanFile));
            String jawaban;
            for (int i = 1; i <= jumlahSoal; i++) {
                // Input validation for the answer key
                while (true) {
                    System.out.printf("%d. ", i);
                    jawaban = sc.nextLine();
                    if (jawaban.equals("A") || jawaban.equals("B") || jawaban.equals("C") || jawaban.equals("D")) {
                        break;
                    } else {
                        System.out.println("Input tidak valid. Masukkan A, B, C, atau D.");
                        continue;
                    }
                }
                // Writing the answer key to the file
                writer.write(i + ". " + jawaban);
                writer.newLine();
            }
            writer.close();
            System.out.println("Berhasil buat file " + jawabanFile.getName() + "\n");
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat membuat jawaban");
        }
    }

    public static File aksesFolder() throws InvalidQuizException {
        // Print the list of folders in the current directory
        System.out.println("\nBerikut adalah daftar folder yang ada: ");
        System.out.println("-".repeat(29));
        printFiles(direktoriUtama);
        System.out.println("-".repeat(29));
        
        // Count the number of folders in the current directory
        File[] contents = direktoriUtama.listFiles();
        int folderCount = 0;
        for (File file : contents) {
            if (!file.getName().endsWith(".java")) {
                folderCount++;
            }
        }
        if (folderCount == 0) {
            return null;
        }

        // Ask the user to choose a folder
        sc.nextLine(); // Clear the scanner buffer
        while (true) {
            System.out.print("Pilih nama folder untuk diakses: ");
            String folderName = sc.nextLine();
    
            // Find the folder in the current directory
            File selectedFolder = null;
            try {
                selectedFolder = findFile(direktoriUtama, folderName);
                if (selectedFolder == null) {
                    throw new InvalidQuizException("Input tidak valid. Masukkan nama folder yang valid.\n");
                }
                return selectedFolder;
            } catch (InvalidQuizException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public static File findFile(File selectedFolder, String fileName) {
        // Find the file with the given name in the selected folder
        File[] files = selectedFolder.listFiles();
        for (File file : files) {
            if (file.getName().equals(fileName)) {
            return file;
            }
        }
        return null;
    }
    
    public static int countMatchingLines(File file, File kjFile) {
        // Count the number of matching lines between the two files
        int matchingLines = 0;
        try {
            // Create buffered readers for the two files
            BufferedReader reader1 = new BufferedReader(new FileReader(file));
            BufferedReader reader2 = new BufferedReader(new FileReader(kjFile));
            // Read the first line from each file
            String line1 = reader1.readLine();
            String line2 = reader2.readLine();
            // Compare the lines until the end of one of the files is reached
            while (line1 != null && line2 != null) {
                if (line1.equals(line2)) {
                    matchingLines++;
                }
                line1 = reader1.readLine();
                line2 = reader2.readLine();
            }
            reader1.close();
            reader2.close();
        } catch (IOException e) {
            System.out.println("Terjadi kesalahan saat menghitung jawaban benar");
        }

        return matchingLines;
    }

    /**
     * Prints the names of all files in the given folder that do not have a ".java" extension.
     *
     * @param folderName the folder to search for files
     */
    
    public static void printCurrentDirectory() {
        printFiles(direktoriUtama);
    }

    /**
     * Prints the names of all files in the given folder that have a ".java" extension.
     *
     * @param folderName the folder to search for files
     */
    public static void printFiles(File folderName) {
        File contents[] = folderName.listFiles();
        boolean found = false;
        String fileListString = "";

        for (File file : contents) {
            if (!file.getName().endsWith(".java")){
                fileListString += "> " + file.getName() + "\n";
                found = true;
            }
        }

        if (!found) {
            System.out.println("Belum ada folder yang dibuat!");
        } else {
            // Remove the last newline character
            fileListString = fileListString.substring(0, fileListString.length() - 1);
            System.out.println(fileListString);
        }
    }

    /**
     * Calculates the number of questions in a given file.
     * 
     * @param file the file containing the questions
     * @return the number of questions in the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static int hitungSoal(File file) throws IOException {
        Scanner reader = new Scanner(file);
        int soalCount = 0;
        while (reader.hasNextLine()) {
            reader.nextLine();
            soalCount++;
        }
        reader.close();
        return soalCount;
    }
}
