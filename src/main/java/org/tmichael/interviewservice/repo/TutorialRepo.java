package org.tmichael.interviewservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.tmichael.interviewservice.model.Tutorial;

import java.util.List;

public interface TutorialRepo extends JpaRepository<Tutorial, Long> {

    List<Tutorial> findByPublished(boolean published);
    List<Tutorial> findByTitleContaining(String title);
    @Query("SELECT t FROM Tutorial t JOIN FETCH t.comments WHERE t.title like concat('%', ?1, '%')")
    List<Tutorial> findByTitleContainingAndFetchComments(String title);
    @Query("SELECT t FROM Tutorial t JOIN FETCH t.comments")
    List<Tutorial> findAllAndFetchComments();
}
