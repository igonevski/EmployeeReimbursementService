package dev.gonevski.data;

import dev.gonevski.entities.Employee;
import java.util.List;

public interface EmployeeDAO {

    Employee addEmployee (Employee addedEmployee);
    Employee getEmployeeById (int employeeId);
    List<Employee> getAllEmployees();
    Employee updateEmployee (Employee updatedEmployee);
    boolean deleteEmployee (int employeeId);

}
