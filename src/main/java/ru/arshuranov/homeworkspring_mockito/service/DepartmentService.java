package ru.arshuranov.homeworkspring_mockito.service;

import ru.arshuranov.homeworkspring_mockito.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMaxSalary(Integer department);

    Employee getEmployeeWithMinSalary(Integer department);

    Map<Integer, List<Employee>> getGroupedByDepartmentEmployees(Integer department);
}
