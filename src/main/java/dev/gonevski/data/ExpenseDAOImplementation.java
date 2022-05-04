package dev.gonevski.data;

import dev.gonevski.entities.Expense;
import dev.gonevski.exceptions.ResourceNotFound;
import dev.gonevski.utilities.ConnectionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOImplementation implements ExpenseDAO {

    private Logger logger = LogManager.getLogger(ExpenseDAOImplementation.class.getName());

    @Override
    public Expense addExpense(Expense addedExpense) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into ersexpenses values(default,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, addedExpense.getEmployeeId());
            ps.setDouble(2, addedExpense.getExpenseAmount());
            ps.setLong(3, addedExpense.getExpenseDate());
            ps.setString(4, addedExpense.getExpenseType());
            ps.setString(5, addedExpense.getExpenseStatus());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

            int addedID = rs.getInt("ersexpenseid");
            System.out.println(addedID);
            addedExpense.setExpenseId(addedID);
            return addedExpense;
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("unable to create Expense",e);
            return null;
        }
    }



    @Override
    public Expense getExpenseById(int expenseId) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ersexpenses where ersexpenseid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseId);
            ResultSet rs = ps.executeQuery();

            //look into exceptions here
            rs.next();
            Expense expense = new Expense();
            expense.setExpenseId(rs.getInt("ersexpenseid"));
            expense.setEmployeeId(rs.getInt("ersemployeeid"));
            expense.setExpenseAmount(rs.getDouble("ersexpenseamount"));
            expense.setExpenseDate(rs.getLong("ersexpensedate"));
            expense.setExpenseType(rs.getString("ersexpensetype"));
            expense.setExpenseStatus(rs.getString("ersexpensestatus"));
            return expense;

        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error("unable to retrieve Expense by ID",e);
        }
        return null;
    }

    @Override
    public Expense getExpenseByEmployee(int employeeId) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ersexpenses where ersemployeeid = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employeeId);
            ResultSet rs = ps.executeQuery();

            //look into exceptions here
            rs.next();
            Expense expense = new Expense();
            expense.setExpenseId(rs.getInt("ersexpenseid"));
            expense.setEmployeeId(rs.getInt("ersemployeeid"));
            expense.setExpenseAmount(rs.getDouble("ersexpenseamount"));
            expense.setExpenseDate(rs.getLong("ersexpensedate"));
            expense.setExpenseType(rs.getString("ersexpensetype"));
            expense.setExpenseStatus(rs.getString("ersexpensestatus"));
            return expense;

        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error("unable to get expense by employee ID",e);
        }
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ersexpenses";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenseList = new ArrayList<>();
            while(rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("ersexpenseid"));
                expense.setEmployeeId(rs.getInt("ersemployeeid"));
                expense.setExpenseAmount(rs.getDouble("ersexpenseamount"));
                expense.setExpenseDate(rs.getLong("ersexpensedate"));
                expense.setExpenseType(rs.getString("ersexpensetype"));
                expense.setExpenseStatus(rs.getString("ersexpensestatus"));
                expenseList.add(expense);
            }
            return expenseList;
        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error("unable to get list of all expenses",e);
        }
        return null;
    }

    @Override
    public Expense updateExpense(Expense updatedExpense) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update ersexpenses set ersemployeeid=?, ersexpenseamount=?, ersexpensedate=?, ersexpensetype=?, ersexpensestatus=? where ersexpenseid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,updatedExpense.getEmployeeId());
            ps.setDouble(2,updatedExpense.getExpenseAmount());
            ps.setLong(3, updatedExpense.getExpenseDate());
            ps.setString(4, updatedExpense.getExpenseType());
            ps.setString(5, updatedExpense.getExpenseStatus());
            ps.setInt(6,updatedExpense.getExpenseId());

            int rowsUpdated = ps.executeUpdate();

            if(rowsUpdated == 0){
                throw new ResourceNotFound(updatedExpense.getExpenseId());
            }
            return updatedExpense;

        }
        catch (SQLException e) {
            e.printStackTrace();
            logger.error("unable to update by provided expense ID",e);
            return null;
        }
    }

    @Override
    public boolean deleteExpense(int expenseId) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from ersexpenses where ersexpenseid=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseId);
            ps.execute();
            return true;
        }
        catch(SQLException e){
            e.printStackTrace();
            logger.error("unable to delete expense entry with the provided ID",e);
            return false;
        }

    }

}
