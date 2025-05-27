package com.cooksys.wikiProjectAPI.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.wikiProjectAPI.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
