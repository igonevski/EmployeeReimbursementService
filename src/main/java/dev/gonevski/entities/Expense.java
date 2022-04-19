package dev.gonevski.entities;

public class Expense {

    private int expenseId;
    private int employeeId;
    private double expenseAmount;
    private long expenseDate;
    private String expenseType;
    private String expenseStatus;

    public Expense() {
    }

    public Expense(int expenseId, int employeeId, double expenseAmount, long expenseDate, String expenseType, String expenseStatus) {
        this.expenseId = expenseId;
        this.employeeId = employeeId;
        this.expenseAmount = expenseAmount;
        this.expenseDate = expenseDate;
        this.expenseType = expenseType;
        this.expenseStatus = expenseStatus;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(double expenseAmount) {
        this.expenseAmount = expenseAmount;
    }

    public long getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(long expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseType() {
        return expenseType;
    }

    public void setExpenseType(String expenseType) {
        this.expenseType = expenseType;
    }

    public String getExpenseStatus() {
        return expenseStatus;
    }

    public void setExpenseStatus(String expenseStatus) {
        this.expenseStatus = expenseStatus;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", employeeId=" + employeeId +
                ", expenseAmount=" + expenseAmount +
                ", expenseDate=" + expenseDate +
                ", expenseType='" + expenseType + '\'' +
                ", expenseStatus='" + expenseStatus + '\'' +
                '}';
    }
}
