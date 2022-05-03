package dev.mokua.daotests;
import dev.mokua.dao.EmployeeDao;
import dev.mokua.dao.EmployeeDaoImpl;
import dev.mokua.entities.Employee;
import org.junit.jupiter.api.*;
import org.testng.annotations.Test;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static Employee testEmployee = null;

    @Test
    @Order(1)
    void add_employee_test() {
        Employee brian = new Employee(0,"Brian", "Mokua", "bmokua@gmail.com", 0L, 0L);
        boolean result = employeeDao.addEmployee(brian);
        EmployeeDaoTests.testEmployee = brian;
        Assertions.assertTrue(result);

    }

    @Test
    @Order(2)
    void get_all_employees(){
        Employee a = new Employee(0,"A","B", "C", 0L, 0L);
        Employee b = new Employee(0,"A","B", "C",0L, 0L);
        Employee c = new Employee(0,"A","B", "C",0L, 0L);
        employeeDao.addEmployee(a);
        employeeDao.addEmployee(b);
        employeeDao.addEmployee(c);

        List<Employee> newEmployeeList = employeeDao.getEmployees();
      //  int totalEmployees = newEmployeeList.size(); // if list did not work and was not tested this might fail regardless
       Assertions.assertTrue(newEmployeeList != null  && !newEmployeeList.isEmpty());
    }

    @Test
    @Order(3)
    void get_employee_by_employee_id(){

        Employee retrievedEmployee = employeeDao.getEmployeeById(21);

        System.out.println(retrievedEmployee);
        Assertions.assertEquals(21, retrievedEmployee.getEmployeeId());
    }

    @Test
    @Order(4)
    void update_employee() {
        Employee retrievedEmployee = employeeDao.getEmployeeById(21);
        retrievedEmployee.setFirstName("Brian II");
        boolean result = employeeDao.updateEmployee(retrievedEmployee);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(5)
    void delete_employee() {

        boolean result = employeeDao.deleteEmployeeById(21);
        Assertions.assertTrue(result);
    }
}