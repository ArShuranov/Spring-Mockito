package ru.arshuranov.homeworkspring_mockito.service.Impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.arshuranov.homeworkspring_mockito.exception.InvalidInputException;
import ru.arshuranov.homeworkspring_mockito.model.Employee;
import ru.arshuranov.homeworkspring_mockito.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

//implements interface EmployeeService the following methods are implemented: read(getAll), add, remove, find
@Service
public class EmployeeServiceImpl implements EmployeeService {
    // хранилище для теста
    // storage employees
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Map<String, Employee> getAllEmployees() {
        return employees;
    }


    //util method for creating key for employees(HashMap key = firstName + lastName)
    private String getEmployeeKey(String firstName, String lastName) {
        return firstName + lastName;
    }

    //"[a-zA-Z]"
    private void StringUtilsCheck(String firstName, String lastName) {
        if ((StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName))
                || !StringUtils.isAlpha(firstName + lastName)) {
            throw new InvalidInputException("Некорректное имя или фамилия");
        }

    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int salary, int department) {
        String key = getEmployeeKey(firstName, lastName);
        //StringUtilsCheck(firstName, lastName);

        if (employees.containsKey(key)) {
            throw new RuntimeException("Такой сотрудник уже есть в базе");
        } else {
            employees.put(key, new Employee(StringUtils.capitalize(firstName), StringUtils.capitalize(lastName), salary, department));
        }
        return employees.get(key);
    }

    @Override
    public String removeEmployee(String firstName, String lastName) {
        String key = getEmployeeKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new RuntimeException("Такого сотрудника нет в базе");
        } else {
            employees.remove(key);
        }
        return "Сотрудник успешно удален";
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String key = getEmployeeKey(firstName, lastName);
        if (!employees.containsKey(key)) {
            throw new RuntimeException("Такого сотрудника нет в базе");
        } else {
            return employees.get(key);
        }
    }
}
