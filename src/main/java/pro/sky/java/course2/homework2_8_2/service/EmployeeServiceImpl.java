package pro.sky.java.course2.homework2_8_2.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.homework2_8_2.data.Employee;
import pro.sky.java.course2.homework2_8_2.exceptions.BadRequest;
import pro.sky.java.course2.homework2_8_2.exceptions.NotFound;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private Map<String, Employee> staffOfEmployee = new HashMap<>(Map.of(
            "Петров Юрий", new Employee("Петров", "Юрий", 200_000f, 0),
            "Якобсон Иосиф", new Employee("Якобсон", "Иосиф", 180_000f, 0),
            "Стивен Джексон", new Employee("Стивен", "Джексон", 180_000f, 1),
            "КимМария", new Employee("Ким", "Мария", 150_000f, 1),
            "Мансурова Амира", new Employee("Мансурова", "Амира", 150_000f, 2),
            "Шевченко Ирина", new Employee("Шевченко", "Ирина", 120_000f, 2),
            "Яцехиро Анимото", new Employee("Яцехиро", "Анимото", 180_000f, 3),
            "Дорошенко Матвей", new Employee("Дорошенко", "Матвей", 150_000f, 3),
            "Абдуллаев Бахром", new Employee("Абдуллаев", "Бахром", 100_000f, 3)
    ));

    private List<String> departments = new ArrayList<>(List.of("Руководство", "Бухгалтерия",
            "Отдел кадров", "Технический отдел"));

    @Override
    public Employee findEmloyee(String lastName, String firstName) {
        Employee employee = staffOfEmployee.get(lastName + firstName);
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            return employee;
        } else {
            throw new BadRequest();
        }
    }

    @Override
    public Employee addEmployee(String lastName, String firstName, Float salary, Integer departmentId) {
        Employee newEmployee = new Employee(lastName, firstName, salary, departmentId);
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            throw new BadRequest();
        } else {
            staffOfEmployee.put(lastName + firstName, newEmployee);
            return newEmployee;
        }
    }

    @Override
    public Employee dismissEmployee(String lastName, String firstName) {
        Employee dismissedEmployee = staffOfEmployee.get(lastName + firstName);
        if (staffOfEmployee.containsKey(lastName + firstName)) {
            staffOfEmployee.remove(lastName + firstName, dismissedEmployee);
            return dismissedEmployee;
        } else {
            throw new NotFound();
        }
    }

    @Override
    public String getNameOfDepartment(Integer departmentId) {
        return departments.get(departmentId);
    }

    @Override
    public String getAllStaff() {
        if (staffOfEmployee.size() == 0) {
            throw new NotFound();
        }
        Set<String> allStaff = staffOfEmployee.keySet();
        return "Список сотрудников:" + allStaff;
    }

    @Override
    public String calculateAllSalaries() {
        List<Float> listOfSalaries = staffOfEmployee.values().stream()
                .map(e -> e.getSalary())
                .collect(Collectors.toList());
        double wageFund = listOfSalaries.stream().mapToDouble(e->e).sum();
        return "Фонд оплаты труда составляет " + wageFund + " руб.";
    }
}
