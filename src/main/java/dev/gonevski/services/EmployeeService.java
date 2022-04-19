package dev.gonevski.services;

import dev.gonevski.entities.Employee;
import dev.gonevski.entities.Expense;
import java.util.List;

public interface EmployeeService {

//    POST /employees
//    returns a 201
    public Employee recordEmployee(Employee recordedEmployee);
//    GET /employees
    List<Employee> GETEmployeeList();
//    GET /employees/120
//    returns a 404 if employee not found
    public Employee GETEmployeeById(int employeeId);
//    PUT /employees/150
//    returns a 404 if employee not found
    public Employee PUTEmployeeById(int employeeId);
//    DELETE /employees/190
//    returns a 404 if employee not found
    public Employee DELETEEmployeeById(int employeeId);
//    GET /employees/120/expenses
//    returns expenses for employee 120
    public Expense GETExpenseByEmployeeId(int employeeId);
//    POST /employees/120/expenses
//    adds an expense to employee 120
    public Expense POSTExpenseByEmployeeId(int employeeId);

}
