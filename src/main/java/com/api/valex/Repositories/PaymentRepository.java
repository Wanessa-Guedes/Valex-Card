package com.api.valex.Repositories;

import com.api.valex.Models.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

    List<Payments> findAllByCardId(long id);
}
