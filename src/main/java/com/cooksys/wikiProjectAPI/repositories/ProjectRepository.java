package com.cooksys.wikiProjectAPI.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.wikiProjectAPI.entities.Project;
import com.cooksys.wikiProjectAPI.entities.Team;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

  List<Project> findByTeam(Team team);

}
