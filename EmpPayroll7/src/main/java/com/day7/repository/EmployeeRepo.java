package com.day7.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.day7.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    
}

