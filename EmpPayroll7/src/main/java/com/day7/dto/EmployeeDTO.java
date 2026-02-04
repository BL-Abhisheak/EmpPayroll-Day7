package com.day7.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    
    @Positive(message = "Salary must be positive")
    @DecimalMin(value = "0.0", inclusive = false, message = "Salary must be greater than zero")
    @NotNull(message = "Salary cannot be null")
    private double salary;

    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "Male|Female", message = "Gender must be Male or Female")
    private String gender;

    @NotBlank(message = "Note cannot be blank")
    private String note;

    @NotNull(message = "Start Date is required")
    @PastOrPresent(message = "Start Date cannot be in the future")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date startDate;

    @NotBlank(message = "Department cannot be blank")
    private String department;

}