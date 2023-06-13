package ru.arshuranov.homeworkspring_mockito.service;

import ru.arshuranov.homeworkspring_mockito.model.Employee;

import java.util.Map;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int salary, int department);

    String removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Map<String, Employee> getAllEmployees();
}
