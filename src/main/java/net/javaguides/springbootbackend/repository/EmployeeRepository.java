package net.javaguides.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springbootbackend.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
