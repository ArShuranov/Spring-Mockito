package ru.arshuranov.homeworkspring_mockito.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.arshuranov.homeworkspring_mockito.model.Employee;
import ru.arshuranov.homeworkspring_mockito.service.EmployeeService;
import ru.arshuranov.homeworkspring_mockito.service.Impl.EmployeeServiceImpl;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmployeeServiceImplTest {

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    public void shouldCorrectlyAddEmployee() {
        Employee employee = new Employee("Ivan4", "Ivanov4", 100000, 1);
        employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        Map<String, Employee> actual = employeeService.getAllEmployees();
        Map<String, Employee> expected = new HashMap<>(Map.of("Ivan4Ivanov4", employee));
        assertEquals(expected, actual);

    }

    @Test
    public void shouldThrowExceptionWhenAddExistEmployee() {
        employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        });
    }

    @Test
    public void shouldCorrectlyRemoveEmployee() {
        employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        employeeService.removeEmployee("Ivan4", "Ivanov4");
        Map<String, Employee> actual = employeeService.getAllEmployees();
        Map<String, Employee> expected = new HashMap<>();
        assertEquals(expected, actual);

    }

    @Test
    public void shouldThrowExceptionWhenRemoveNonExistEmployee() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.removeEmployee("Ivan4", "Ivanov4");
        });
    }

    @Test
    public void shouldCorrectlyFindEmployee() {
        employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        Employee actual = employeeService.findEmployee("Ivan4", "Ivanov4");
        Employee expected = new Employee("Ivan4", "Ivanov4", 100000, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldThrowExceptionWhenFindNonExistEmployee() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.findEmployee("Ivan4", "Ivanov4");
        });
    }
}
