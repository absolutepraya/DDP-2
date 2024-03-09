import java.util.ArrayList;
import java.util.Scanner;

public class PacilValley {
    private static Scanner in = new Scanner(System.in);
    private static ArrayList<Employee> employees = new ArrayList<>();

    private static void printSeparator() {
        System.out.println("=".repeat(64));
    }

    public static void employeeList() {
        int totalEmployee = employees.size();

        if (totalEmployee == 0) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }

        printSeparator();
        System.out.println("PacilValley memiliki total " + totalEmployee + " karyawan:");
        // Cetak semua employee yang ada di dalam list
        for (int i = 0; i < totalEmployee; i++) {
            Employee employee = employees.get(i);
            if (i == totalEmployee - 1) {
                System.out.println(employee.toString());
            } else {
                System.out.println(employee.toString() + "\n");
            }
        }

        printSeparator();
    }

    public static void hireEmployee() {
        Employee newEmployee;

        System.out.print("Nama: ");
        String nama = in.nextLine();

        System.out.print("Pengalaman Kerja (tahun): ");
        int pengalamanKerja = Integer.parseInt(in.nextLine());

        System.out.print("Base Salary (IDR): ");
        double baseSalary = Integer.parseInt(in.nextLine());

        String role;
        String tempPosition;
        while (true) {
            System.out.print("Role Employee: ");
            role = in.nextLine();

            double fee; // Inisiasi fee untuk semua role
            if (role.equalsIgnoreCase("Engineer")) {
                // Meminta input project fee
                System.out.print("Project Fee (IDR): ");
                fee = Integer.parseInt(in.nextLine());
                // Membuat object Engineer baru
                newEmployee = new Engineer(nama, pengalamanKerja, baseSalary, fee);
                tempPosition = "Engineer";
                break;
            } else if (role.equalsIgnoreCase("Salesman")) {
                // Meminta input commission fee
                System.out.print("Commission Fee (%): ");
                fee = Integer.parseInt(in.nextLine());
                // Membuat object Salesman baru
                newEmployee = new Salesman(nama, pengalamanKerja, baseSalary, fee);
                tempPosition = "Salesman";
                break;
            } else if (role.equalsIgnoreCase("Accountant")) {
                // Meminta input hourly rate
                System.out.print("Hourly Rate (IDR): ");
                fee = Integer.parseInt(in.nextLine());
                // Membuat object Accountant baru
                newEmployee = new Accountant(nama, pengalamanKerja, baseSalary, fee);
                tempPosition = "Accountant";
                break;
            } else {
                System.out.println("\nRole employee tidak valid, silahkan input kembali dengan nilai yang benar!\n");
            }
        }

        // Set employeeID untuk employee baru
        newEmployee.setEmployeeID(employees.size() + 1);
        System.out.println("\n" + tempPosition + " dengan ID " + (employees.size() + 1) + " bernama " + newEmployee.getName() + " berhasil dihire!\n");

        // Menambahkan employee baru ke dalam list
        employees.add(newEmployee);
    }

    public static void logEmployeeSalary() {
        if (employees.isEmpty()) {
            System.out.println("PacilValley belum memiliki karyawan :(\n");
            return;
        }
        
        // Meminta input employeeID dan melakukan validasi
        int employeeID;
        while (true) {
            System.out.print("Employee ID: ");
            employeeID = Integer.parseInt(in.nextLine());
            if (employeeID < 1 || employeeID > employees.size()) {
                System.out.println("\nEmployee dengan ID " + employeeID + " tidak ditemukan! Silahkan masukkan ID yang sesuai.\n");
            } else {
                Employee employee = employees.get(employeeID - 1);
                System.out.println("Employee bernama " + employee.getName() + " dengan role " + employee.getClass().getSimpleName() + " berhasil dipilih!");
                break;
            }
        }

        // Conditionals untuk menentukan role dari employee
        Employee employee = employees.get(employeeID - 1);
        if (employee instanceof Engineer) {
            Engineer engineer = (Engineer) employee;
            System.out.print("Jumlah assigned project: ");
            int totalProject = Integer.parseInt(in.nextLine());
            engineer.setTotalProject(totalProject);
            // Set final salary dari employee yang dipilih
            employee.setFinalSalary(engineer.CalculateSalary());
        } else if (employee instanceof Salesman) {
            Salesman salesman = (Salesman) employee;
            System.out.print("Jumlah sales (IDR): ");
            double totalSales = Integer.parseInt(in.nextLine());
            salesman.setTotalSales(totalSales);
            // Set final salary dari employee yang dipilih
            employee.setFinalSalary(salesman.CalculateSalary());
        } else if (employee instanceof Accountant) {
            Accountant accountant = (Accountant) employee;
            System.out.print("Jumlah jam bekerja: ");
            int totalHoursWorked = Integer.parseInt(in.nextLine());
            accountant.setTotalHoursWorked(totalHoursWorked);
            // Set final salary dari employee yang dipilih
            employee.setFinalSalary(accountant.CalculateSalary());
        }

        // Print final salary dari employee yang dipilih
        System.out.println("Gaji " + employee.getName() + " bulan ini adalah " + String.format("%.1f", employee.getFinalSalary()) + " IDR!\n");
    }

    private static void printMenu() {
        System.out.println("Silakan pilih salah satu opsi berikut:");
        System.out.println("[1] Employee List");
        System.out.println("[2] Hire Employee");
        System.out.println("[3] Log Employee Salary");
        System.out.println("[4] Exit");
        System.out.println("=".repeat(64));
    }

    public static void main(String[] args) {
        System.out.println("Selamat datang di PacilValley!");
        while (true) {
            printMenu();
            System.out.print("Input: ");
            int pilihan = Integer.parseInt(in.nextLine());

            if (pilihan == 1) {
                employeeList();
            } else if (pilihan == 2) {
                hireEmployee();
            } else if (pilihan == 3) {
                logEmployeeSalary();
            } else {
                System.out.println("Terima kasih telah menggunakan layanan PacilValley ~ !");
                break;
            }
        }
    }
}