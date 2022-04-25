package dev.gonevski.data;

import dev.gonevski.entities.Expense;
import dev.gonevski.exceptions.ResourceNotFound;
import dev.gonevski.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDAOImplementation implements ExpenseDAO {

    @Override
    public Expense addExpense(Expense addedExpense) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "insert into ers_expenses values(default,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, addedExpense.getEmployeeId());
            ps.setDouble(2, addedExpense.getExpenseAmount());
            ps.setString(3, addedExpense.getExpenseType());
            ps.setString(4, addedExpense.getExpenseStatus());
            ps.setLong(4, addedExpense.getExpenseDate());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys();

            rs.next();

            int addedID = rs.getInt("ers_expense_id");
            System.out.println(addedID);
            addedExpense.setExpenseId(addedID);
            return addedExpense;
        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public Expense getExpenseById(int expenseId) {

        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ers_expenses where ers_expense_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseId);
            ResultSet rs = ps.executeQuery();

            rs.next();
            Expense expense = new Expense();
            expense.setExpenseId(rs.getInt("ers_expense_id"));
            expense.setEmployeeId(rs.getInt("ers_employee_id"));
            expense.setExpenseAmount(rs.getDouble("ers_expense_amount"));
            expense.setExpenseDate(rs.getLong("ers_expense_date"));
            expense.setExpenseType(rs.getString("ers_expense_type"));
            expense.setExpenseStatus(rs.getString("ers_expense_status"));
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense getExpenseByEmployee(int employeeId) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ers_expenses where ers_employee_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,employeeId);
            ResultSet rs = ps.executeQuery();

            rs.next();
            Expense expense = new Expense();
            expense.setExpenseId(rs.getInt("ers_expense_id"));
            expense.setEmployeeId(rs.getInt("ers_employee_id"));
            expense.setExpenseAmount(rs.getDouble("ers_expense_amount"));
            expense.setExpenseDate(rs.getLong("ers_expense_date"));
            expense.setExpenseType(rs.getString("ers_expense_type"));
            expense.setExpenseStatus(rs.getString("ers_expense_status"));
            return expense;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Expense> getAllExpenses() {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "select * from ers_expenses";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            List<Expense> expenseList = new ArrayList<>();
            while(rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("ers_expense_id"));
                expense.setEmployeeId(rs.getInt("ers_employee_id"));
                expense.setExpenseAmount(rs.getDouble("ers_expense_amount"));
                expense.setExpenseDate(rs.getLong("ers_expense_date"));
                expense.setExpenseType(rs.getString("ers_expense_type"));
                expense.setExpenseStatus(rs.getString("ers_expense_status"));
                expenseList.add(expense);
            }
            return expenseList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Expense updateExpense(Expense updatedExpense) {
        try {
            Connection conn = ConnectionUtil.createConnection();
            String sql = "update ers_expenses set ers_employee_id=?, ers_expense_amount=?, ers_expense_date=?, ers_expense_type=?, ers_expense_status=? where ers_expense_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,updatedExpense.getEmployeeId());
            ps.setString(2, updatedExpense.getExpenseType());
            ps.setDouble(3,updatedExpense.getExpenseAmount());
            ps.setString(4, updatedExpense.getExpenseStatus());
            ps.setLong(5, updatedExpense.getExpenseDate());
            ps.setInt(6,updatedExpense.getExpenseId());

            int rowsUpdated =ps.executeUpdate();

            if(rowsUpdated == 0){
                throw new ResourceNotFound(updatedExpense.getExpenseId());
            }
            return updatedExpense;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteExpense(int expenseId) {
        try{
            Connection conn = ConnectionUtil.createConnection();
            String sql = "delete from ers_expenses where ers_expense_id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseId);
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;

        }

    }

}
