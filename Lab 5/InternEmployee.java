public class InternEmployee extends Employee implements ExtendContractDuration {
    // Attributes
    public int contractDuration;

    // Constructor
    public InternEmployee(String name, double salary, int contractDuration) {
        super(name, salary);
        this.contractDuration = contractDuration;
        employeeId = InternEmployee.employeeCnt++; // Assign employeeId dan increment employeeCnt
    }

    // Override method calculateSalary
    @Override
    public double calculateSalary() {
        // Mengalikan salary dengan salary multiplier
        return salary * getSalaryMultiplier();
    }

    // Override method extendContract
    @Override
    public void extendContract(int duration) {
        contractDuration += duration;
    }

    // Override method toString untuk menampilkan informasi employee
    @Override
    public String toString() {
        return "[%d] %s | Salary : %.0f | Kontrak : %d bulan".formatted(employeeId, name, calculateSalary(), contractDuration);
    }

    // Method untuk mendapatkan salary multiplier berdasarkan contract duration
    private double getSalaryMultiplier() {
        if (contractDuration <= 6) {
            return 1.0;
        } else if (contractDuration <= 12) {
            return 1.25;
        } else {
            return 1.5;
        }
    }
}
