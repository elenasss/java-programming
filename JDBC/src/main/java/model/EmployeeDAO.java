package model;

import JDBC.db.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private static DBManager dbManager;
    private static final String INSERT_EMPLOYEE = "INSERT INTO employees " +
            "(employee_id, first_name, last_name, email, hire_date, department, salary)" +
            " VALUES(?,?,?,?,?,?,?)";
    private static final String DELETE_BY_ID = "DELETE FROM employees WHERE employee_id = ?";
    private static final String SELECT_NAME_EMAIL_HIRE_DATE_DEPARTMENT_SALARY_FROM_EMPLOYEES =
            "SELECT first_name, last_name, email, hire_date, job_id, salary FROM employees ";
    private static final String SELECT_FROM_EMPLOYEES_JOIN_DEPARTMENTS =
            SELECT_NAME_EMAIL_HIRE_DATE_DEPARTMENT_SALARY_FROM_EMPLOYEES +
                    "JOIN departments AS d ON e.department = d.department ";

    public static void insertIntoDB(Employee employee) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE);
        preparedStatement.setInt(1, employee.getId());
        preparedStatement.setString(2, employee.getFirstName());
        preparedStatement.setString(3, employee.getLastName());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setString(5, String.valueOf(employee.getHireDate()));
        preparedStatement.setString(6, employee.getDepartment());
        preparedStatement.setDouble(7, employee.getSalary());
        preparedStatement.executeUpdate();
        preparedStatement.executeUpdate();
    }

    public static int deleteEmployeeById(long employeeId) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
        preparedStatement.setLong(1, employeeId);
        int rowsAffected = preparedStatement.executeUpdate();

        preparedStatement.close();
        return rowsAffected;
    }

    public static List<Employee> searchByFirstName(String firstName) throws SQLException {
        String query = SELECT_NAME_EMAIL_HIRE_DATE_DEPARTMENT_SALARY_FROM_EMPLOYEES + "WHERE first_name = ?";

        return selectEmployeeBy(query, firstName);
    }

    public static List<Employee> searchByLastName(String lastName) throws SQLException {
        String query = SELECT_NAME_EMAIL_HIRE_DATE_DEPARTMENT_SALARY_FROM_EMPLOYEES + "WHERE last_name = ?";
        return selectEmployeeBy(query, lastName);
    }

    public static List<Employee> searchByDepartmentName(String department) throws SQLException {
        String query = SELECT_FROM_EMPLOYEES_JOIN_DEPARTMENTS + " WHERE d.department_name = ?";
        return selectEmployeeBy(query, department);
    }

    private static List<Employee> selectEmployeeBy(String query, String name) throws SQLException {
        Connection connection = DBManager.getInstance().getConnection();
        List<Employee> employees = new ArrayList<>();

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Employee employee = employeeFromResultSet(resultSet);
            employees.add(employee);
        }
        preparedStatement.close();
        resultSet.close();

        return employees;
    }

    private static Employee employeeFromResultSet(ResultSet resultSet) throws SQLException {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String email = resultSet.getString("email");
        LocalDate hireDate = resultSet.getDate("hire_date").toLocalDate();
        String department = resultSet.getString("department");
        double salary = resultSet.getDouble("salary");
        return new Employee(firstName, lastName, email, hireDate, department, salary);
    }
}