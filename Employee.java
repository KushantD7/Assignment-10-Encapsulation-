import java.util.ArrayList;

// Abstract class Employee
abstract class Employee {
    private int employeeId;      // Encapsulation: private fields
    private String name;
    private double baseSalary;

    // Constructor
    public Employee(int employeeId, String name, double baseSalary) {
        this.employeeId = employeeId;
        this.name = name;
        this.baseSalary = baseSalary;
    }

    // Getters and Setters
    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBaseSalary() { return baseSalary; }
    public void setBaseSalary(double baseSalary) { this.baseSalary = baseSalary; }

    // Abstract Method (must be implemented in subclasses)
    public abstract double calculateSalary();

    // Concrete Method
    public void displayDetails() {
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: " + baseSalary);
        System.out.println("Calculated Salary: " + calculateSalary());
    }
}

// Interface Department
interface Department {
    void assignDepartment(String departmentName);
    void getDepartmentDetails();
}

// FullTimeEmployee subclass
class FullTimeEmployee extends Employee implements Department {
    private String department;
    private double allowance;

    public FullTimeEmployee(int employeeId, String name, double baseSalary, double allowance) {
        super(employeeId, name, baseSalary);
        this.allowance = allowance;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + allowance;  // Fixed salary + allowance
    }

    @Override
    public void assignDepartment(String departmentName) {
        this.department = departmentName;
    }

    @Override
    public void getDepartmentDetails() {
        System.out.println("Department: " + department);
    }
}

// PartTimeEmployee subclass
class PartTimeEmployee extends Employee implements Department {
    private String department;
    private int workHours;
    private double hourlyRate;

    public PartTimeEmployee(int employeeId, String name, double hourlyRate, int workHours) {
        super(employeeId, name, 0);  // baseSalary not used here
        this.hourlyRate = hourlyRate;
        this.workHours = workHours;
    }

    @Override
    public double calculateSalary() {
        return workHours * hourlyRate; // Salary depends on hours worked
    }

    @Override
    public void assignDepartment(String departmentName) {
        this.department = departmentName;
    }

    @Override
    public void getDepartmentDetails() {
        System.out.println("Department: " + department);
    }
}

// Main class
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        // Polymorphism: Employee reference can point to subclass objects
        ArrayList<Employee> employees = new ArrayList<>();

        FullTimeEmployee emp1 = new FullTimeEmployee(101, "Aman", 30000, 5000);
        emp1.assignDepartment("IT");

        PartTimeEmployee emp2 = new PartTimeEmployee(102, "Adarsh", 500, 40);
        emp2.assignDepartment("Support");

        employees.add(emp1);
        employees.add(emp2);

        // Process all employees using polymorphism
        for (Employee emp : employees) {
            emp.displayDetails();

            // Downcasting to access interface method
            if (emp instanceof Department) {
                ((Department) emp).getDepartmentDetails();
            }
            System.out.println("-----------------------");
        }
    }
}
