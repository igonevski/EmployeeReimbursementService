package dev.gonevski.entities;

public class Employee {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String workDept;
    private String jobTitle;

    public Employee() {

    }

    public Employee(int employeeId, String firstName, String lastName, String workDept, String jobTitle) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.workDept = workDept;
        this.jobTitle = jobTitle;
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

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", workDept='" + workDept + '\'' +
                ", jobTitle='" + jobTitle +
                '}';
    }
}
