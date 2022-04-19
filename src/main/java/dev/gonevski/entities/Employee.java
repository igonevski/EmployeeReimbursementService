package dev.gonevski.entities;

public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String workDept;
    private String phoneNum;
    private String jobTitle;
    private long hireDate;
    private double salary;

    public Employee() {

    }

    public Employee(int employeeId, String firstName, String lastName, String workDept, String phoneNum, String jobTitle, long hireDate, double salary) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workDept = workDept;
        this.phoneNum = phoneNum;
        this.jobTitle = jobTitle;
        this.hireDate = hireDate;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getWorkDept() {
        return workDept;
    }

    public void setWorkDept(String workDept) {
        this.workDept = workDept;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public long getHireDate() {
        return hireDate;
    }

    public void setHireDate(long hireDate) {
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", workDept='" + workDept + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                '}';
    }
}
