package dev.mokua.services;

import dev.mokua.entities.Employee;

import java.util.List;

public interface EmployeeService {

    //POST /Employees
    Employee addEmployee(Employee employee);

    //GET /Employees
    List<Employee> getEmployees();

    //GET /Employees/120
    Employee getEmployeeById(int employeeId);

    //PUT /Employees/150
    boolean updateEmployee(Employee employee);

    //DELETE /Employees/190
    boolean deleteEmployeeById(int employeeId);
}
