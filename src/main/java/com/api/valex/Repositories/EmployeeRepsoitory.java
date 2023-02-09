package com.api.valex.Repositories;

import com.api.valex.Models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepsoitory extends JpaRepository<Employees, Long> {

    Employees findById(long id);
}
