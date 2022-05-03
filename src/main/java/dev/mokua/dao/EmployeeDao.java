package dev.mokua.dao;

import dev.mokua.entities.Employee;

import java.util.List;

public interface EmployeeDao {

    //POST /Employees
    boolean addEmployee(Employee employee);

    //GET /Employees
    List<Employee> getEmployees();

    //GET Employees/120
    Employee getEmployeeById(int employeeId);

    //PUT Employees/150
    boolean updateEmployee(Employee employee);

    //DELETE /Employees/190
    boolean deleteEmployeeById(int id);

}