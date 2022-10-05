package com.techmind.project_enterprise.repository;

import com.techmind.project_enterprise.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeRepository extends JpaRepository<Employee, Integer> {
}
