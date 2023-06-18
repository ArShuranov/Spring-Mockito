package ru.arshuranov.homeworkspring_mockito.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.arshuranov.homeworkspring_mockito.model.Employee;
import ru.arshuranov.homeworkspring_mockito.service.Impl.DepartmentServiceImpl;
import ru.arshuranov.homeworkspring_mockito.service.Impl.EmployeeServiceImpl;

import java.util.*;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private final Map<String, Employee> employees = new HashMap<>((Map.of
            ("IvanIvanov", new Employee("Ivan", "Ivanov", 100000, 4),
                    "Ivan1Ivanov1", new Employee("Ivan1", "Ivanov1", 200000, 2),
                    "Ivan2Ivanov2", new Employee("Ivan2", "Ivanov2", 300000, 3),
                    "Ivan3Ivanov3", new Employee("Ivan3", "Ivanov3", 400000, 3)

            )));

    private final List<Employee> groupedEmployeesByDepartment = new ArrayList<>((Arrays.asList
            (new Employee("Ivan3", "Ivanov3", 400000, 3),
            (new Employee("Ivan2", "Ivanov2", 300000, 3)
                    ))));




    @BeforeEach
    public void setUp() {
    }

    @Test
    public void shouldGetEmployeeWithMaxSalaryFromDepartment() {
        //given

        final int depatmentId = 3;
        given(employeeService.getAllEmployees()).willReturn(employees);

        //when
        Employee employeeWithMaxSalary = departmentService.getEmployeeWithMaxSalary(depatmentId);

        //then
        Assertions.assertEquals(employees.get("Ivan3Ivanov3"), employeeWithMaxSalary);
        verify(employeeService, times(1)).getAllEmployees();

    }

    @Test
    public void shouldReturnNullWhenNoEmployeesMaxSalary() {
        //given

        final int depatmentId = 1; //в мапе нет такого отдела
        given(employeeService.getAllEmployees()).willReturn(employees);

        //when
        Employee employeeWithMaxSalary = departmentService.getEmployeeWithMaxSalary(depatmentId);

        //then
        Assertions.assertNull(employeeWithMaxSalary);
        verify(employeeService, times(1)).getAllEmployees();

    }

    @Test
    public void shouldReturnEmployeeWithMinSalaryFromDepartment() {
        //given

        final int depatmentId = 3;
        given(employeeService.getAllEmployees()).willReturn(employees);

        //when
        Employee employeeWithMinSalary = departmentService.getEmployeeWithMinSalary(depatmentId);

        //then
        Assertions.assertEquals(employees.get("Ivan2Ivanov2"), employeeWithMinSalary);
        verify(employeeService, times(1)).getAllEmployees();

    }

    @Test
    public void shouldReturnNullWhenNoEmployeesMinSalary() {
        //given

        final int depatmentId = 1;
        given(employeeService.getAllEmployees()).willReturn(employees);

        //when
        Employee employeeWithMinSalary = departmentService.getEmployeeWithMinSalary(depatmentId);

        //then
        Assertions.assertNull(employeeWithMinSalary);
        verify(employeeService, times(1)).getAllEmployees();

    }

    @Test
    public void shouldReturnSumSalaryByDepartment() {
        //given

        final int depatmentId = 3;
        given(employeeService.getAllEmployees()).willReturn(employees);

        //when
        int sumSalary = departmentService.getSumSalaryByDepartment(depatmentId);

        //then
        Assertions.assertEquals(700_000, sumSalary);
        verify(employeeService, times(1)).getAllEmployees();

    }

    @Test
    public void shouldReturnGroupedEmployeesByDepartment() {
        //given

        final Integer depatmentId = 3;
        given(employeeService.getAllEmployees()).willReturn(employees);
        Map<Integer, List<Employee>> result = new HashMap<>(Map.of(3, groupedEmployeesByDepartment
        ));

        //when
        Map employeesGroupedByDepartment = departmentService.getGroupedByDepartmentEmployees(depatmentId);

        //then
        Assertions.assertEquals(result, employeesGroupedByDepartment);
        verify(employeeService, times(1)).getAllEmployees();

    }

    @Test
    public void shouldReturnGroupedEmployeesByNonExistDepartment() {
        //given

        final Integer depatmentId = 1;
        given(employeeService.getAllEmployees()).willReturn(employees);
        Map<Integer, List<Employee>> result = new HashMap<>();

        //when
        Map employeesGroupedByDepartment = departmentService.getGroupedByDepartmentEmployees(depatmentId);

        //then
        Assertions.assertEquals(result, employeesGroupedByDepartment);
        verify(employeeService, times(1)).getAllEmployees();

    }

    @Test
    public void shouldReturnGroupedAllEmployeesByDepartments() {
        //given
        // листы для тестов группировки по всем отедлам

        final List<Employee> departmentNumber_2 = new ArrayList<>((List.of
                (new Employee("Ivan1", "Ivanov1", 200000, 2)
                        )));

        final List<Employee> departmentNumber_3 = new ArrayList<>((Arrays.asList
                (new Employee("Ivan3", "Ivanov3", 400000, 3),
                        (new Employee("Ivan2", "Ivanov2", 300000, 3)
                        ))));

        final List<Employee> departmentNumber_4 = new ArrayList<>((Arrays.asList
                (new Employee("Ivan", "Ivanov", 100000, 4)
                )));

        //итоговая мапа для сравнения с результатом
        given(employeeService.getAllEmployees()).willReturn(employees);
        Map<Integer, List<Employee>> result = new HashMap<>(Map.of(2, departmentNumber_2, 3, departmentNumber_3,
                4, departmentNumber_4
        ));

        //when
        Map allEmployeesmployeesGroupedByDepartments = departmentService.getGroupedAllEmployees();

        //then
        Assertions.assertEquals(result, allEmployeesmployeesGroupedByDepartments);
        verify(employeeService, times(1)).getAllEmployees();

    }

}
