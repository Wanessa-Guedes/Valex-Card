package com.api.valex.Repositories;

import com.api.valex.Models.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {

    List<Cards> findAllByEmployeeId(Long employeeId);

    Cards findById(long id);

    Cards findBySecurityCode(String securityCode);
}
