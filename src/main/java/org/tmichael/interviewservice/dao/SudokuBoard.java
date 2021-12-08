package org.tmichael.interviewservice.dao;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SudokuBoard {

    private List<List<Character>> rows = new ArrayList<>(9);
}
