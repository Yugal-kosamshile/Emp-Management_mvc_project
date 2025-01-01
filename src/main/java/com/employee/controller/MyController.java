package com.employee.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.employee.employeemodel.EmployeeModel;
import com.employee.entity.EmployeeEntity;
import com.employee.service.EmployeeService;

import jakarta.validation.Valid;

@Controller
public class MyController { 

    @Autowired
    EmployeeService employeeService;

    // Get the edit form for an employee by ID
    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable("id") Long id, Model model) {
        EmployeeEntity employee = employeeService.editEmployeeById(id);
        model.addAttribute("employee", employee);
        model.addAttribute("id", id);  // Fix here: Add employee ID to model
        return "edit-employee";
    }

    // Update the employee after editing
    @PostMapping("/updateemployee")
    public String updateEmployee(@Valid EmployeeEntity employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-employee";  // Return to the edit form if validation fails
        }
        employeeService.updateEmployeeDetails(employee);
        return "redirect:/getallemployees"; 
    }

    // Delete the employee by ID
    @GetMapping("/delete")
    public String deleteProductById(@RequestParam Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/getallemployees";
    }

    // Show the employee by ID inserted in the search form
    @PostMapping("/searchbyid")
    public String searchById(@RequestParam Long id, Model model) {
        EmployeeEntity employee = employeeService.searchById(id);
        model.addAttribute("employee", employee);
        return "search-employee";
    }

    // Save the employee after validation
    @PostMapping("/savedEmployee")
    public String savedEmployee(@Valid EmployeeModel employeeModel, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            HashMap<String, String> validationError = new HashMap<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                validationError.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("validationErrors", validationError);
            return "addEmployee";
        }
        employeeService.saveEmployeeDetails(employeeModel);
        return "redirect:/getallemployees";
    }

    // Get all employees
    @GetMapping("/getallemployees")
    public String getAllEmployees(Model model) {
        List<EmployeeEntity> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "emplist";
    }

    // Get search form
    @GetMapping("/searchform")
    public String getSearchForm() {
        return "search-employee";
    }

    // Show the add employee form
    @GetMapping("/employeeform")
    public String addEmployee(Model model) {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setSalary(32000.50);
        model.addAttribute("employeeModel", employeeModel);
        return "addEmployee"; 
    }

    // Home page
    @GetMapping("/homepage")
    public String home() {
        return "home"; 
    }

    // Contact us page
    @GetMapping("/contactus")
    public String contact() {
        return "contact"; 
    }

    // About us page
    @GetMapping("/aboutus")
    public String about() {
        return "about"; 
    }
}
