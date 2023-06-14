package ru.arshuranov.homeworkspring_mockito.service.Impl;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.arshuranov.homeworkspring_mockito.exception.InvalidInputException;
import ru.arshuranov.homeworkspring_mockito.model.Employee;
import ru.arshuranov.homeworkspring_mockito.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    // хранилище для теста
    private final Map<String, Employee> employees = new HashMap<>(); /*(Map.of
            ("IvanIvanov", new Employee("Ivan", "Ivanov", 100000, 4),
                    "Ivan1Ivanov1", new Employee("Ivan1", "Ivanov1", 200000, 2),
                    "Ivan2Ivanov2", new Employee("Ivan2", "Ivanov2", 300000, 3),
                    "Ivan3Ivanov3", new Employee("Ivan3", "Ivanov3", 400000, 3)

            ));*/

    @Override
    public Map<String, Employee> getAllEmployees() {
        return employees;
    }

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
