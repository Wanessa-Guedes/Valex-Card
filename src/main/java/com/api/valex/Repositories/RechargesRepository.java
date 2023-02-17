package com.api.valex.Repositories;

import com.api.valex.Models.Cards;
import com.api.valex.Models.Recharges;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RechargesRepository extends JpaRepository<Recharges, Long> {
    Recharges findByCardId(long id);
}
