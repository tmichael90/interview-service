package org.tmichael.interviewservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tmichael.interviewservice.service.InterviewService;

@RestController
@RequestMapping("interview")
public class InterviewController {

    private final InterviewService interviewService;


    @Autowired
    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @GetMapping("fibSequence/{fibIdx}")
    public Integer fibSequence(@PathVariable("fibIdx") Integer fibIdx) {
        return interviewService.fibSequence(fibIdx);
    }
}
