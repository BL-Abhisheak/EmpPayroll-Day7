package com.day7.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.day7.dto.EmployeeDTO;
import com.day7.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public EmployeeDTO addEmployee(@Valid @RequestBody EmployeeDTO dto) {
        log.info("POST /emp/add called");
        return employeeService.addEmployee(dto);
    }

    @GetMapping("/{id}")
    public EmployeeDTO viewById(@PathVariable int id) {
        log.info("GET /emp/{} called", id);
        return employeeService.findById(id);
    }

    @GetMapping("/all")
    public List<EmployeeDTO> viewAll() {
        log.info("GET /emp/all called");
        return employeeService.viewAllEmp();
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmp(@PathVariable int id, @Valid @RequestBody EmployeeDTO dto) {
        log.info("PUT /emp/{} called", id);
        return employeeService.updateEmp(id, dto);
    }

    @DeleteMapping("/{id}")
    public String deleteEmp(@PathVariable int id) {
        log.warn("DELETE /emp/{} called", id);
        return employeeService.deleteEmp(id);
    }
}
