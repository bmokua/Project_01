package dev.mokua.daotests;

import dev.mokua.dao.ExpenseDao;
import dev.mokua.dao.ExpenseDaoImpl;
import dev.mokua.entities.Employee;
import dev.mokua.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ExpenseDaoTests {
    static ExpenseDao expenseDao = new ExpenseDaoImpl();
    static Expense testExpense = null;

    @Test
    @Order(1)
    void add_expense_test(){

        Expense firstExpense = new Expense(1, 0, 500.00, 0,  "PENDING",0);
        ExpenseDaoTests.testExpense = expenseDao.addExpense(firstExpense);
        Assertions.assertTrue(testExpense.getExpenseId()!=0);

    }

    @Test
    @Order(2)
    void get_all_expenses(){
        Expense firstExpense = new Expense(2, 0, 500.00, 0,  "PENDING",0);
        Expense secondExpense = new Expense(1, 0, 500.00, 0,  "PENDING",0);
        Expense thirdExpense = new Expense(3, 0, 500.00, 0,  "PENDING",0);

        expenseDao.addExpense(firstExpense);
        expenseDao.addExpense(secondExpense);
        expenseDao.addExpense(thirdExpense);

        List<Expense> newExpenseList = expenseDao.getExpenses();
        //  int totalEmployees = newEmployeeList.size(); // if list did not work and was not tested this might fail regardless
        Assertions.assertTrue(newExpenseList != null  && !newExpenseList.isEmpty());
    }

    @Test
    @Order(3)
    void get_expense_by_expense_id(){

        Expense retrievedExpense = expenseDao.getExpenseById(testExpense.getExpenseId());
        System.out.println(retrievedExpense);
        Assertions.assertEquals(testExpense.getExpenseId(), retrievedExpense.getExpenseId());
    }

    @Test
    @Order(4)
    void get_expense_by_employee_id(){

        List<Expense> retrievedExpense = expenseDao.getExpensesByEmployeeId(testExpense.getEmployeeId());
        System.out.println(retrievedExpense.size() > 0);
        Assertions.assertTrue(retrievedExpense.size() > 0);
    }

    @Test
    @Order(5)
    void update_expense() {
        Expense retrievedExpense = expenseDao.getExpenseById(testExpense.getExpenseId());
        retrievedExpense.setAmount(10000.00);
        boolean result = expenseDao.updateExpense(retrievedExpense);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(6)
    void delete_employee() {

        boolean result = expenseDao.deleteExpense(testExpense.getExpenseId());
        Assertions.assertTrue(result);
    }



}
