package dev.mokua.daotests;
import dev.mokua.dao.EmployeeDao;
import dev.mokua.dao.EmployeeDaoImpl;
import dev.mokua.entities.Employee;
import org.junit.jupiter.api.*;


import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDaoTests {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static Employee testEmployee = null;

    @Test
    @Order(1)
    void add_employee_test() {
        Employee brian = new Employee(0,"Brian", "Mokua", "bmokua@gmail.com");
        brian = employeeDao.addEmployee(brian);
        EmployeeDaoTests.testEmployee = brian;
        Assertions.assertTrue(brian.getEmployeeId()!=0);

    }

    @Test
    @Order(2)
    void get_all_employees(){
        Employee a = new Employee(0,"Brenda","Taylor", "brendataylor@gmail.com");
        Employee b = new Employee(0,"George","Handler", "goergehandler@gmail.com");
        Employee c = new Employee(0,"Adam","Gibson", "adamgibson@gmail.com");
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

        Employee retrievedEmployee = employeeDao.getEmployeeById(testEmployee.getEmployeeId());

        System.out.println(retrievedEmployee);
        Assertions.assertEquals(testEmployee.getEmployeeId(), retrievedEmployee.getEmployeeId());
    }

    @Test
    @Order(4)
    void update_employee() {
        Employee retrievedEmployee = employeeDao.getEmployeeById(testEmployee.getEmployeeId());
        retrievedEmployee.setFirstName("Brian II");
        boolean result = employeeDao.updateEmployee(retrievedEmployee);
        Assertions.assertTrue(result);
    }

    @Test
    @Order(5)
    void delete_employee() {

        boolean result = employeeDao.deleteEmployeeById(testEmployee.getEmployeeId());
        Assertions.assertTrue(result);
    }
}