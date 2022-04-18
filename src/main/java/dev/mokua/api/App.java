package dev.mokua.api;

import com.google.gson.Gson;
import dev.mokua.entities.Employee;
import dev.mokua.services.EmployeeService;
import dev.mokua.services.EmployeeServiceImpl;
import dev.mokua.services.ExpenseService;
import dev.mokua.services.ExpenseServiceImpl;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        ExpenseService expenseService = new ExpenseServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();

        Javalin javalinApp = Javalin.create();

        // POST /employees
        javalinApp.post("/employees", context -> {

            String body = context.body();
            Gson gson = new Gson();

            Employee employee = gson.fromJson(body, Employee.class);

            employeeService.addEmployee(employee);

            context.status(201);
            context.result("Added employee!!!");

        });

        javalinApp.start(7000);

    }
}
