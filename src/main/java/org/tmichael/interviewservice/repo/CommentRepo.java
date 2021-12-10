package org.tmichael.interviewservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tmichael.interviewservice.model.Comment;

public interface CommentRepo extends JpaRepository<Comment, Long> {


}
