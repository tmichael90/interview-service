package org.tmichael.interviewservice.dao;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class WordSearchRequest {

    private List<List<Character>> board = new ArrayList<>();
    private String word;
}
