package com.api.valex.Repositories;


import com.api.valex.Models.Companies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompaniesRepository extends JpaRepository<Companies, Long> {

    Companies findByApiKey(String apiKey);
}
