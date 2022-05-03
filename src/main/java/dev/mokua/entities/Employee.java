package dev.mokua.entities;

public class Employee {

    private int employeeId; //Employee id
    private String firstName;
    private String lastName;
    private String email;
    private long decisionDate;
    private long requestDate;

    public Employee(){

    }

    public Employee(int employeeId, String firstName, String lastName, String email, long decisionDate, long requestDate) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.decisionDate = decisionDate;
        this.requestDate = requestDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getDecisionDate() {
        return decisionDate;
    }

    public void setDecisionDate(long decisionDate) {
        this.decisionDate = decisionDate;
    }

    public long getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(long requestDate) {
        this.requestDate = requestDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", decisionDate=" + decisionDate +
                ", requestDate=" + requestDate +
                '}';
    }
}
