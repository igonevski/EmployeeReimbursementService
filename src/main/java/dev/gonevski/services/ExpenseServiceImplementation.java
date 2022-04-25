package dev.gonevski.services;

import dev.gonevski.data.ExpenseDAOImplementation;
import dev.gonevski.data.ExpenseDAO;
import dev.gonevski.entities.Expense;

// Using an existing data structure package
import java.util.List;

public class ExpenseServiceImplementation implements ExpenseService {

    private ExpenseDAO expenseDAO;

    public ExpenseServiceImplementation(ExpenseDAOImplementation newExpenseDAO) {
        this.expenseDAO = (ExpenseDAO) newExpenseDAO;
    }

    @Override
    public Expense POSTExpense(Expense recordedExpense) {
        Expense newExpense = this.expenseDAO.addExpense(recordedExpense);
        return newExpense;
    }

    @Override
    public List<Expense> GETExpenseList() {
        return this.expenseDAO.getAllExpenses();
    }

//  Save this for later - after finishing up the rest of the options! - @Override
//  public Expense GETExpenseByStatus(String expenseStatus) {}

    @Override
    public Expense GETExpenseById(int expenseId) {
        return this.expenseDAO.getExpenseById(expenseId);
    }

    @Override
    public Expense GETExpenseByEmployee(int employeeId) {
        return this.expenseDAO.getExpenseByEmployee(employeeId);
    }

    @Override
    public Expense PUTExpense(Expense expense) {
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public Expense PATCHApproveExpense(Expense expense) {
        expense.setExpenseStatus("Approved");
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public Expense PATCHDenyExpense(Expense expense) {
        expense.setExpenseStatus("Denied");
        return this.expenseDAO.updateExpense(expense);
    }

    @Override
    public boolean DELETEExpenseById(int expenseId) {
        return this.expenseDAO.deleteExpense(expenseId);
    }

}
