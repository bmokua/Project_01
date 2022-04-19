package dev.mokua.dao;

import dev.mokua.entities.Expense;

import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao{


    @Override
    public boolean addExpense(Expense expense) {
        return false;
    }

    @Override
    public List<Expense> getExpenseByStatus(String status) {
        return null;
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        return null;
    }

    @Override
    public boolean updateExpense(Expense expense) {
        return false;
    }

    @Override
    public boolean deleteExpense(int expenseId) {
        return false;
    }

    @Override
    public List<Expense> getExpensesByEmployeeId(Integer employeeId) {
        return null;
    }

    @Override
    public boolean addExpenseByEmployeeId(Expense expense) {
        return false;
    }
}
