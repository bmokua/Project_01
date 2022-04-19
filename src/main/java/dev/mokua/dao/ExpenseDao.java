package dev.mokua.dao;

import dev.mokua.entities.Expense;

import java.util.List;

public interface ExpenseDao {

    //POST expenses
    boolean addExpense(Expense expense);

    //GET the expenses with a status of pending
    List<Expense> getExpenseByStatus(String status);

    //GET /Expenses/12
    Expense getExpenseById(int expenseId);

    //PUT /Expenses/15
    boolean updateExpense(Expense expense);


    //DELETE/Expense/19
    boolean deleteExpense(int expenseId);

    //GET /Employees/120/Expenses
    List<Expense> getExpensesByEmployeeId(Integer employeeId);

    //POST /Employees/120/Expenses
    boolean addExpenseByEmployeeId(Expense expense);




}
