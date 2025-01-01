package com.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                  
    
    private String name;              
    private String email;             
    private String phone;               
    private int age;                  
    private String gender;              
    private String department;        
    private double salary;      
   private String address;     

   private double pf;                  
   private double hra;                 
   private double da;                  
   private double sa;   
}
