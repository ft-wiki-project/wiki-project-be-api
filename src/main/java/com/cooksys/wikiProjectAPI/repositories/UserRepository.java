package com.cooksys.wikiProjectAPI.repositories;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.wikiProjectAPI.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByCredentialsUsernameAndCredentialsPassword(String username, String password);
  
  Optional<User> findByCredentialsUsername(String username);
 
  Optional<User> findByProfileEmail(String email);
 
}
