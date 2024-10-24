package JDBC.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Employee {

    private static int uniqueID = 1;
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate hireDate;
    private String department;
    private double salary;

    public Employee(int id, String firstName, String lastName, String email, LocalDate hireDate, String department, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, String email, LocalDate hireDate, String department, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hireDate = hireDate;
        this.department = department;
        this.salary = salary;
        this.id = uniqueID++;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", hireDate=" + hireDate +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                '}';
    }
}