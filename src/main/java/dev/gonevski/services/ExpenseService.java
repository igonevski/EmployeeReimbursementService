package dev.gonevski.services;

import dev.gonevski.entities.Expense;
import java.util.List;

public interface ExpenseService {

//    POST /expenses - returns a 201
    public Expense POSTExpense(Expense recordedExpense);

//    GET /expenses
    List<Expense> GETExpenseList();


//    GET /expenses?status=pending - also can get status approved or denied - save this for later!!!
//    public Expense GETExpenseByStatus(String expenseStatus);


//    GET /expenses/{expenseId} - returns a 404 if expense not found
    public Expense GETExpenseById(int expenseId);

//    GET /expenses/employees/{employeeId}
    public Expense GETExpenseByEmployee(int employeeId);

    //    PUT /expenses/{expenseId} - returns a 404 if expense not found
    public Expense PUTExpense(Expense expense);

//    PATCH /expenses/{expenseId}/approve - returns a 404 if expense not found
    public Expense PATCHApproveExpense(Expense expense);

//    PATCH /expenses/{expenseId}/deny - returns a 404 if expense not found
    public Expense PATCHDenyExpense(Expense expense);

//    DELETE /expenses/{expenseId} - returns a 404 if car not found
    public boolean DELETEExpenseById(int expenseId);

}
