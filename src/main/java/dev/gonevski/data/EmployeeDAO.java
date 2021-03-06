package dev.gonevski.data;

import dev.gonevski.entities.Employee;
import java.util.List;

public interface EmployeeDAO {
    // Defining Function 1
    Employee addEmployee (Employee addedEmployee);
    // Defining Function 2
    Employee getEmployeeById (int employeeId);
    List<Employee> getAllEmployees();
    Employee updateEmployee (Employee updatedEmployee);
    boolean deleteEmployee (int employeeId);

}
