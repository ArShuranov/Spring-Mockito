package ru.arshuranov.homeworkspring_mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arshuranov.homeworkspring_mockito.model.Employee;
import ru.arshuranov.homeworkspring_mockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RequestMapping("/departments")
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

    @GetMapping("/max-salary")
    public Employee getEmployeeWithMaxSalary(@RequestParam("departmentId") Integer department) {
        return departmentService.getEmployeeWithMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeWithMinSalary(@RequestParam("departmentId") Integer department) {
        return departmentService.getEmployeeWithMinSalary(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getGroupedByDepartmentEmployees(
            @RequestParam(name = "departmentId", required = false) Integer department
    ) {
        return departmentService.getGroupedByDepartmentEmployees(department);
    }
}
