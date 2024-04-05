abstract class Employee {
    // Attributes
    public int employeeId;
    public static int employeeCnt = 0;
    public String name;
    public double salary;

    // Constructor
    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Abstract method untuk diimplementasikan di subclass
    public abstract double calculateSalary();
    public abstract String toString();
}
