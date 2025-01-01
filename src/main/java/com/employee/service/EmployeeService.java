package com.employee.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.employeemodel.EmployeeModel;
import com.employee.entity.EmployeeEntity;
import com.employee.repository.EmployeeRepository;



@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    public List<EmployeeEntity> getAllEmployees()
    {
    	List<EmployeeEntity> employees = employeeRepository.findAll();
    	return employees;
    }
    
	public EmployeeEntity searchById(Long id) {
		
		Optional<EmployeeEntity> optionalData = employeeRepository.findById(id);
		if(optionalData.isPresent())
		{
			EmployeeEntity employee = optionalData.get();
			return employee;
		}
		else
		{
			return null;
		}
	}

	public void deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
	}
    public void saveEmployeeDetails(EmployeeModel employeeModel) {

        double pf = employeeModel.getSalary() * 0.12;   
        double hra = employeeModel.getSalary() * 0.20;  
        double da = employeeModel.getSalary() * 0.10;   
        double sa = employeeModel.getSalary() * 0.08;   


        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setName(employeeModel.getName());
        employeeEntity.setEmail(employeeModel.getEmail());
        employeeEntity.setPhone(employeeModel.getPhone());
        employeeEntity.setAge(employeeModel.getAge());
        employeeEntity.setGender(employeeModel.getGender());
        employeeEntity.setDepartment(employeeModel.getDepartment());
        employeeEntity.setSalary(employeeModel.getSalary());
        employeeEntity.setAddress(employeeModel.getAddress());

        employeeEntity.setPf(pf);
        employeeEntity.setHra(hra);
        employeeEntity.setDa(da);
        employeeEntity.setSa(sa);

        employeeRepository.save(employeeEntity);
    }

    public EmployeeEntity editEmployeeById(Long id) {
		Optional<EmployeeEntity> employee =employeeRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		else {
			
			return null;
		}
		
		
	}

    
	
    public void updateEmployeeDetails(EmployeeEntity updatedEmployee) {
	    // Fetch the employee entity from the database using the employee's ID
	    Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(updatedEmployee.getId());
	    
	    // Check if the employee exists in the database
	    if (optionalEmployee.isPresent()) {
	        // Retrieve the existing employee entity
	        EmployeeEntity existingEmployee = optionalEmployee.get();

	        // Update the fields with the values from the EmployeeModel
	        existingEmployee.setName(updatedEmployee.getName());
	        existingEmployee.setEmail(updatedEmployee.getEmail());
	        existingEmployee.setPhone(updatedEmployee.getPhone());
	        existingEmployee.setAge(updatedEmployee.getAge());
	        existingEmployee.setGender(updatedEmployee.getGender());
	        existingEmployee.setDepartment(updatedEmployee.getDepartment());
	        existingEmployee.setSalary(updatedEmployee.getSalary());
	        existingEmployee.setAddress(updatedEmployee.getAddress());

	        // Calculate the derived fields
	        double pf = updatedEmployee.getSalary() * 0.12;
	        double hra = updatedEmployee.getSalary() * 0.20;
	        double da = updatedEmployee.getSalary() * 0.10;
	        double sa = updatedEmployee.getSalary() * 0.08;

	        // Set the derived values to the employee entity
	        existingEmployee.setPf(pf);
	        existingEmployee.setHra(hra);
	        existingEmployee.setDa(da);
	        existingEmployee.setSa(sa);

	        // Save the updated employee entity back to the database
	        employeeRepository.save(existingEmployee);
	    } 
	}


	
	
}
