// Class engineer merupakan subclass dari class Employee
public class Engineer extends Employee {
    // Private attribute dari class Engineer
    private int totalProject;
    private double projectFee;

    // Constructor
    public Engineer(String name, int yearsOfWork, double baseSalary, double projectFee) {
        // Inherit attribute dari class Employee
        super(name, yearsOfWork, baseSalary);
        this.projectFee = projectFee;
    }

    // Setter
    public void setTotalProject(int totalProject) {
        this.totalProject = totalProject;
    }
    public void setProjectFee(double projectFee) {
        this.projectFee = projectFee;
    }

    // Getter
    public int getTotalProject() {
        return totalProject;
    }
    public double getProjectFee() {
        return projectFee;
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

        return super.CalculateSalary() + (this.projectFee * this.totalProject) * salaryMultiplier;
    }

    @Override
    // Override method toString dari class Object
    public String toString() {
        return super.toString() + 
                "Role: Engineer\n" +
                "Banyak Project: " + this.totalProject + "\n" +
                "Final Salary: " + String.format("%.1f", super.getFinalSalary()) + " IDR";
    }
}
