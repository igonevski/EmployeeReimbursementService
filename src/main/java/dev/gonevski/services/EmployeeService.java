package dev.gonevski.services;

import dev.gonevski.entities.Employee;
import dev.gonevski.entities.Expense;
import java.util.List;

public interface EmployeeService {

//    POST /employees - returns a 201
    public Employee POSTEmployee(Employee recordedEmployee);

//    GET /employees
    List<Employee> GETEmployeeList();

//    GET /employees/{employeeId} - returns a 404 if employee not found
    public Employee GETEmployeeById(int employeeId);

//    PUT /employees/{employeeId} - returns a 404 if employee not found
    public Employee PUTEmployee(Employee employee);

//    DELETE /employees/{employeeId} - returns a 404 if employee not found
    public boolean DELETEEmployeeById(int employeeId);

    
}
