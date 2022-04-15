package pro.sky.java.course2.homework2_8_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.homework2_8_2.data.Employee;
import pro.sky.java.course2.homework2_8_2.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String addNewEmployee(@RequestParam String lastName,
                                 @RequestParam String firstName,
                                 @RequestParam Float salary,
                                 @RequestParam Integer departmentId) {
        Employee newEmployee = employeeService.addEmployee(lastName, firstName, salary, departmentId);
        return newEmployee.getLastName() + " " + newEmployee.getFirstName() + " is hired.";
    }

    @GetMapping("/remove")
    public String removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        Employee disMissedEmployee = employeeService.dismissEmployee(firstName, lastName);
        return disMissedEmployee.getLastName() + " " + disMissedEmployee.getFirstName() + " is dismissed.";
    }

    @GetMapping("/find")
    public String findStaff(@RequestParam String lastName, @RequestParam String firstName) {
        Employee employee = employeeService.findEmloyee(lastName, firstName);
        return employee.getLastName() + " " + employee.getFirstName() + " is found.";
    }

    @GetMapping("/print")
    public String printAllStaff() {
        return employeeService.getAllStaff();
    }

    @GetMapping("/sumOfSalaries")
    public String sumOfSalaries() {
        return employeeService.calculateAllSalaries();
    }
}
