package ru.arshuranov.homeworkspring_mockito.controller;

import org.springframework.web.bind.annotation.*;
import ru.arshuranov.homeworkspring_mockito.model.Employee;
import ru.arshuranov.homeworkspring_mockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RequestMapping("/department")
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public String test() {
        return "It's working!";
    }

    @GetMapping("/{id}/salary/max")
    public Employee getEmployeeWithMaxSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeWithMaxSalary(id);
    }

    @GetMapping("/{id}/salary/min")
    public Employee getEmployeeWithMinSalary(@PathVariable Integer id) {
        return departmentService.getEmployeeWithMinSalary(id);
    }

    @GetMapping("/{id}/salary/sum")
    public int getSumSalaryByDepartment(@PathVariable Integer id) {
        return departmentService.getSumSalaryByDepartment(id);
    }

    @GetMapping("/{id}/employees")
    public Map<Integer, List<Employee>> getGroupedByDepartmentEmployees(@PathVariable Integer id) {
        return departmentService.getGroupedByDepartmentEmployees(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getGroupedAllEmployees() {
        return departmentService.getGroupedAllEmployees();
    }


}
