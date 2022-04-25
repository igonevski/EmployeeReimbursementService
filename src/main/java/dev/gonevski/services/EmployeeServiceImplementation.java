package dev.gonevski.services;

import dev.gonevski.data.EmployeeDAO;
import dev.gonevski.data.EmployeeDAOImplementation;
import dev.gonevski.entities.Employee;

// Using an existing data structure package
import java.util.List;

public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeDAO employeeDAO;

    public EmployeeServiceImplementation(EmployeeDAOImplementation newEmployeeDAO) {this.employeeDAO = (EmployeeDAO) newEmployeeDAO;}

    @Override
    public Employee POSTEmployee(Employee recordedEmployee) {
        Employee employee = this.employeeDAO.addEmployee(recordedEmployee);
        return employee;
    }
    @Override
    public List<Employee> GETEmployeeList() {
        return this.employeeDAO.getAllEmployees();
    }
    @Override
    public Employee GETEmployeeById(int employeeId) {
        return this.employeeDAO.getEmployeeById(employeeId);
    }
    @Override
    public Employee PUTEmployee(Employee employee) {
        return this.employeeDAO.updateEmployee(employee);
    }
    @Override
    public boolean DELETEEmployeeById(int employeeId) {
        return this.employeeDAO.deleteEmployee(employeeId);
    }

}
