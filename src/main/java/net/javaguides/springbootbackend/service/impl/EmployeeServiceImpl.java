package net.javaguides.springbootbackend.service.impl;

import java.util.List;
//import java.util.Optional;

import org.springframework.stereotype.Service;

import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
//import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.repository.EmployeeRepository;
import net.javaguides.springbootbackend.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    
	private EmployeeRepository employeeRepository;
	
	
	
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}



	@Override
	public Employee saveEmployee(Employee employee) {
		 
		return employeeRepository.save(employee);
	}



	@Override
	public List<Employee> getAllEmployees() {
		 
		return employeeRepository.findAll();
	}



	@Override
	public Employee getEmployeeById(long id) {
	 /* Optional<Employee> employee=employeeRepository.findById(id);
	  if(employee.isPresent()) {
		  return employee.get();
	  }
	  else {
		  throw new ResourceNotFoundException("Employee","Id",id); 
	  }*/
		
		return employeeRepository.findById(id).orElseThrow(()->
		                      new ResourceNotFoundException("Employee","Id",id));
		
		
	}



	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// We need to check the employee with given id is existed in DB or not
		Employee existingEmployee= employeeRepository.findById(id).orElseThrow(
			()-> new ResourceNotFoundException("Employee","Id",id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		
		//Save exiting employee to DB
		employeeRepository.save(existingEmployee);
		
		
		
		
		return existingEmployee;
	}



	@Override
	public void deleteEmployee(long id) {
		//Check whether employee exists in the DB or not
		 employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee","Id",id));
		 employeeRepository.deleteById(id);
		
	}

}
