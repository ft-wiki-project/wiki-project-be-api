package com.cooksys.wikiProjectAPI.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.wikiProjectAPI.entities.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}
