package com.techmind.project_enterprise.service;

import com.techmind.project_enterprise.model.Employee;
import com.techmind.project_enterprise.model.Enterprise;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    Employee saveEmployee (Employee employee);
    Employee getEmployeeById(Integer id);
    Employee updateEmployee(Employee employee);
    void deleteEmployeeById(Integer id);
    boolean existSById(Integer id);
    Optional<Employee> getOne(Integer id);

}
