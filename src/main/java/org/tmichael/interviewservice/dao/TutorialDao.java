package org.tmichael.interviewservice.dao;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TutorialDao {

    private String title;
    private String description;
    private boolean published;
    private List<CommentDao> comments = new ArrayList<>();
}
