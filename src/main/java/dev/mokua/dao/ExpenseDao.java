package dev.mokua.dao;

import dev.mokua.entities.Expense;

import java.util.List;

public interface ExpenseDao {

    //POST expenses
    void addExpense(Expense expense);

    //GET the expenses with a status of pending
    List<Expense> getExpenseByStatus(String status);

    //GET /Expenses/12
    Expense getExpense(Integer expenseId);

    //PUT /Expenses/15
    void updateExpense(Expense expense);

    //PATCH /Expenses/20/Approve
    void approveExpense(Integer expenseId);

    //PATCH /Expense/20/Deny
    void denyExpense(Integer expenseId);

    //DELETE/Expense/19
    void deleteExpense(Integer expenseId);

    //GET /Employees/120/Expenses
    List<Expense> getExpensesByEmployeeId(Integer employeeId);

    //POST /Employees/120/Expenses
    void addExpensesByEmployeeId(Expense espense, Integer employeeId);




}
