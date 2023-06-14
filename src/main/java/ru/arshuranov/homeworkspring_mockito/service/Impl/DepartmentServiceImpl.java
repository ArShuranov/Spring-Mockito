package ru.arshuranov.homeworkspring_mockito.service.Impl;

import org.springframework.stereotype.Service;
import ru.arshuranov.homeworkspring_mockito.model.Employee;
import ru.arshuranov.homeworkspring_mockito.service.DepartmentService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeWithMaxSalary(Integer department) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Employee getEmployeeWithMinSalary(Integer department) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElse(null);
    }

    @Override
    public Map<Integer, List<Employee>> getGroupedByDepartmentEmployees(Integer department) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> department == null || employee.getDepartment().equals(department))
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    public Map<Integer, List<Employee>> getGroupedAllEmployees() {
        return employeeService.getAllEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public int getSumSalaryByDepartment(Integer department) {
        return employeeService.getAllEmployees().values().stream()
                .filter(employee -> department == null || employee.getDepartment().equals(department)).mapToInt(Employee::getSalary).sum();
    }

}
