package dev.gonevski.data;

import dev.gonevski.entities.Employee;
import dev.gonevski.exceptions.ResourceNotFound;
import dev.gonevski.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImplementation implements EmployeeDAO {

    @Override
    public Employee addEmployee (Employee addedEmployee) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into ers_employees values(default,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, addedEmployee.getFirstName());
            ps.setString(2, addedEmployee.getLastName());
            ps.setString(3, addedEmployee.getWorkDept());
            ps.setString(4, addedEmployee.getJobTitle());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

            int employeeId = rs.getInt("eid");
            System.out.println(employeeId);
            addedEmployee.setEmployeeId(employeeId);
            return addedEmployee;
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Employee getEmployeeById (int employeeId) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ers_employees where ers_employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ResultSet rs = ps.executeQuery();
            //look into exceptions here
            rs.next();
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getInt("ers_employee_id"));
            employee.setFirstName(rs.getString("ers_employee_first_name"));
            employee.setLastName(rs.getString("ers_employee_last_name"));
            employee.setWorkDept(rs.getString("ers_employee_work_dept"));
            employee.setJobTitle(rs.getString("ers_employee_job_title"));
            return employee;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ers_employees";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("ers_employee_id"));
                employee.setFirstName(rs.getString("ers_employee_first_name"));
                employee.setLastName(rs.getString("ers_employee_last_name"));
                employee.setWorkDept(rs.getString("ers_employee_work_dept"));
                employee.setJobTitle(rs.getString("ers_employee_job_title"));
                employeeList.add(employee);
            }
            return employeeList;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee updateEmployee (Employee updatedEmployee) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update ers_employees set ers_employee_first_name=?, ers_employee_last_name=?, ers_employee_work_dept=?, ers_employee_job_title=? where ers_employee_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, updatedEmployee.getFirstName());
            ps.setString(2, updatedEmployee.getLastName());
            ps.setString(3, updatedEmployee.getWorkDept());
            ps.setString(4, updatedEmployee.getJobTitle());
            ps.setInt(5, updatedEmployee.getEmployeeId());

            int rowsUpdated = ps.executeUpdate();

            if(rowsUpdated == 0){
                throw new ResourceNotFound(updatedEmployee.getEmployeeId());
            }
            return updatedEmployee;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployee (int employeeId) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from ers_employees where ers_employee_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.execute();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
