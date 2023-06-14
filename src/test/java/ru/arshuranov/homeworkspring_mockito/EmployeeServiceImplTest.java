package ru.arshuranov.homeworkspring_mockito;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.arshuranov.homeworkspring_mockito.model.Employee;
import ru.arshuranov.homeworkspring_mockito.service.Impl.EmployeeServiceImpl;


import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmployeeServiceImplTest {

    @Test
    public void addEmployee() {

        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Employee employee = new Employee("Ivan4", "Ivanov4", 100000, 1);
        employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        Map<String, Employee> actual = employeeService.getAllEmployees();
        Map<String, Employee> expected = new HashMap<>(Map.of("Ivan4Ivanov4", employee));
        assertEquals(expected, actual);

    }

    @Test
    public void addExistEmployee() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        });
    }

    @Test
    public void removeEmployee() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        employeeService.removeEmployee("Ivan4", "Ivanov4");
        Map<String, Employee> actual = employeeService.getAllEmployees();
        Map<String, Employee> expected = new HashMap<>();
        assertEquals(expected, actual);

    }

    @Test
    public void removeNonExistEmployee() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.removeEmployee("Ivan4", "Ivanov4");
        });
    }

    @Test
    public void findEmployee() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        employeeService.addEmployee("Ivan4", "Ivanov4", 100000, 1);
        Employee actual = employeeService.findEmployee("Ivan4", "Ivanov4");
        Employee expected = new Employee("Ivan4", "Ivanov4", 100000, 1);
        assertEquals(expected, actual);
    }

    @Test
    public void findNonExistEmployee() {
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl();
        Assertions.assertThrows(RuntimeException.class, () -> {
            employeeService.findEmployee("Ivan4", "Ivanov4");
        });
    }
}
