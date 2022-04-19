package dev.gonevski.data;

import dev.gonevski.entities.Expense;
import java.util.List;

public interface ExpenseDAO {

    Expense addExpense (Expense addedExpense);
    Expense getExpenseById (int expenseId);
    Expense getExpenseByEmployee (int employeeId);
    List<Expense> getAllExpenses();
    Expense updateExpense (Expense updatedExpense);
    boolean deleteExpense (int expenseId);

}
