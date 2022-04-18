package dev.mokua.services;

import dev.mokua.entities.Employee;

import java.util.List;

public interface EmployeeService {

    //POST /Employees
    void addEmployee(Employee employee);

    //GET /Employees
    List<Employee> getEmployees();

    //GET /Employees/120
    Employee getEmployee();

    //PUT /Employees/150
    void updateEmployee(Employee employee);

    //DELETE /Employees/190
    void deleteEmployee(Integer employeeId);
}
