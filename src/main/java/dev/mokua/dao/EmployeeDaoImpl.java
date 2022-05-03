package dev.mokua.dao;

import dev.mokua.entities.Employee;
import dev.mokua.utilities.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao{
    public final String insert_employee = "" +
            "insert into employee(first_name, last_name, request_date, " +
            "decision_date, email) values (?,?,current_date,current_date,?) \n";

    public final String get_employees = "" +
            "select * from employee \n";

    public final String get_employee = "" +
            "select employee_id, first_name, last_name, request_date, " +
            "decision_date, email from employee\n" +
            "where employee_id = ? \n";

    public final String update_employee = "" +
            "update employee set first_name = ? , last_name = ? , decision_date = current_date\n " +
            "where employee_id = ? \n ";

    public final String delete_employee = "" +
            "delete from employee where employee_id = ?";



    @Override
    public boolean addEmployee(Employee employee) {
        boolean returnValue = false;
        try{
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(insert_employee, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getEmail());

            ps.execute();

            ResultSet rs = ps.getGeneratedKeys(); //ResultSet a virtual table of results

            if(rs.next()) {
                returnValue = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return returnValue;
    }

    @Override
    public List<Employee> getEmployees() {
        final List<Employee> newEmployeeList = new ArrayList<>();

        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(get_employees);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Employee employee = buildEmployeeFromResultSet(rs);
                newEmployeeList.add(employee);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newEmployeeList;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        Employee employee = new Employee();
        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(get_employee);

            ps.setInt(1,employeeId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                employee = buildEmployeeFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        boolean successUpdate = false;
        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(update_employee, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, employee.getFirstName());
            ps.setString(2, employee.getLastName());
            ps.setInt(3, employee.getEmployeeId());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                successUpdate=true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return successUpdate;

    }

    @Override
    public boolean deleteEmployeeById(int id) {
        boolean successfulDelete= false;
        try {
            Connection conn = ConnectionUtil.createConnection();
            PreparedStatement ps = conn.prepareStatement(delete_employee, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1,id);

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()){
                successfulDelete=true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return successfulDelete;
    }

    public static Employee buildEmployeeFromResultSet(ResultSet rs) throws SQLException {
        Employee employee = new Employee();

        if(rs != null){
            Integer employeeId = rs.getInt("employee_id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            long requestDate = rs.getTimestamp("request_date").getTime();
            long decisionDate = rs.getTimestamp("decision_date").getTime();

            employee.setEmployeeId(employeeId);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setRequestDate(requestDate);
            employee.setDecisionDate(decisionDate);
        }
        return employee;
    }
}
