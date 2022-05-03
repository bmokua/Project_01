package dev.mokua.services;

import dev.mokua.entities.Expense;

import java.util.List;

public interface ExpenseService {

    //    POST /expenses
    boolean addExpense(Expense expense);

    //GET all expenses
    List<Expense> getExpenses();

    //    GET /expenses?status=pending
    List<Expense> getExpenseByStatus(String status);

    //    GET /expenses/12
    Expense getExpenseById(int expenseId);

    //    PUT /expenses/15
    boolean updateExpense(Expense expense);

    //    PATCH /expenses/20/approve
    boolean approveExpense(int expenseId);

    //    PATCH /expenses/20/deny
    boolean denyExpense(int expenseId);

    //    DELETE/expenses/19
    boolean deleteExpense(int expenseId);

    //    GET /employees/120/expenses
    List<Expense> getExpensesByEmployeeId(int employeeId);

    //    POST /employees/120/expenses

}