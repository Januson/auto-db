package org.example.employee;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    Employee getEmployee(@PathVariable Integer id) {
        return employeeService.findById(id).get();
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    String addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.save(employee);
        return "SUCCESS";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    Employee updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.save(employee);
        return updatedEmployee;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.DELETE)
    Map<String, String> deleteEmployee(@RequestParam Integer id) {
        Map<String, String> status = new HashMap<>();
        Optional<Employee> employee = employeeService.findById(id);
        if (employee.isPresent()) {
            employeeService.delete(employee.get());
            status.put("Status", "Employee deleted successfully");
        }
        else {
            status.put("Status", "Employee not exist");
        }
        return status;
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    List<Employee> getAllEmployee() {
        return employeeService.findAll();
    }

    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    String addAllEmployees(@RequestBody List<Employee> employeeList) {
        employeeService.saveAll(employeeList);
        return "SUCCESS";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.DELETE)
    String addAllEmployees() {
        employeeService.deleteAll();
        return "SUCCESS";
    }
}