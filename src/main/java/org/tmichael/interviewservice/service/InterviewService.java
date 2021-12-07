package org.tmichael.interviewservice.service;

import org.springframework.stereotype.Component;

@Component
public class InterviewService {


    public int fibSequence(int fibIdx) {
        int fibNumber;
        if (fibIdx <= 1) {
            fibNumber = fibIdx;
        } else {
            fibNumber = fibSequence(fibIdx - 1) + fibSequence(fibIdx - 2);
        }

        return fibNumber;
    }
}
