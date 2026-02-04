package com.day7.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.day7.dto.EmployeeDTO;
import com.day7.entity.Employee;
import com.day7.repository.EmployeeRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    public EmployeeDTO addEmployee(EmployeeDTO dto) {
        log.info("Request received to add employee : {}", dto);

        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setSalary(dto.getSalary());

        Employee saved = employeeRepo.save(emp);

        log.info("Employee saved successfully with id {}", saved.getId());

        return new EmployeeDTO(saved.getName(), saved.getSalary());
    }

    public EmployeeDTO findById(int id) {
        log.info("Fetching employee with id {}", id);

        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Employee not found with id {}", id);
                    return new RuntimeException("Employee not found with id: " + id);
                });

        log.info("Employee found : {}", emp.getName());

        return new EmployeeDTO(emp.getName(), emp.getSalary());
    }

    public List<EmployeeDTO> viewAllEmp() {
        log.info("Fetching all employees");

        List<EmployeeDTO> list = employeeRepo.findAll()
                .stream()
                .map(emp -> new EmployeeDTO(emp.getName(), emp.getSalary()))
                .collect(Collectors.toList());

        log.info("Total employees fetched : {}", list.size());

        return list;
    }

    public EmployeeDTO updateEmp(int id, EmployeeDTO dto) {
        log.info("Updating employee with id {}", id);

        Employee emp = employeeRepo.findById(id)
                .orElseThrow(() -> {
                    log.error("Cannot update. Employee not found {}", id);
                    return new RuntimeException("Employee not found with id: " + id);
                });

        emp.setName(dto.getName());
        emp.setSalary(dto.getSalary());

        Employee updated = employeeRepo.save(emp);

        log.info("Employee updated successfully {}", id);

        return new EmployeeDTO(updated.getName(), updated.getSalary());
    }

    public String deleteEmp(int id) {
        log.warn("Deleting employee with id {}", id);

        employeeRepo.deleteById(id);

        log.info("Employee deleted {}", id);

        return "Employee details with id : " + id + " is deleted";
    }
}