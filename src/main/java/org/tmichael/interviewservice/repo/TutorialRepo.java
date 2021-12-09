package org.tmichael.interviewservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tmichael.interviewservice.model.Tutorial;

import java.util.List;

public interface TutorialRepo extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
}
