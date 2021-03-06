package dev.gonevski.api;

import com.google.gson.Gson;
import dev.gonevski.data.EmployeeDAOImplementation;
import dev.gonevski.data.ExpenseDAOImplementation;
import dev.gonevski.entities.Employee;
import dev.gonevski.entities.Expense;
import dev.gonevski.exceptions.ResourceNotFound;
import dev.gonevski.services.EmployeeService;
import dev.gonevski.services.EmployeeServiceImplementation;
import dev.gonevski.services.ExpenseService;
import dev.gonevski.services.ExpenseServiceImplementation;
import io.javalin.Javalin;

//import java.sql.SQLException;
import java.util.Objects;

// Add list utilities here - not creating own data structures, instead using a package
import java.util.List;
import java.util.ArrayList;

public class ExpenseReimbursementServiceApp {

    public static ExpenseService expenseService = new ExpenseServiceImplementation(new ExpenseDAOImplementation());
    public static EmployeeService employeeService = new EmployeeServiceImplementation(new EmployeeDAOImplementation());
    public static Gson gson = new Gson();

    public static void main(String[] args) {

        Javalin app = Javalin.create();

        app.get("/", context -> context.result("I am a healthy application"));

        // Provide the routes here as follows:


        // EMPLOYEE ROUTES

        app.post("/employees", context -> {
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            List<Employee> employeeList = employeeService.GETEmployeeList();
            // performed stream matching to the employee ID to ensure that there are no duplicate employee ID's in ERS
            boolean isEmployee = employeeList.stream().anyMatch(o -> o.getFirstName().equals(employee.getFirstName()) && o.getLastName().equals(employee.getLastName()));
            if (isEmployee) {
                context.status(400);
                context.result("Employee with that ID already exists in the database");
            }
            else {
                Employee postedEmployee = employeeService.POSTEmployee(employee);
                context.status(201);
                String employeeJson = gson.toJson(postedEmployee);
                context.result(employeeJson);
            }
        });

        app.get("/employees", context -> {
            List<Employee> employeeList = employeeService.GETEmployeeList();
            String employeeListJSON = gson.toJson(employeeList);
            context.status(200);
            context.result(employeeListJSON);
        });

        app.get("/employees/{employeeId}", context -> {
            int employeeId = Integer.parseInt(context.pathParam("employeeId"));
            try {
                String employeeJSON = gson.toJson(employeeService.GETEmployeeById(employeeId));
                context.status(200);
                context.result(employeeJSON);
            }
            catch(ResourceNotFound e){
                context.status(404);
                context.result("No employee was found with ID: "+ employeeId);
            }
        });

        app.put("/employees/{employeeId}", context -> {
            int employeeId = Integer.parseInt(context.pathParam("employeeId"));
            String body = context.body();
            Employee employee = gson.fromJson(body,Employee.class);
            employee.setEmployeeId(employeeId);
            employeeService.PUTEmployee(employee);
            context.status(200);
            context.result("The Employee field has been updated.");
        });

        app.delete("/employees/{employeeId}", context -> {
            int employeeId = Integer.parseInt(context.pathParam("employeeId"));
            try {
                List<Expense> expenseList = expenseService.GETExpenseList();

                // performed stream matching to the employee iD because there is a constraint to not remove employees with expenses!
                boolean removeEmployee = expenseList.stream().anyMatch(o -> o.getEmployeeId() == employeeId);
                if (removeEmployee) {
                    context.status(400);
                    context.result("Employees with recorded expenses cannot be removed from the database.");
                }
                else {
                    boolean result = employeeService.DELETEEmployeeById(employeeId);
                    if (result) {
                        context.status(200);
                        context.result("Employee field with the provided ID has been deleted from the database.");
                    }
                    else {
                        context.status(404);
                        context.result("Employee with the provided ID: " + employeeId + " has not been found.");
                    }
                }
            }
            catch(ResourceNotFound e){
                context.status(500);
                context.result(e.getMessage());
            }
        });


        // EXPENSE ROUTES

        app.post("/expenses", context -> {
            String body = context.body();
            Expense expense = gson.fromJson(body,Expense.class);
            //expense.setExpenseStatus("Pending");
            Expense newExpense = expenseService.POSTExpense(expense);
            context.status(201);
            String expenseRecordJSON = gson.toJson(newExpense);
            context.result(expenseRecordJSON);
        });

        app.get("/expenses",context -> {
            String status = context.queryParam("status");
            List<Expense> expenseList = expenseService.GETExpenseList();
            if(status==null){
                context.result(gson.toJson(expenseList));
            }
            else{
                List<Expense> requestedStatus = new ArrayList<>();
                for(Expense expense : expenseList){
                    if(expense.getExpenseStatus().equals(status)){
                        requestedStatus.add(expense);
                    }
                }
                String expenseRecordsListJSON = gson.toJson(requestedStatus);
                context.status(200);
                context.result(expenseRecordsListJSON);
            }
        });

        app.get("/expenses/{expenseId}", context -> {
            int expenseId = Integer.parseInt(context.pathParam("expenseId"));
            try {
                String expensesJSON = gson.toJson(expenseService.GETExpenseById(expenseId));
                context.result(expensesJSON);
            }
            catch(ResourceNotFound e){
                context.status(404);
                context.result("Expense ID: " + expenseId + " has not been found in the database.");
            }
        });

        app.put("/expenses/{expenseId}",context -> {
            try {
                int expenseId = Integer.parseInt(context.pathParam("expenseId"));
                String body = context.body();
                Expense newExpense = gson.fromJson(body,Expense.class);
                newExpense.setExpenseId(expenseId);
                if(Objects.equals(newExpense.getExpenseStatus(),"Pending")){
                    expenseService.PUTExpense(newExpense);
                    context.status(200);
                    context.result("The Expense field has been updated.");
                }
                else{
                    context.status(400);
                    context.result("Expense fields that have confirmed statuses cannot be changed.");
                }}
            catch(ResourceNotFound e){
                context.status(404);
                context.result(e.getMessage());
            }
        });

        app.patch("/expenses/{expenseId}/approve",context -> {
            try {
                int expenseId = Integer.parseInt(context.pathParam("expenseId"));
                Expense newExpense = expenseService.GETExpenseById(expenseId);
                if(Objects.equals(newExpense.getExpenseStatus(), "Pending")){
                    expenseService.PATCHApproveExpense(newExpense);
                    context.result("Expense has been updated to the approved status.");
                    context.status(200);
                }
                else {
                    context.status(400);
                    context.result("Expense fields that have confirmed statuses cannot be updated.");
                }
            }
            catch(ResourceNotFound e){
                context.status(404);
                context.result(e.getMessage());
            }
        });

        app.patch("/expenses/{expenseId}/deny",context -> {
            try {
                int expenseId = Integer.parseInt(context.pathParam("expenseId"));
                Expense newExpense = expenseService.GETExpenseById(expenseId);
                if(Objects.equals(newExpense.getExpenseStatus(),"Pending")){
                    expenseService.PATCHDenyExpense(newExpense);
                    context.status(200);
                    context.result("Expense has been updated to the denied status.");
                }
                else {
                    context.status(400);
                    context.result("Expense fields that have confirmed statuses cannot be updated.");
                }
            }
            catch(ResourceNotFound e){
                context.result(e.getMessage());
                context.status(404);

            }
        });

        app.delete("/expenses/{expenseId}", context -> {
            try {
                int expenseId = Integer.parseInt(context.pathParam("expenseId"));
                Expense newExpense = expenseService.GETExpenseById(expenseId);
                if (Objects.equals(newExpense.getExpenseStatus(), "Pending")) {
                    expenseService.DELETEExpenseById(expenseId);
                    context.status(200);
                    context.result("Expense deleted");
                }
                else {
                    context.status(400);
                    context.result("Expense fields that have confirmed statuses cannot be deleted.");
                }
            }
            catch(ResourceNotFound e){
                context.status(500);
                context.result(e.getMessage());
            }
        });


        app.get("/employees/{employeeId}/expenses",context -> {
            try{
                int employeeId = Integer.parseInt(context.pathParam("employeeId"));
                List<Expense> expenseList = expenseService.GETExpenseList();
                List<Expense> matchingIDList = new ArrayList<>();
                for(Expense expense : expenseList) {
                    if (expense.getEmployeeId() == (employeeId)) {
                        matchingIDList.add(expense);
                    }
                }
                String expenseListJSON = gson.toJson(matchingIDList);
                String employeeJSON = gson.toJson(employeeService.GETEmployeeById(employeeId));
                // String expensesJSON = gson.toJson(expenseService.GETExpenseByEmployee(employeeId));
                context.status(200);
                context.result("The following employee and expense fields have been retrieved: " + employeeJSON + expenseListJSON);
            }
            catch(ResourceNotFound e){
                context.status(404);
            }
        });

        app.post("/employees/{employeeId}/expenses", context -> {
            int employeeId = Integer.parseInt(context.pathParam("employeeId"));
            String body = context.body();
            Expense newExpense = gson.fromJson(body, Expense.class);
            newExpense.setEmployeeId(employeeId);
            newExpense.setExpenseStatus("Pending");
            if(newExpense.getExpenseAmount() < 0 )
            {
                context.status(400);
                context.result("The following field cannot be created with a negative expense amount: "+ newExpense);
            }
            else{
                Expense nextExpense = expenseService.POSTExpense(newExpense);
                context.status(201);
                String expenseRecordJSON = gson.toJson(nextExpense);
                context.result(expenseRecordJSON);
                context.result("A new expense field has been created and added to the system.");
            }
        });

        app.start(5000);
        // Must be port 5000 to work on Elastic Beanstalk

    }
}
