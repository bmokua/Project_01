package dev.mokua.services;

import dev.mokua.dao.EmployeeDao;
import dev.mokua.dao.EmployeeDaoImpl;
import dev.mokua.entities.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    public EmployeeDao employeeDao;

    //Constructor for the employeeDao
    public EmployeeServiceImpl(){
        employeeDao = new EmployeeDaoImpl();
    }


    @Override
    public Employee addEmployee(Employee employee) {
        return this.employeeDao.addEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeDao.getEmployees();
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return this.employeeDao.getEmployeeById(employeeId);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return this.employeeDao.updateEmployee(employee);
    }

    @Override
    public boolean deleteEmployeeById(int employeeId) {
        return this.employeeDao.deleteEmployeeById(employeeId);
    }
}
