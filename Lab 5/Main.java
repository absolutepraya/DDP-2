import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Employee> employeeList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Selamat Datang di PacilRekrutment");
        while (true) {
            printWelcomingMsg();
            System.out.print("Input: ");
            int actionCode = sc.nextInt();
            switch (actionCode) {
                case 1:
                    printEmployeeList();
                    break;
                case 2:
                    hireEmployee();
                    break;
                case 3:
                    askForRaise();
                    break;
                case 4:
                    extendContract();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan layanan PacilRekrutment ~ !");
                    sc.close();
                    return;
                default:
                    unknownActionMsg();
                    break;
            }
        }
    }

    public static void printEmployeeList() {
        // Cek apakah ada pegawai yang terdaftar
        if (employeeList.size() == 0) {
            System.out.println("Tidak Ada Employee yang Terdaftar!!!\n");
            return;
        }

        // Mencari jumlah pegawai dengan status Permanent, Contract, dan Intern
        int permanentEmployeeCnt = 0;
        int contractEmployeeCnt = 0;
        int internEmployeeCnt = 0;
        for (Employee employee : employeeList) {
            if (employee instanceof PermanentEmployee) {
                permanentEmployeeCnt++;
            }
            if (employee instanceof ContractEmployee) {
                contractEmployeeCnt++;
            }
            if (employee instanceof InternEmployee) {
                internEmployeeCnt++;
            }
        }

        // Print Employee list
        if (permanentEmployeeCnt > 0) displayPermanentEmployee();
        if (contractEmployeeCnt > 0) displayContractEmployee();
        if (internEmployeeCnt > 0) displayInternEmployee();
    }

    public static void hireEmployee() {
        // Input nama, base salary, dan status employee
        System.out.print("Nama: "); 
        String name = sc.next();
        // Cek apakah employee sudah terdaftar
        for (Employee employee : employeeList) {
            if (employee.name.equals(name)) {
                System.out.println("Nama sudah terdaftar!!!\n");
                return;
            }
        }
        System.out.print("Base Salary: "); 
        double salary = sc.nextDouble();
        System.out.print("Status Employee (Permanent/Contract/Intern): "); 
        String status = sc.next().toLowerCase();

        // Langsung membuat employee jika status employee adalah Permanent
        if (status.equals("permanent")) {
            employeeList.add(new PermanentEmployee(name, salary));
            // Print pesan berhasil
            System.out.printf("PermanentEmployee dengan ID %d bernama %s berhasil ditambahkan!\n\n", PermanentEmployee.employeeCnt - 1, name);
            return;
        }

        // Input contract duration jika status employee adalah Contract atau Intern
        int contractDuration = 0;
        System.out.print("Lama Kontrak (Bulan): "); 
        contractDuration = sc.nextInt();
        // Membuat employee
        if (status.equals("contract")) {
            employeeList.add(new ContractEmployee(name, salary, contractDuration));
            // Print pesan berhasil
            System.out.printf("ContractEmployee dengan ID %d bernama %s berhasil ditambahkan!\n\n", ContractEmployee.employeeCnt - 1, name);
        } else {
            employeeList.add(new InternEmployee(name, salary, contractDuration));
            // Print pesan berhasil
            System.out.printf("InternEmployee dengan ID %d bernama %s berhasil ditambahkan!\n\n", InternEmployee.employeeCnt - 1, name);
        }
    }

    public static void askForRaise() {
        // Cek apakah ada pegawai yang terdaftar
        if (employeeList.size() == 0) {
            System.out.println("Tidak Ada Permanent atau Contract Employee yang Terdaftar!!!\n");
            return;
        }

        // Mencari jumlah pegawai dengan status Permanent dan Contract
        int permanentEmployeeCnt = 0;
        int contractEmployeeCnt = 0;
        for (Employee employee : employeeList) {
            if (employee instanceof PermanentEmployee) {
                permanentEmployeeCnt++;
            }
            if (employee instanceof ContractEmployee) {
                contractEmployeeCnt++;
            }
        }
        // Cetak employee dengan status Permanent dan Contract
        if (permanentEmployeeCnt > 0) displayPermanentEmployee();
        if (contractEmployeeCnt > 0) displayContractEmployee();

        // Input nama atau ID employee
        System.out.print("Masukan Nama/ID Employee: ");
        String nameOrId = sc.next();
        Employee employee = getEmployeeByNameOrId(nameOrId);

        // Cek apakah employee ditemukan dan cek apakah employee merupakan instance dari AskForRaise
        if (employee == null) {
            System.out.printf("Employee dengan Nama/ID %s Tidak Ditemukan!!!\n\n", nameOrId);
            return;
        }
        if (employee instanceof InternEmployee) {
            System.out.println("Intern Employee Tidak Bisa Mendapatkan Raise!!!\n");
            return;
        }

        // Input raise
        System.out.print("Masukan Jumlah Kenaikan: ");
        double raise = sc.nextDouble();

        // Cek apakah raise valid
        if (raise < 0) {
            System.out.println("Kenaikan Gaji Tidak Boleh Negatif!!!\n");
            return;
        }

        // Meminta kenaikan gaji berdasarkan status employee
        if (employee instanceof ContractEmployee) ((ContractEmployee) employee).askRaise(raise);
        else ((PermanentEmployee) employee).askRaise(raise);

        // Print pesan berhasil
        System.out.printf("Employee dengan Nama/ID %s Berhasil Dinaikkan Gajinya Sebesar %.0f\n\n", nameOrId, raise);
    }

    public static void extendContract() {
        // Cek apakah ada pegawai yang terdaftar
        if (employeeList.size() == 0) {
            System.out.println("Tidak Ada Contract atau Intern Employee yang Terdaftar!!!\n");
            return;
        }

        // Mencari jumlah pegawai dengan status Contract dan Intern
        int contractEmployeeCnt = 0;
        int internEmployeeCnt = 0;
        for (Employee employee : employeeList) {
            if (employee instanceof ContractEmployee) {
                contractEmployeeCnt++;
            }
            if (employee instanceof InternEmployee) {
                internEmployeeCnt++;
            }
        }
        // Cetak employee dengan status Contract dan Intern
        if (contractEmployeeCnt > 0) displayContractEmployee();
        if (internEmployeeCnt > 0) displayInternEmployee();

        // Input nama atau ID employee
        System.out.print("Masukan Nama/ID Employee: ");
        String nameOrId = sc.next();
        Employee employee = getEmployeeByNameOrId(nameOrId);

        // Cek apakah employee ditemukan dan cek apakah employee merupakan instance dari ExtendContractDuration
        if (employee == null) {
            System.out.printf("Employee dengan Nama/ID %s Tidak Ditemukan!!!\n\n", nameOrId);
            return;
        }
        if (employee instanceof PermanentEmployee) {
            System.out.println("PermanentEmployee Tidak Bisa Extend Kontrak!!!\n");
            return;
        }

        // Input duration
        System.out.print("Masukan Durasi Perpanjangan: ");
        int duration = sc.nextInt();

        // Memperpanjang kontrak berdasarkan status employee
        if (employee instanceof InternEmployee) ((InternEmployee) employee).extendContract(duration);
        else ((ContractEmployee) employee).extendContract(duration);

        // Print pesan berhasil
        System.out.printf("Employee dengan Nama/ID %s Berhasil Diperpanjang Kontraknya Selama %d Bulan\n\n", nameOrId, duration);
    }

    // Kumpulan Helper Method

    public static Employee getEmployeeByNameOrId(String nameOrId) {
        // Return employee if exists, otherwise null
        for (Employee employee : employeeList) {
            if (employee.name.equals(nameOrId) || Integer.toString(employee.employeeId).equals(nameOrId)) {
                return employee;
            }
        }
        return null;
    }

    public static void displayPermanentEmployee() {
        if (PermanentEmployee.employeeCnt == 0) {
            return;
        }
        System.out.println("===== Pegawai Tetap =====");
        ArrayList<PermanentEmployee> permanentEmployees = getPermanentEmployee();
        for (PermanentEmployee employee : permanentEmployees) {
            System.out.println(employee);
        }
        System.out.println();
    }

    public static void displayContractEmployee() {
        if (ContractEmployee.employeeCnt == 0) {
            return;
        }
        System.out.println("===== Pegawai Kontrak =====");
        ArrayList<ContractEmployee> contractEmployees = getContractEmployee();
        for (ContractEmployee employee : contractEmployees) {
            System.out.println(employee);
        }
        System.out.println();
    }

    public static void displayInternEmployee() {
        if (InternEmployee.employeeCnt == 0) {
            return;
        }
        System.out.println("===== Pegawai Intern =====");
        ArrayList<InternEmployee> internEmployees = getInternEmployee();
        for (InternEmployee employee : internEmployees) {
            System.out.println(employee);
        }
        System.out.println();
    }

    // Penggunaan Generics dapat digunakan (akan dipelajari di week mendatang)
    // untuk mengurangi pengulangan 3 method ini
    public static ArrayList<InternEmployee> getInternEmployee() {
        ArrayList<InternEmployee> internEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof InternEmployee) {
                internEmployees.add((InternEmployee) employee);
            }
        }
        return internEmployees;
    }

    public static ArrayList<ContractEmployee> getContractEmployee() {
        ArrayList<ContractEmployee> contractEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof ContractEmployee) {
                contractEmployees.add((ContractEmployee) employee);
            }
        }
        return contractEmployees;
    }

    public static ArrayList<PermanentEmployee> getPermanentEmployee() {
        ArrayList<PermanentEmployee> permanentEmployees = new ArrayList<>();
        for (Employee employee : employeeList) {
            if (employee instanceof PermanentEmployee) {
                permanentEmployees.add((PermanentEmployee) employee);
            }
        }
        return permanentEmployees;
    }

    // Printing Function
    public static void printWelcomingMsg() {
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Raise Salary");
        System.out.println("[4] Extend Contract");
        System.out.println("[5] Exit");
        System.out.println("=".repeat(64));
    }

    public static void unknownActionMsg() {
        System.out.println("Mohon masukkan opsi yang valid!\n");
    }
}