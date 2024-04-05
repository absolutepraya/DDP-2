public class ContractEmployee extends Employee implements AskForRaise, ExtendContractDuration {
    // Attributes
    public int contractDuration;
    public double raise;

    // Constructor
    public ContractEmployee(String name, double salary, int contractDuration) {
        super(name, salary);
        this.contractDuration = contractDuration;
        employeeId = ContractEmployee.employeeCnt++; // Assign employeeId dan increment employeeCnt
    }

    // Override method askRaise
    @Override
    public void askRaise(double raise) {
        this.raise += raise;
    }

    // Override method calculateSalary
    @Override
    public double calculateSalary() {
        // Mengalikan salary dengan salary multiplier, lalu menambahkan raise
        return (salary + raise) * getSalaryMultiplier();
    }

    // Override method extendContract
    @Override
    public void extendContract(int duration) {
        contractDuration += duration;
    }

    // Override method toString untuk menampilkan informasi employee
    @Override
    public String toString() {
        return "[%d] %s | Salary : %.0f | Kenaikan : %.0f | Kontrak : %d bulan".formatted(employeeId, name, calculateSalary(), raise, contractDuration);
    }

    // Method untuk mendapatkan salary multiplier berdasarkan contract duration
    private double getSalaryMultiplier() {
        if (contractDuration <= 6) {
            return 1.0;
        } else if (contractDuration <= 12) {
            return 1.5;
        } else {
            return 2.0;
        }
    }
}
