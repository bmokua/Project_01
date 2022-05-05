package dev.mokua.dao;

import dev.mokua.entities.Employee;
import dev.mokua.entities.Expense;
import dev.mokua.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static dev.mokua.utilities.Statuses.*;

public class ExpenseDaoImpl implements ExpenseDao {

    public static final String insert_expense = "" +
            "insert into expense(employee_id , amount, status, request_date, \n" +
            "decision_date) values (?,?,?,current_timestamp, current_timestamp); \n";

    public static final String get_expenses = "" +
            "select * from expense; \n";

    public static final String get_expense_by_status = "" +
            "select expense_id, employee_id, amount, status, request_date, \n" +
            "decision_date from expense where status = ?; \n";

    public static final String get_expense_by_id = "" +
            "select expense_id, employee_id, amount, status, request_date, \n" +
            "decision_date from expense where expense_id = ?; \n";

    public static final String update_expense = "" +
            "update expense \n" +
            "set amount = ?, \n" +
            "status = ? \n" +
            "where expense_id = ?; \n";

    public static final String approve_expense = "" +
            "update expense set \n" +
            "status = ? \n" +
            "where expense_id = ?; \n";

    public static final String deny_expense = "" +
            "update expense set \n" +
            "status = ?  \n" +
            "where expense_id = ?; \n";


    public static final String delete_expense = "" +
            "delete from expense where expense_id = ?" +
            " and status = 'PENDING';\n";

    public static final String get_expense_by_employee_id = "" +
            "select expense_id, employee_id, amount, status, request_date, " +
            "decision_date from expense where employee_id = ?; \n";


    //Add expense by expense
    @Override
    public Expense addExpense(Expense expense) {
        Expense returnValue = new Expense();

        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(insert_expense, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, expense.getEmployeeId());
            ps.setDouble(2, expense.getAmount());
            ps.setString(3, pendingStatus);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet a virtual table of results

            if (rs.next()) {
                returnValue = buildExpenseFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    @Override
    public List<Expense> getExpenses() {
        final List<Expense> newExpenseList = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(get_expenses);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Expense expense = buildExpenseFromResultSet(rs);
                newExpenseList.add(expense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newExpenseList;
    }

    //Get expense by status
    @Override
    public List<Expense> getExpenseByStatus(String status) {
        final List<Expense> statusExpenseList = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(get_expense_by_status);

            ps.setString(1, status);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Expense expense = buildExpenseFromResultSet(rs);
                statusExpenseList.add(expense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return statusExpenseList;
    }

    //Get expense by ID
    @Override
    public Expense getExpenseById(int expenseId) {


        Expense expense = new Expense();
        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(get_expense_by_id);

            ps.setInt(1, expenseId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                expense = buildExpenseFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expense;
    }

    //Update an expense
    @Override
    public boolean updateExpense(Expense expense) {

        boolean successUpdate = false;
        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(update_expense, Statement.RETURN_GENERATED_KEYS);

            ps.setDouble(1, expense.getAmount());
            ps.setString(2, expense.getStatus());
            ps.setInt(3, expense.getExpenseId());


//            ps.execute();
//            ResultSet rs = ps.getGeneratedKeys();

            if (ps.executeUpdate() > 0) {
                successUpdate = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return successUpdate;
    }

    @Override
    public boolean approveExpense(int expenseId) {
        boolean returnValue = false;

        //Timestamp timestamp = new Timestamp(System.currentTimeMillis());


        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(approve_expense, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, approvedStatus);
            ps.setInt(2, expenseId);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet a virtual table of results


            if (rs.next()) {

                returnValue = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    @Override
    public boolean denyExpense(int expenseId) {
        boolean returnValue = false;

        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(deny_expense, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, deniedStatus);
            ps.setInt(2, expenseId);

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet a virtual table of results

            if (rs.next()) {
                returnValue = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    //Delete an expense
    @Override
    public boolean deleteExpense(int expenseId) {

        boolean successfulDelete = false;
        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(delete_expense, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, expenseId);

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                successfulDelete = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return successfulDelete;
    }

    //Get expense by employeeID
    @Override
    public List<Expense> getExpensesByEmployeeId(int employeeId) {

        final List<Expense> employeeIdExpenseList = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(get_expense_by_employee_id);

            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Expense expense = buildExpenseFromResultSet(rs);
                employeeIdExpenseList.add(expense);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeIdExpenseList;
    }



    public static Expense buildExpenseFromResultSet(ResultSet rs) throws SQLException {
        Expense expense = new Expense();

        if(rs != null){
            int expenseId = rs.getInt("expense_id");
            int employeeId = rs.getInt("employee_id");
            Double amount = rs.getDouble("amount");
            String status = rs.getString("status");
            long requestDate = rs.getTimestamp("request_date").getTime();
            long decisionDate = rs.getTimestamp("decision_date").getTime();

            expense.setExpenseId(expenseId);
            expense.setEmployeeId(employeeId);
            expense.setAmount(amount);
            expense.setStatus(status);
            expense.setRequestDate(requestDate);
            expense.setDecisionDate(decisionDate);
        }
        return expense;
    }
}
