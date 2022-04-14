package pro.sky.java.course2.homework2_8_2.service;

import pro.sky.java.course2.homework2_8_2.data.Employee;

public interface EmployeeService {
    public Employee addEmployee (String lastName, String firstName, Float salary, Integer departmentId);
    public Employee dismissEmployee (String firstName, String lastName);
    public Employee findEmloyee (String firstName, String lastName);
    String getNameOfDepartment(Integer departmentId);
    public String getAllStaff();

    String calculateAllSalaries();
}
