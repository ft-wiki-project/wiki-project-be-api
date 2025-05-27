package com.cooksys.wikiProjectAPI.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.wikiProjectAPI.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
