package dev.mokua.dao;

import dev.mokua.entities.Employee;
import dev.mokua.entities.Expense;

import java.util.List;

public interface ExpenseDao {

    //POST expenses
    boolean addExpense(Expense expense);

    //GET all expenses
    List<Expense> getExpenses();

    //GET the expenses with a status of pending
    List<Expense> getExpenseByStatus(String status);

    //GET /Expenses/12
    Expense getExpenseById(int expenseId);

    //PUT /Expenses/15
    boolean updateExpense(Expense expense);

    //    PATCH /expenses/20/approve
    boolean approveExpense(int expenseId);

    //    PATCH /expenses/20/deny
    boolean denyExpense(int expenseId);


    //DELETE/Expense/19
    boolean deleteExpense(int expenseId);

    //GET /Employees/120/Expenses
    List<Expense> getExpensesByEmployeeId(int employeeId);




}
