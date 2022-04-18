package dev.mokua.dao;

import dev.mokua.entities.Employee;

import java.util.List;

public interface EmployeeDao {

    //POSY /Employees
    void addEmployee(Employee employee);

    //GET /Employees
    List<Employee> getEmployees();

    //GET Employees/120
    Employee getEmployee();

    //PUT Employees/150
    void updateEmployee(Employee employee);

}