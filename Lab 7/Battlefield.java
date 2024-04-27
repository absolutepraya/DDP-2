import java.util.Scanner;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

public class Battlefield {
    WarriorList<Warrior> warriorList = new WarriorList<>();

    private Scanner scanner = new Scanner(System.in);

    public void runMenu() {

        while (true) {
            // Menu utama
            System.out.println("\nWelcome to the Battlefield Simulator!");
            System.out.println("1. Add Warrior");
            System.out.println("2. Display Warriors");
            System.out.println("3. Simulate Battle");
            System.out.println("4. Revive Warrior");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addWarrior();
                    break;
                case 2:
                    displayWarriors();
                    break;
                case 3:
                    simulateBattle();
                    break;
                case 4:
                    revive();
                    break;
                case 5:
                    System.out.println("--------Game Over--------");
                    System.out.println("░░░░░░░█▐▓▓░████▄▄▄█▀▄▓▓▓▌█");
                    System.out.println("░░░░░▄█▌▀▄▓▓▄▄▄▄▀▀▀▄▓▓▓▓▓▌█");
                    System.out.println("░░░▄█▀▀▄▓█▓▓▓▓▓▓▓▓▓▓▓▓▀░▓▌█");
                    System.out.println("░░█▀▄▓▓▓███▓▓▓███▓▓▓▄░░▄▓▐█");
                    System.out.println("░█▌▓▓▓▀▀▓▓▓▓███▓▓▓▓▓▓▓▄▀▓▓▐█");
                    System.out.println("▐█▐██▐░▄▓▓▓▓▓▀▄░▀▓▓▓▓▓▓▓▓▓▌█▌");
                    System.out.println("█▌███▓▓▓▓▓▓▓▓▐░░▄▓▓███▓▓▓▄▀▐█");
                    System.out.println("█▐█▓▀░░▀▓▓▓▓▓▓▓▓▓██████▓▓▓▓▐█");
                    System.out.println("▌▓▄▌▀░▀░▐▀█▄▓▓██████████▓▓▓▌█▌");
                    System.out.println("▌▓▓▓▄▄▀▀▓▓▓▀▓▓▓▓▓▓▓▓█▓█▓█▓▓▌█▌");
                    System.out.println("█▐▓▓▓▓▓▓▄▄▄▓▓▓▓▓▓█▓█▓█▓█▓▓▓▐█");
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, 4, or 5.");
            }
        }
    }

    // Method untuk tambah warrior ke Arraylist
    private void addWarrior() {
        // Minta tipe warrior
        System.out.println();
        System.out.println("Select type of warrior:");
        System.out.println("1. Tank");
        System.out.println("2. Archer");
        System.out.println("3. Mage");
        int type = getValidInt("Choose an option: ", 1, 3);

        System.out.print("Enter Warrior name: ");
        String name = scanner.nextLine().trim();

        int health = getValidInt("Enter Warrior health (500 to 5000): ", 500, 5000);
        int attack = getValidInt("Enter Warrior attack (30 to 1000): ", 30, 1000);
        int defense = getValidInt("Enter Warrior defense (0 to 250): ", 0, 250);

        Warrior warrior = null;

        // Tambah validasi sesuai tipe warrior
        if (type == 1) {
            int shield = getValidInt("Enter shield strength (0 to 500): ", 0, 500);
            // Cast warrior ke Tank
            warrior = new Tank(name, attack, defense, health, shield);
        } else if (type == 2) {
            double critRate = getValidDouble("Enter critical rate (0.0 to 1.0): ", 0.0, 1.0);
            double critDmg = getValidDouble("Enter critical damage multiplier (1.0 to 5.0): ", 1.0, 5.0);
            // Cast warrior ke Archer
            warrior = new Archer(name, attack, defense, health, critRate, critDmg);
        } else if (type == 3) {
            // Cast warrior ke Mage
            warrior = new Mage(name, attack, defense, health);
        }

        // Menambahkan warrior ke WarriorList
        warriorList.addWarrior(warrior);
        System.out.println("\n" + name + " has been added to the battle.");
    }

    // Method untuk validasi int
    private int getValidInt(String prompt, int min, int max) {
        int input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextInt()) {
                System.out.println("That's not a valid number!");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextInt();
            scanner.nextLine();
            if (input < min || input > max) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }

    // Method untuk validasi double
    private double getValidDouble(String prompt, double min, double max) {
        double input;
        do {
            System.out.print(prompt);
            while (!scanner.hasNextDouble()) {
                System.out.println("That's not a valid number!");
                System.out.print(prompt);
                scanner.next();
            }
            input = scanner.nextDouble();
            scanner.nextLine();
            if (input < min || input > max) {
                System.out.println("Please enter a value between " + min + " and " + max + ".");
            }
        } while (input < min || input > max);
        return input;
    }

    // Method untuk display semua warrior
    public void displayWarriors() {
        // Sort semua warrior berdasarkan nama menggunakan Collections.sort
        Collections.sort(warriorList.getWarriors());

        // Print header
        System.out.println("\nCurrent warriors in the battlefield:");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        System.out.printf("| %-10s | %-15s | %-7s | %-7s | %-7s | %-7s | %-10s | %-10s |\n", "Type", "Name", "Attack",
                "Defense", "Health", "Shield", "Crit Rate", "Crit Dmg");
        System.out.println(
                "+------------+-----------------+---------+---------+---------+---------+------------+------------+");
        
        // Print setiap warrior
        for (Warrior warrior : warriorList.getWarriors()) {
            warrior.displayStats();
        }
    }

    // Method untuk simulasi attack
    public void simulateBattle() {
        // Sort semua warrior berdasarkan nama menggunakan Collections.sort
        Collections.sort(warriorList.getWarriors());

        // Memasukkan warrior yang sudah di-sort ke dalam Map menggunakan iteration
        int index = 1;
        Map<Integer, Warrior> warriorMap = new HashMap<>();
        for (Warrior warrior : warriorList.getWarriors()) {
            warriorMap.put(index, warrior);
            index++;
        }
        
        System.out.println("Select the attacking warrior:");
        // Print setiap warrior dari warriorMap dan lakukan validasi index attacker
        for (Map.Entry<Integer, Warrior> entry : warriorMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }

        System.out.println();
        int attackerIndex = getValidInt("Choose a warrior: ", 1, warriorMap.size());

        // Memilih attacker dari warriorMap dengan cara membuat objek attackerWarrior dan mengambil dari warriorMap
        Warrior attackerWarrior = warriorMap.get(attackerIndex);
        // Menghapus attacker dari warriorMap
        warriorMap.remove(attackerIndex);

        System.out.println("Select the defending warrior:");
        // Print setiap warrior dari warriorMap dan lakukan validasi index defender
        for (Map.Entry<Integer, Warrior> entry : warriorMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }

        // Mendapatkan index angka paling kecil dari warrior di warriorMap dengan iteration
        int minIndex = Collections.min(warriorMap.keySet());
        // Mendapatkan index angka paling besar dari warrior di warriorMap dengan iteration
        int maxIndex = Collections.max(warriorMap.keySet());

        System.out.println();
        int defenderIndex = getValidInt("Choose a warrior: ", minIndex, maxIndex);

        // Memilih defender dari warriorMap dengan cara membuat objek defenderWarrior dan mengambil dari warriorMap
        Warrior defenderWarrior = warriorMap.get(defenderIndex);

        // Menghapus defenderWarrior dari warriorList untuk ditambahkan ulang setelah simulasi
        warriorList.removeWarrior(defenderWarrior);

        // Simulasi attack dan defend di battlefield
        System.out.println("\n" + attackerWarrior.getName() + " is attacking " + defenderWarrior.getName());
        attackerWarrior.attack(defenderWarrior);

        if (!defenderWarrior.isAlive()) {
            System.out.println(defenderWarrior.getName() + " has fallen in battle!");
            // Jika defender mati, tambahkan ke fallenWarriors
            warriorList.addFallenWarrior(defenderWarrior);
        } else {
            System.out.println(defenderWarrior.getName() + " survived the attack with " + defenderWarrior.getHealth() + " health remaining.");
            // Jika defender masih hidup, tambahkan kembali ke warriorList
            warriorList.addWarrior(defenderWarrior);
        }
    }

    // Method untuk membangkitkan warrior
    public void revive() {
        // Cek apakah fallenWarriors kosong
        if (warriorList.getFallenWarriors().isEmpty()) {
            System.out.println("There are currently no warriors to revive.");
            return;
        }

        // Revive warrior yang sudah mati dengan sistem FIFO, dan syarat belum melebihi 2 kali revive
        while (true) {
            Warrior revivedWarrior = warriorList.getFallenWarriors().poll();
            if (revivedWarrior.getNumRevived() < 2) {
                revivedWarrior.revive();
                warriorList.addWarrior(revivedWarrior);
                System.out.println("Reviving " + revivedWarrior.getName() + "...");
                System.out.println("Successfully revived " + revivedWarrior.getName() + "!");
                break;
            } else {
                // Iterate ke fallenWarriors selanjutnya untuk di-revive jika warrior yang sekarang sudah di-revive 2 kali
                continue;
            }
        }
    }

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        battlefield.runMenu();
    }
}
