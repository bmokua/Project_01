package dev.mokua.services;

import dev.mokua.entities.Expense;

import java.util.List;

public interface ExpenseService {

    //    POST /expenses
    boolean addExpense(Expense expense);

    //    GET /expenses?status=pending
    List<Expense> getExpenseByStatus(String status);

    //    GET /expenses/12
    Expense getExpense(Integer expenseId);

    //    PUT /expenses/15
    boolean updateExpense(Integer expense);

    //    PATCH /expenses/20/approve
    boolean approveExpense(Integer expenseId);

    //    PATCH /expenses/20/deny
    boolean denyExpense(Integer expenseId);

    //    DELETE/expenses/19
    boolean deleteExpense(Integer expenseId);

    //    GET /employees/120/expenses
    List<Expense> getExpensesByEmployeeId(Integer employeeId);

    //    POST /employees/120/expenses
    boolean addExpenseByEmployeeId(Expense expense);
}
