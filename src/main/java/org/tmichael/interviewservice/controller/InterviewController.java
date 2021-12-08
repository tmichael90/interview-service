package org.tmichael.interviewservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tmichael.interviewservice.dao.ListNode;
import org.tmichael.interviewservice.dao.ListNodePair;
import org.tmichael.interviewservice.service.EasyInterviewService;
import org.tmichael.interviewservice.service.MediumInterviewService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("interview")
public class InterviewController {

    private final EasyInterviewService easyInterviewService;
    private final MediumInterviewService mediumInterviewService;


    @Autowired
    public InterviewController(EasyInterviewService easyInterviewService, MediumInterviewService mediumInterviewService) {
        this.easyInterviewService = easyInterviewService;
        this.mediumInterviewService = mediumInterviewService;
    }

    //
    // Medium questions
    //

    @GetMapping("medium/reverseNum/{num}")
    public Integer reverseNum(@PathVariable("num") Integer num) {
        return mediumInterviewService.reverseNum(num);
    }

    @GetMapping("medium/convertToZigZag")
    public String convertToZigZag(@RequestParam("str") String str, @RequestParam("numRows") Integer numRows) {
        return mediumInterviewService.convertToZigZag(str, numRows);
    }

    @GetMapping("medium/longestPalindrome/{str}")
    public String longestPalindrome(@PathVariable("str") String str) {
        return mediumInterviewService.longestPalindrome(str);
    }

    @GetMapping("medium/lengthOfLongestSubstring/{str}")
    public Integer lengthOfLongestSubstring(@PathVariable("str") String str) {
        return mediumInterviewService.lengthOfLongestSubstring(str);
    }

    @PostMapping("medium/addTwoNumbers")
    public ListNode addTwoNumbers(@RequestBody ListNodePair nodePair) {
        return mediumInterviewService.addTwoNumbers(nodePair.getNode1(), nodePair.getNode2());
    }

    //
    // Easy questions
    //

    @GetMapping("easy/isParenValid/{parenStr}")
    public Boolean isParenValid(@PathVariable("parenStr") String parenStr) {
        return easyInterviewService.isParenValid(parenStr);
    }

    @GetMapping("easy/longestCommonPrefix")
    public String longestCommonPrefix(@RequestParam("strs") String[] strs) {
        return easyInterviewService.longestCommonPrefix(strs);
    }

    @GetMapping("easy/twoSum")
    public List<Integer> twoSum(@RequestParam("target") Integer target, @RequestParam("nums") Integer[] nums) {
        int[] primNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            primNums[i] = nums[i];
        }

        return Arrays.stream(easyInterviewService.twoSum(primNums, target)).boxed().collect(Collectors.toList());
    }

    @GetMapping("easy/fizzBuzz")
    public String fizzBuzz() {
        return easyInterviewService.fizzBuzz();
    }

    @GetMapping("easy/isPalindrome/{num}")
    public Boolean isPalindrome(@PathVariable("num") int num) {
        return easyInterviewService.isPalindrome(num);
    }

    @GetMapping("easy/romanToInt/{romanNumeral}")
    public Integer romanToInt(@PathVariable("romanNumeral") String romanNumeral) {
        return easyInterviewService.romanToInt(romanNumeral);
    }

    @GetMapping("easy/fibSequence/{fibIdx}")
    public Integer fibSequence(@PathVariable("fibIdx") Integer fibIdx) {
        return easyInterviewService.fibSequence(fibIdx);
    }
}
