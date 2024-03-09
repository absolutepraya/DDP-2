// Class accountant merupakan subclass dari class Employee
public class Accountant extends Employee {
    // Private attribute dari class Accountant
    private int totalHoursWorked;
    private double hourlyRate;

    // Constructor
    public Accountant(String name, int yearsOfWork, double baseSalary, double hourlyRate) {
        // Inherit attribute dari class Employee
        super(name, yearsOfWork, baseSalary);
        this.hourlyRate = hourlyRate;
    }

    // Setter
    public void setTotalHoursWorked(int totalHoursWorked) {
        this.totalHoursWorked = totalHoursWorked;
    }
    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    // Getter
    public int getTotalHoursWorked() {
        return totalHoursWorked;
    }
    public double getHourlyRate() {
        return hourlyRate;
    }

    @Override
    // Override CalculateSalary dari class Employee karena ada perhitungan tambahan
    public double CalculateSalary() {
        // Conditionals untuk menentukan salaryMultiplier
        double salaryMultiplier = 0;
        if (super.getYearsOfWork() > 10) {
            salaryMultiplier = 2;
        } else if (super.getYearsOfWork() > 5) {
            salaryMultiplier = 1.5;
        } else {
            salaryMultiplier = 1;
        }

        return super.CalculateSalary() + (this.totalHoursWorked * this.hourlyRate) * salaryMultiplier;
    }

    @Override
    // Override method toString dari class Object
    public String toString() {
        return super.toString() + 
                "Role: Accountant\n" +
                "Total Jam Kerja: " + this.totalHoursWorked + "\n" +
                "Final Salary: " + String.format("%.1f", super.getFinalSalary()) + " IDR";
    }
}
