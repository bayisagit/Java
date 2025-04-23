import java.util.ArrayList;
import java.util.Scanner;

class Employee {
    private String name;
    private String sex; // 'M' for male, 'F' for female
    private double salary;
    private String cRank;

    public Employee(String name, String sex, double salary, String cRank) {
        this.name = name;
        this.sex = sex;
        this.salary = salary;
        this.cRank = cRank;
    }

    public double calculateTax() {
        double taxRate = 0.2; // 20% tax
        return salary * taxRate;
    }

    public String display() {
        return "Name: " + name + ", Sex: " + sex + ", Salary: " + salary + ", C-Rank: " + cRank;
    }

    public String getSex() {
        return sex;
    }
}

public class EmployeeManagement {
    private static ArrayList<Employee> employees = new ArrayList<>();

    public static void insertEmployees(int n) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            System.out.print("Enter employee name: ");
            String name = scanner.nextLine();
            System.out.print("Enter employee sex (M/F): ");
            String sex = scanner.nextLine().toUpperCase();
            System.out.print("Enter employee salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter employee C-Rank: ");
            String cRank = scanner.nextLine();
            employees.add(new Employee(name, sex, salary, cRank));
        }
        scanner.close();
    }

    public static void displayAllEmployees() {
        System.out.println("\nAll Employees:");
        for (Employee emp : employees) {
            System.out.println(emp.display());
        }
    }

    public static void displayTaxAndSalary() {
        System.out.println("\nTax and Salary Details:");
        for (Employee emp : employees) {
            double tax = emp.calculateTax();
            System.out.println(emp.display() + ", Tax: " + tax);
        }
    }

    public static void displayFemaleEmployees() {
        System.out.println("\nFemale Employees:");
        for (Employee emp : employees) {
            if (emp.getSex().equals("F")) {
                System.out.println(emp.display());
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of employees: ");
        int n = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        insertEmployees(n);
        displayAllEmployees();
        displayTaxAndSalary();
        displayFemaleEmployees();
        scanner.close();
    }
}