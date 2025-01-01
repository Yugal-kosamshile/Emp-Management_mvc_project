package com.employee.employeemodel;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeModel {
    
	@NotBlank(message = "Employee name cannot be blank.")
    private String name;
	
	@NotBlank(message = "E-mail cannot be blank.")
    private String email;
	
	@Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits.")
	private String phone;

	@Min(value = 18, message = "Age must be at least 18.")
    @Max(value = 65, message = "Age must be no more than 65.")
    private int age;
	
	@NotBlank(message = "Gender cannot be blank.")
    private String gender;
	
	 @Size(max = 50, message = "Department name cannot exceed 50 characters.")
	 @NotBlank(message = "Department cannot be blank.")
    private String department;
	 
	 @Positive(message = "Salary must be a positive value.")
    private double salary;
	 
	 @Size(max = 255, message = "Address cannot exceed 255 characters.")
	 @NotBlank(message = "Address cannot be blank.")
    private String address;
}
