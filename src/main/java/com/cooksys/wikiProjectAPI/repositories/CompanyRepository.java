package com.cooksys.wikiProjectAPI.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.wikiProjectAPI.entities.Company;
import com.cooksys.wikiProjectAPI.entities.User;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


}
