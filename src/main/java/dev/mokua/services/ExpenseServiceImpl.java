package dev.mokua.services;

import dev.mokua.dao.ExpenseDao;
import dev.mokua.dao.ExpenseDaoImpl;
import dev.mokua.entities.Expense;
import dev.mokua.utilities.Statuses;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService{

    public ExpenseDao expenseDao;

    //Constructor for expenseDao
    public ExpenseServiceImpl() {
        expenseDao = new ExpenseDaoImpl();
    }

    @Override
    public Expense addExpense(Expense expense) {
        return this.expenseDao.addExpense(expense);
    }

    @Override
    public List<Expense> getExpenses() {
        return this.expenseDao.getExpenses();
    }

    @Override
    public List<Expense> getExpenseByStatus(String status) {
        return this.expenseDao.getExpenseByStatus(status);
    }

    @Override
    public Expense getExpenseById(int expenseId) {
        return this.expenseDao.getExpenseById(expenseId);
    }

    @Override
    public boolean updateExpense(Expense expense) {
        return this.expenseDao.updateExpense(expense);
    }

    //needs updating
    @Override
    public boolean approveExpense(int expenseId) {

        Expense expense = this.expenseDao.getExpenseById(expenseId);
        expense.setStatus(Statuses.approvedStatus);
        return this.expenseDao.updateExpense(expense);
    }

    //needs updating
    @Override
    public boolean denyExpense(int expenseId) {

        Expense expense = this.expenseDao.getExpenseById(expenseId);
        expense.setStatus(Statuses.deniedStatus);
        return this.expenseDao.updateExpense(expense);

    }

    @Override
    public boolean deleteExpense(int expenseId) {
        return this.expenseDao.deleteExpense(expenseId);
    }

    @Override
    public List<Expense> getExpensesByEmployeeId(int employeeId) {
        return this.expenseDao.getExpensesByEmployeeId(employeeId);
    }

}
