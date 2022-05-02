package dev.gonevski.DAOtests;

import dev.gonevski.entities.Employee;
import dev.gonevski.data.EmployeeDAO;
import dev.gonevski.data.EmployeeDAOImplementation;
import java.util.List;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order
public class EmployeeDAOTest {

    static EmployeeDAO employeeDAO = new EmployeeDAOImplementation();
    static Employee testEmployee = null;

    @Test
    @Order(1)
    public void addEmployee_test() {
        Employee john = new Employee(0,"John","Doe", "IT", "Network Engineer");
        Employee savedEmployee = employeeDAO.addEmployee(john);
        EmployeeDAOTest.testEmployee = savedEmployee;
        Assertions.assertNotEquals(0,savedEmployee.getEmployeeId());
    }

    @Test
    @Order(2)
    public void getEmployeeById_test() {
        System.out.println(employeeDAO.getAllEmployees());
        Employee retrievedEmployee  = employeeDAO.getEmployeeById(testEmployee.getEmployeeId());// use the id generated from the previous test
        System.out.println(retrievedEmployee);
        Assertions.assertEquals("John", retrievedEmployee.getFirstName());
    }

    @Test
    @Order(3)
    public void updateEmployee_test() {
        EmployeeDAOTest.testEmployee.setFirstName("Jane");
        employeeDAO.updateEmployee(testEmployee);// the new title should be saved to the database
        Employee retrievedEmployee = employeeDAO.getEmployeeById(testEmployee.getEmployeeId());
        Assertions.assertEquals("Jane",retrievedEmployee.getFirstName());
    }

    @Test
    @Order(4)
    public void deleteEmployee_test() {
        boolean result = employeeDAO.deleteEmployee(testEmployee.getEmployeeId()); // true if successful
        Assertions.assertTrue(result);
    }

    @Test
    @Order(5)
    public void getAllEmployees_test() {
        Employee a = new Employee(0,"Alex","Anderson","R&D", "Scientist");
        Employee b = new Employee(0,"Drake","Bale","Accounting", "Accountant");
        Employee c = new Employee(0,"Carl","Davis","IT", "Network Engineer");
        employeeDAO.addEmployee(a);
        employeeDAO.addEmployee(b);
        employeeDAO.addEmployee(c);
        List<Employee> employees = employeeDAO.getAllEmployees();
        int totalEmployees = employees.size(); // if list did not work and was not tested this might fail regardless
        Assertions.assertTrue(totalEmployees >= 3);
    }

}
