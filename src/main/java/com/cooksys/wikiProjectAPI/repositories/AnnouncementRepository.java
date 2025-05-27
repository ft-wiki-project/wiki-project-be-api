package com.cooksys.wikiProjectAPI.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cooksys.wikiProjectAPI.entities.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

  List<Announcement> findByCompanyId(Long companyId);

}
