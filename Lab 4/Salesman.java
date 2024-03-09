// Class salesman merupakan subclass dari class Employee
public class Salesman extends Employee {
    // Private attribute dari class Salesman
    private double totalSales;
    private double commissionFee;

    // Constructor
    public Salesman(String name, int yearsOfWork, double baseSalary, double commissionFee) {
        // Inherit attribute dari class Employee
        super(name, yearsOfWork, baseSalary);
        this.commissionFee = commissionFee;
    }

    // Setter
    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }
    public void setCommissionFee(double commissionFee) {
        this.commissionFee = commissionFee;
    }

    // Getter
    public double getTotalSales() {
        return totalSales;
    }
    public double getCommissionFee() {
        return commissionFee;
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

        return super.CalculateSalary() + (this.totalSales * this.commissionFee / 100) * salaryMultiplier;
    }

    @Override
    // Override method toString dari class Object
    public String toString() {
        return super.toString() + 
                "Role: Salesman\n" +
                "Banyak Sales: " + String.format("%.1f", this.totalSales) + " IDR\n" +
                "Final Salary: " + String.format("%.1f", super.getFinalSalary()) + " IDR";
    }
}
