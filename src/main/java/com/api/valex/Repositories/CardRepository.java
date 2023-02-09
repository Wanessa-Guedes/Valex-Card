package com.api.valex.Repositories;

import com.api.valex.Models.Cards;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {

    List<Cards> findAllByEmployeeId(Long employeeId);
}
