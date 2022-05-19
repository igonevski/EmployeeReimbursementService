# Employee Reimbursement Service

## Project Description
The Employee Reimbursement System (ERS) is REST API that helps manage the process of reimbursing employees for expenses. Employees can be created and edited via the API. Expenses for employees can be added and updated to pending and approved. Reviewed expenses can not be edited.

## Technologies Used
1. Java
2. Javalin
3. PostgreSQL
4. Postman
5. REST
6. JUnit
7. Log4J 2

## List of features ready 
1. All expenses have a single employee as the issuer
2. Expenses start as pending and must then be approved or denied
3. Once approved or denied they CANNOT be deleted or edited
4. Negative expenses are not allowed
5. HTTP Requests to create, read, update, and delete employee and reimbursement entries

## To-do list:
1. Create an object relational mapper (ORM) to save entities to read an entity class and abstract away needing to write individualized DAO methods per entity object.
2. Inlcude improved frontend webpage design with JavaScript and React.js

## Getting Started
git clone https://github.com/igonevski/EmployeeReimbursementService.git
