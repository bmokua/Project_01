package dev.mokua.dao;

import dev.mokua.entities.Expense;

import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao{

    @Override
    public void addExpense(Expense expense) {

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
    public void updateExpense(Expense expense) {

    }

    @Override
    public void approveExpense(Integer expenseId) {

    }

    @Override
    public void denyExpense(Integer expenseId) {

    }

    @Override
    public void deleteExpense(Integer expenseId) {

    }

    @Override
    public List<Expense> getExpensesByEmployeeId(Integer employeeId) {
        return null;
    }

    @Override
    public void addExpensesByEmployeeId(Expense espense, Integer employeeId) {

    }
}