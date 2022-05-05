package dev.mokua.api;

import com.google.gson.Gson;
import dev.mokua.entities.Employee;
import dev.mokua.entities.Expense;
import dev.mokua.exceptions.ResourceNotFound;
import dev.mokua.services.EmployeeService;
import dev.mokua.services.EmployeeServiceImpl;
import dev.mokua.services.ExpenseService;
import dev.mokua.services.ExpenseServiceImpl;
import dev.mokua.utilities.Statuses;
import io.javalin.Javalin;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//        POST /employees
//        returns a 201
//        GET /employees
//        GET /employees/120
//        returns a 404 if employee not found
//        PUT /employees/150
//        returns a 404 if employee not found
//        DELETE /employees/190
//        returns a 404 if employee not found

public class App {
    public static void main(String[] args) {

//        String secureConnection = System.getenv("DatabaseUrl");
//        System.out.println(secureConnection);

        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());

        System.out.println(timeStamp);

        ExpenseService expenseService = new ExpenseServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();

        Javalin jApp = Javalin.create();
        jApp.get("/", context -> {
            context.result("Application deployed");
            context.status(200);
        });

        // POST /employees
        jApp.post("/employees", context -> {

            String body = context.body();
            Gson gson = new Gson();
            String response ="";

            Employee employee = gson.fromJson(body, Employee.class);
            boolean successfulAdd = employeeService.addEmployee(employee).getEmployeeId()!=0;
            if(successfulAdd){
                context.status(201);
                context.result("Added employee");
            }else{
                context.status(404);
                context.result("Add not successful");
            }


        });

        //Get /employees
        jApp.get("/employees", context -> {
           Gson gson = new Gson();
            try {
                List<Employee> employeeList = employeeService.getEmployees();
                String response = gson.toJson(employeeList);
                context.status(201);
                context.result(response);
            }catch (ResourceNotFound e) {
                context.status(404);
                context.result("Employees not found");
            }
        });

        //Get/employees/120
        jApp.get("/employees/{id}", context -> {
            Gson gson = new Gson();

            int employeeId = Integer.parseInt(context.pathParam("id"));
            try {
                String employeeJSON = gson.toJson(employeeService.getEmployeeById(employeeId));
                context.result(employeeJSON);
                context.status(201);
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("This employee" + employeeId +  "was not found");
            }


        });

        //PUT /employees/150
        jApp.put("/employees/{id}",context -> {

            Gson gson = new Gson();
            String body = context.body();

            String response = "";
            //int employeeId = Integer.valueOf(context.pathParam("id"));
            Employee employee = gson.fromJson(body, Employee.class);

            boolean successfulUpdate = employeeService.updateEmployee(employee);
            if(successfulUpdate){
                context.status(201);
                response = "Successful update";
            }else{
                context.status(404);
                response= "Update not successful";
            }

            context.result(response);
        });

        //DELETE /employees/190
        jApp.delete("/employees/{id}",context -> {

            String response = "";
            int employeeId = Integer.valueOf(context.pathParam("id"));

            boolean successfulUpdate = employeeService.deleteEmployeeById(employeeId);
            if(successfulUpdate){
                context.status(201);
                response = "Delete was successful";
            }else{
                context.status(404);
                response = "Delete not successful";
            }

            context.result(response);
        });


//        Expenses routes
//        POST /expenses
//        returns a 201
//        GET /expenses
//        GET /expenses?status=pending
//        also can get status approved or denied
//        GET /expenses/12
//        returns a 404 if expense not found
//        PUT /expenses/15
//        returns a 404 if expense not found
//        PATCH /expenses/20/approve
//        returns a 404 if expense not found
//        PATCH /expenses/20/deny
//        returns a 404 if expense not found
//        DELETE /expenses/19
//        returns a 404 if car not found
//        It is common for REST routes to be nested
//
//        GET /employees/120/expenses
//        returns expenses for employee 120
//        POST /employees/120/expenses
//        adds an expense to employee 120


        //POST/expenses
        jApp.post("/expenses", context -> {

            String body = context.body();
            Gson gson = new Gson();

            Expense expense = gson.fromJson(body, Expense.class);

           boolean addedExpense =  expenseService.addExpense(expense).getExpenseId()!=0;

           if(addedExpense){

               context.status(201);
               context.result("Added expense!!!");

           }else{
               context.status(404);
               context.result("Add not successful");
           }

        });

        //Get/expenses
        jApp.get("/expenses", context -> {
            Gson gson = new Gson();
            try {
                List<Expense> expenseList = expenseService.getExpenses();
                String response = gson.toJson(expenseList);
                context.status(201);
                context.result(response);
            }catch (Exception e){
                context.status(404);
                context.result("Expenses not found");
            }
        });

        // GET /expenses/status
        jApp.get("/expenses/status", context -> {
            Gson gson = new Gson();

            List<Expense> expenseList = new ArrayList<>();
            String expenseStatus = context.queryParam("status");

                if(expenseStatus != null){
                    expenseList = expenseService.getExpenseByStatus(expenseStatus);

                }else {
                    expenseList = expenseService.getExpenses();
                }

                String response = gson.toJson(expenseList);

                    context.status(201);
                    context.result(response);

        });

        //GET /expenses/12
        jApp.get("/expenses/{id}", context -> {
            Gson gson = new Gson();
            int expenseId = Integer.parseInt(context.pathParam("id"));
            try {
                String expenseJSON = gson.toJson(expenseService.getExpenseById(expenseId));
                context.result(expenseJSON);
                context.status(201);
            } catch (ResourceNotFound e) {
                context.status(404);
                context.result("This expense" + expenseId+  "was not found.");
            }

        });

        //PUT /expenses/15
        jApp.put("/expenses/{id}",context -> {

            Gson gson = new Gson();
            String body = context.body();

            String response = "";
            int expenseId = Integer.valueOf(context.pathParam("id"));
            Expense expense = gson.fromJson(body, Expense.class);

            boolean successfulUpdate = expenseService.updateExpense(expense);
            if(successfulUpdate){
                context.status(201);
                response = "Successful update.";
            }else{
                context.status(404);
                response= "Update not successful.";
            }

            context.result(response);
        });

        //PATCH /expenses/20/approve
        //returns a 404 if expense not found
        jApp.patch("/expenses/{id}/approve", context -> {
            Gson gson = new Gson();

            try {
                int expenseId = Integer.valueOf(context.pathParam("id"));
                Expense expense = expenseService.getExpenseById(expenseId);
                if(expense.getStatus().equalsIgnoreCase("PENDING")) {
                    expenseService.approveExpense(expenseId);
                    context.status(201);
                    context.result("Approved expense.");
                }else{
                    context.status(401);
                    context.result("Cannot Update expense");
                }
            }catch(Exception e){
                context.status(404);
                context.result("Expense not approved.");
            }
        });

        // PATCH /expenses/20/deny
        //returns a 404 if expense not found
        jApp.patch("/expenses/{id}/denied", context -> {

            Gson gson = new Gson();

            try {
                int expenseId = Integer.valueOf(context.pathParam("id"));
                Expense expense = expenseService.getExpenseById(expenseId);
                if(expense.getStatus().equalsIgnoreCase("PENDING")) {
                    expenseService.denyExpense(expenseId);
                    context.status(201);
                    context.result("Denied expense.");
                }else {
                    context.status(404);
                    context.result("Cannot update expense");
                }
            }catch(Exception e){
                context.status(201);
                context.result("Denied not approved.");
            }
        });

        //DELETE /expenses/19
        jApp.delete("/expenses/{id}",context -> {


            String response = "";
            int expenseId = Integer.valueOf(context.pathParam("id"));

           boolean successfulUpdate = expenseService.deleteExpense(expenseId);

            if(successfulUpdate){
                context.status(201);
                response = "Delete was successful";
            }else{
                context.status(404);
                response = "Delete not successful";
            }

            context.result(response);
        });


        //GET /employees/120/expenses
        // returns expenses for employee 120
        jApp.get("/employees/{employeeId}/expenses", context -> {

            Gson gson = new Gson();
           int employeeId = Integer.parseInt(context.pathParam("employeeId"));
           try {

               List<Expense> expenseList = expenseService.getExpensesByEmployeeId(employeeId);
               String response = gson.toJson(expenseList);

               context.status(201);
               context.result(response);
           }catch(Exception e){
               context.status(400);
               context.result("The employee with id " + employeeId + " cannot be found.");
           }

        });


        jApp.start(6000);

    }
}
