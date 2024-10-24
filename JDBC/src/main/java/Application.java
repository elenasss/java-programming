import model.Employee;
import model.EmployeeDAO;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Application {
    public void start() {
        while (true) {
            Console.showOptions();
            int userChoice = Console.readUserInput();
            if (userChoice == 7) {
                Console.showExitMessage();
                return;
            }

            switch (userChoice) {
                case 1: // Search by first name
                    Console.showMessage("Enter the first name: ");
                    String firstName = Console.readTextFromConsole();
                    searchByFirstName(firstName);
                    break;
                case 2: // Search by last name
                    Console.showMessage("Enter last name: ");
                    String lastName = Console.readTextFromConsole();
                    searchByLastName(lastName);
                    break;
                case 3: // Search by department name
                    Console.showMessage("Enter department name: ");
                    String departmentName = Console.readTextFromConsole();
                    searchByDepartmentName(departmentName);
                    break;
                case 5:
                    Console.showMessageNewLine("Please enter following data in one line separated by ',' (comma).");
                    Console.showMessageNewLine("first name,last name,email,date (YYYY-mm-dd),job id (AC_MGR),salary");
                    String[] employeeData = Console.readTextFromConsole().split(",");
                    addEmployee(employeeData);
                    break;
                case 6:
                    Console.showMessage("Please enter employee id you want to delete: ");
                    int id = Console.readUserInput();
                    deleteEmployeeById(id);
                    break;
                default: Console.showMessage("Please enter a valid number");
            }
        }
    }

    private void searchByFirstName(String firstName) {
        List<Employee> employees;
        try {
            employees = EmployeeDAO.searchByFirstName(firstName);
            Console.printResultOnConsole(employees);
        } catch (SQLException e) {
            Console.showErrorMessage();
        }
    }

    private void searchByLastName(String lastName) {
        List<Employee> employees;
        try {
            employees = EmployeeDAO.searchByLastName(lastName);
            Console.printResultOnConsole(employees);
        } catch (SQLException e) {
            Console.showErrorMessage();
        }
    }

    private void searchByDepartmentName(String departmentName) {
        try {
            List<Employee> employees = EmployeeDAO.searchByDepartmentName(departmentName);
            Console.printResultOnConsole(employees);
        } catch (SQLException e) {
            Console.showErrorMessage();
        }
    }

    private void addEmployee(String[] employeeData) {
        Employee employee;
        try {
            employee = new Employee(employeeData[0], employeeData[1],
                    employeeData[2], LocalDate.parse(employeeData[3]),
                    employeeData[4], Double.parseDouble(employeeData[5]));
        } catch (Exception e) {
            Console.showMessageNewLine("Invalid data entered! Please review your input and try again.");
            return;
        }


        try {
            EmployeeDAO.insertIntoDB(employee);
            Console.showSuccessMessage("Successfully added: " + employee);
        } catch (SQLException ex) {
            Console.showErrorMessage();
        }
    }

    private void deleteEmployeeById(long id) {
        try {
            int affectedRows = EmployeeDAO.deleteEmployeeById(id);
            if (affectedRows > 0) {
                Console.showSuccessMessage("Successfully deleted employee with id: " + id);
            } else {
                Console.showMessageNewLine("Employee with id: " + id + " does not exist!");
            }
        } catch (SQLException e) {
            Console.showMessageNewLine("Something went wrong when delete user by id: " + id);
        }
    }
}