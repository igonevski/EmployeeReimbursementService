package dev.gonevski.services;

import dev.gonevski.entities.Expense;
import java.util.List;

public interface ExpenseService {

//    POST /expenses
//    returns a 201
    public Expense recordExpense(Expense recordedExpense);
//    GET /expenses
    List<Expense> GETExpenseList();
//    GET /expenses?status=pending
//    also can get status approved or denied
    public Expense GETExpenseByStatus(String expenseStatus);
//    GET /expenses/12
//    returns a 404 if expense not found
    public Expense GETExpenseById(int expenseId);
//    PUT /expenses/15
//    returns a 404 if expense not found
    public Expense PUTExpenseById(int expenseId);
//    PATCH /expenses/20/approve
//    returns a 404 if expense not found
    public Expense PATCHApproveExpenseById(int expenseId);
//    PATCH /expenses/20/deny
//    returns a 404 if expense not found
    public Expense PATCHDenyExpenseById(int expenseId);
//    DELETE /expenses/19
//    returns a 404 if car not found
    public Expense DELETEExpenseById(int expenseId);

}
