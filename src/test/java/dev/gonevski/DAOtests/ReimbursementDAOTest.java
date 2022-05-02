package dev.gonevski.DAOtests;

import dev.gonevski.entities.Expense;
import dev.gonevski.data.ExpenseDAO;
import dev.gonevski.data.ExpenseDAOImplementation;
//import dev.gonevski.exceptions.ResourceNotFound;
import java.util.List;
import org.junit.jupiter.api.*;

//NOTE: BECAUSE THIS TABLE IS CONSTRAINED TO THE OTHER ONE, WHEN I DELETE SOMETHING IN THE TEST FROM THE OTHER,
// THEN IT BECOMES A PROBLEM WHEN I GO TO DO A TEST WITH THIS TABLE BECAUSE I DELETED AN ENTRY THERE
// AND SO I NEEDED TO READJUST THE INPUT TEST CASES HERE SO THAT THE EXPENSE IS INSERTED/UPDATED INTO SOMETHING
// THAT ACTUALLY EXISTS IN THE EMPLOYEE TABLE!!!

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)// to run tests in order
public class ReimbursementDAOTest {

static ExpenseDAO expenseDAO = new ExpenseDAOImplementation();
static Expense testExpense = null;

    @Test
    @Order(1)
    public void addExpense_test() {
        Expense bill = new Expense(0, 2, 100.00, System.currentTimeMillis(), "bill", "pending");
        Expense savedExpense = expenseDAO.addExpense(bill);
        ReimbursementDAOTest.testExpense = savedExpense;
        Assertions.assertNotEquals(0, savedExpense.getExpenseId());
    }

    @Test
    @Order(2)
    public void getExpenseById_test() {
        Expense retrievedExpense = expenseDAO.getExpenseById(testExpense.getExpenseId());
        System.out.println(retrievedExpense);
        Assertions.assertEquals("bill", retrievedExpense.getExpenseType());
    }

    @Test
    @Order(3)
    public void getExpenseByEmployee_test() {
        Expense retrievedExpense = expenseDAO.getExpenseByEmployee(testExpense.getEmployeeId());
        System.out.println(retrievedExpense);
        Assertions.assertEquals(2, retrievedExpense.getEmployeeId());
    }

    @Test
    @Order(4)
    public void updateExpense_test() {
        ReimbursementDAOTest.testExpense.setExpenseAmount(100.01);
        expenseDAO.updateExpense(testExpense);
        Expense retrievedExpense = expenseDAO.getExpenseById(testExpense.getExpenseId());
        Assertions.assertEquals(100.01, retrievedExpense.getExpenseAmount());
    }

    @Test
    @Order(5)
    public void deleteExpense_test() {
        boolean result = expenseDAO.deleteExpense(testExpense.getExpenseId());
        Assertions.assertTrue(result);
    }

    @Test
    @Order(6)
    public void getAllExpenses_test() {
        Expense a = new Expense(0, 4, 100.02, System.currentTimeMillis(), "project", "approved");
        Expense b = new Expense(0, 4, 100.03, System.currentTimeMillis(), "bill", "pending");
        Expense c = new Expense(0, 4, 100.04, System.currentTimeMillis(), "collaboration", "denied");
        expenseDAO.addExpense(a);
        expenseDAO.addExpense(b);
        expenseDAO.addExpense(c);
        List<Expense> expenses = expenseDAO.getAllExpenses();
        int totalExpenses = expenses.size();
        Assertions.assertTrue(totalExpenses >= 3);
    }

}
