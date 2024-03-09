public class Employee {
    // Private attribute dari class Employee
    private int employeeID;
    private String name;
    private int yearsOfWork;
    private double baseSalary;
    private double finalSalary;

    // Constructor
    public Employee(String name, int yearsOfWork, double baseSalary) {
        this.name = name;
        this.yearsOfWork = yearsOfWork;
        this.baseSalary = baseSalary;
    }

    // Setter untuk employeeID, name, yearsOfWork, baseSalary, finalSalary
    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setYearsOfWork(int yearsOfWork) {
        this.yearsOfWork = yearsOfWork;
    }
    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }
    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }

    // Getter untuk employeeID, name, yearsOfWork, baseSalary, finalSalary
    public int getEmployeeID() {
        return employeeID;
    }
    public String getName() {
        return name;
    }
    public int getYearsOfWork() {
        return yearsOfWork;
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    public double getFinalSalary() {
        return finalSalary;
    }

    // Method untuk menghitung finalSalary
    public double CalculateSalary() {
        // Conditionals untuk menentukan salaryMultiplier
        double salaryMultiplier = 0;
        if (this.yearsOfWork > 10) {
            salaryMultiplier = 2;
        } else if (this.yearsOfWork > 5) {
            salaryMultiplier = 1.5;
        } else {
            salaryMultiplier = 1;
        }

        return this.baseSalary * salaryMultiplier;
    }
    
    @Override
    // Override toString dari class Object
    public String toString() {
        // Conditionals untuk menentukan jabatan
        String position;
        if (this.yearsOfWork > 10) {
            position = "Expert";
        } else if (this.yearsOfWork > 5) {
            position = "Senior";
        } else {
            position = "Junior";
        }

        return "Nama: " + this.name + "\n" +
                "Pengalaman Kerja: " + this.yearsOfWork + " tahun\n" +
                "Jabatan: " + position + "\n";
    }
}
