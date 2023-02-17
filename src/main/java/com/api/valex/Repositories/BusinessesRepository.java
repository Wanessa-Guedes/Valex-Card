package com.api.valex.Repositories;

import com.api.valex.Models.Businesses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessesRepository extends JpaRepository<Businesses, Long> {

    Businesses findById(long id);
}
