import model.Employee;

import java.util.List;
import java.util.Scanner;

public class Console {
    private static Scanner scanner;

    private static final String ADD_EMPLOYEE = "To Add new employee press 1";
    private static final String SEARCH_BY_FIRST_NAME = "To search by first name press 2";
    private static final String SEARCH_BY_LAST_NAME = "To search by last name press 3";
    private static final String DELETE_EMPLOYEE_BY_ID = "To delete an employee by id press 4";
    private static final String SEARCH_BY_DEPARTMENT_NAME = "To search by department name press 5";
    private static final String EXIT = "To exit press 5";

    public static void showOptions() {
        showMessageNewLine(SEARCH_BY_FIRST_NAME);
        showMessageNewLine(SEARCH_BY_LAST_NAME);
        showMessageNewLine(SEARCH_BY_DEPARTMENT_NAME);
        showMessageNewLine(ADD_EMPLOYEE);
        showMessageNewLine(DELETE_EMPLOYEE_BY_ID);
        showMessageNewLine(EXIT);
        showMessage("Please enter a number from the menu: ");
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
    }

    public static void showMessageNewLine(String message) {
        System.out.println(message);
    }

    public static int readUserInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    public static String readTextFromConsole() {
        return scanner.nextLine();
    }

    public static void showMessage(String message) {
        System.out.print(message);
    }

    public static void printResultOnConsole(List<Employee> employees) {
        if (!employees.isEmpty()) {
            showMessageNewLine("Results found: " + employees.size());
            for (Employee employee : employees) {
                showMessageNewLine(employee.toString());
            }
        } else {
            showMessageNewLine("No employee/s found based on search criteria!");
        }
    }

    public static void showErrorMessage() {
        showMessageNewLine("Something went wrong.");
    }

    public static void showExitMessage() {
        System.out.print("EXIT");
    }

    public static void showSuccessMessage(String message) {
        System.out.println(message);
    }
}