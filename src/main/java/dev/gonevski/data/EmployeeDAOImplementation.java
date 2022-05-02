package dev.gonevski.data;

import dev.gonevski.entities.Employee;
import dev.gonevski.exceptions.ResourceNotFound;
import dev.gonevski.utilities.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImplementation implements EmployeeDAO {

    private Logger logger = LogManager.getLogger(EmployeeDAOImplementation.class.getName());

    @Override
    public Employee addEmployee (Employee addedEmployee) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into ersemployees values(default,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, addedEmployee.getFirstName());
            ps.setString(2, addedEmployee.getLastName());
            ps.setString(3, addedEmployee.getWorkDept());
            ps.setString(4, addedEmployee.getJobTitle());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

            int employeeId = rs.getInt("ersemployeeid");
            System.out.println(employeeId);
            addedEmployee.setEmployeeId(employeeId);
            return addedEmployee;
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("unable to create Employee",e);
        }
        return null;
    }

    @Override
    public Employee getEmployeeById (int employeeId) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ersemployees where ersemployeeid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            //look into exceptions here

            rs.next();
            Employee employee = new Employee();
            employee.setEmployeeId(rs.getInt("ersemployeeid"));
            employee.setFirstName(rs.getString("ersemployeefirstname"));
            employee.setLastName(rs.getString("ersemployeelastname"));
            employee.setWorkDept(rs.getString("ersemployeeworkdept"));
            employee.setJobTitle(rs.getString("ersemployeejobtitle"));
            return employee;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error("unable to get employee by ID",e);
        }
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ersemployees";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Employee> employeeList = new ArrayList<>();
            while(rs.next()){
                Employee employee = new Employee();
                employee.setEmployeeId(rs.getInt("ersemployeeid"));
                employee.setFirstName(rs.getString("ersemployeefirstname"));
                employee.setLastName(rs.getString("ersemployeelastname"));
                employee.setWorkDept(rs.getString("ersemployeeworkdept"));
                employee.setJobTitle(rs.getString("ersemployeejobtitle"));
                employeeList.add(employee);
            }
            return employeeList;
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("unable to get list of all employees",e);
            return null;
        }
    }

    @Override
    public Employee updateEmployee (Employee updatedEmployee) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update ersemployees set ersemployeefirstname=?, ersemployeelastname=?, ersemployeeworkdept=?, ersemployeejobtitle=? where ersemployeeid=?";
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
            
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("unable to update by provided employee ID",e);
            return null;
        }
    }

    @Override
    public boolean deleteEmployee (int employeeId) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from ersemployees where ersemployeeid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, employeeId);
            ps.execute();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("unable to delete employee entry with the provided ID",e);
            return false;
        }
    }
}
