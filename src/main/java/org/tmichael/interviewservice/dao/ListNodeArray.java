package org.tmichael.interviewservice.dao;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListNodeArray {

    private List<ListNode> nodes = new ArrayList<>();
}
