package dev.mokua.entities;

import java.util.Date;

public class Expense {

    private int employeeId;
    private int expenseId;
    private double amount;
    private long requestDate;
    private String status;
    private long decisionDate;

    public Expense(){

    }

    public Expense(int employeeId, int expenseId, double amount, long requestDate, String status, long decisionDate) {
        this.employeeId = employeeId;
        this.expenseId = expenseId;
        this.amount = amount;
        this.requestDate = requestDate;
        this.status = status;
        this.decisionDate = decisionDate;
    }

    public int getEmployeeId(){return employeeId;}

    public void setEmployeeId(int employeeId){this.employeeId = employeeId;}

    public int getExpenseId() {return expenseId;}

    public void setExpenseId(int expenseId) {this.expenseId = expenseId;}

    public double getAmount() {return amount;}

    public void setAmount(double amount) {this.amount = amount;}

    public long getRequestDate() {return requestDate;}

    public void setRequestDate(long requestDate) {this.requestDate = requestDate;}

    public String getStatus() {return status;}

    public void setStatus(String status) {this.status = status;}

    public long getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(long decisionDate) {
        this.decisionDate = decisionDate;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "employeeId=" + employeeId +
                ", expenseId=" + expenseId +
                ", amount=" + amount +
                ", requestDate=" + requestDate +
                ", status='" + status + '\'' +
                ", decisionDate=" + decisionDate +
                '}';
    }
}
