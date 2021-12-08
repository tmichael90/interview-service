package org.tmichael.interviewservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tmichael.interviewservice.service.EasyInterviewService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("interview")
public class InterviewController {

    private final EasyInterviewService easyInterviewService;


    @Autowired
    public InterviewController(EasyInterviewService easyInterviewService) {
        this.easyInterviewService = easyInterviewService;
    }

    @GetMapping("isParenValid/{parenStr}")
    public Boolean isParenValid(@PathVariable("parenStr") String parenStr) {
        return easyInterviewService.isParenValid(parenStr);
    }

    @GetMapping("longestCommonPrefix")
    public String longestCommonPrefix(@RequestParam("strs") String[] strs) {
        return easyInterviewService.longestCommonPrefix(strs);
    }

    @GetMapping("twoSum")
    public List<Integer> twoSum(@RequestParam("target") Integer target, @RequestParam("nums") Integer[] nums) {
        int[] primNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            primNums[i] = nums[i];
        }

        return Arrays.stream(easyInterviewService.twoSum(primNums, target)).boxed().collect(Collectors.toList());
    }

    @GetMapping("fizzBuzz")
    public String fizzBuzz() {
        return easyInterviewService.fizzBuzz();
    }

    @GetMapping("isPalindrome/{num}")
    public Boolean isPalindrome(@PathVariable("num") int num) {
        return easyInterviewService.isPalindrome(num);
    }

    @GetMapping("romanToInt/{romanNumeral}")
    public Integer romanToInt(@PathVariable("romanNumeral") String romanNumeral) {
        return easyInterviewService.romanToInt(romanNumeral);
    }

    @GetMapping("fibSequence/{fibIdx}")
    public Integer fibSequence(@PathVariable("fibIdx") Integer fibIdx) {
        return easyInterviewService.fibSequence(fibIdx);
    }
}
