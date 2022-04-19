package dev.mokua.services;

import dev.mokua.entities.Expense;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{


    @Override
    public boolean addExpense(Expense expense) {
        return false;
    }

    @Override
    public List<Expense> getExpenseByStatus(String status) {
        return null;
    }

    @Override
    public Expense getExpense(Integer expenseId) {
        return null;
    }

    @Override
    public boolean updateExpense(Integer expense) {
        return false;
    }

    @Override
    public boolean approveExpense(Integer expenseId) {
        return false;
    }

    @Override
    public boolean denyExpense(Integer expenseId) {
        return false;
    }

    @Override
    public boolean deleteExpense(Integer expenseId) {
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
