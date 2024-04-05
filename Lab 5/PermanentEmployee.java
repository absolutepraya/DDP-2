public class PermanentEmployee extends Employee implements AskForRaise {
    // Attributes
    public double raise;

    // Constructor
    public PermanentEmployee(String name, double salary) {
        super(name, salary);
        employeeId = PermanentEmployee.employeeCnt++; // Assign employeeId dan increment employeeCnt
    };

    // Override method askRaise
    @Override
    public void askRaise(double raise) {
        this.raise += raise;
    }
    
    // Override method calculateSalary 
    @Override
    public double calculateSalary() {
        return salary + raise;
    }

    // Override method toString untuk menampilkan informasi employee
    @Override
    public String toString() {
        return "[%d] %s | Salary : %.0f | Kenaikan : %.0f".formatted(employeeId, name, calculateSalary(), raise);
    }
}