package org.tmichael.interviewservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.tmichael.interviewservice.dao.ListNode;
import org.tmichael.interviewservice.dao.ListNodeArray;
import org.tmichael.interviewservice.dao.ListNodePair;
import org.tmichael.interviewservice.dao.SudokuBoard;
import org.tmichael.interviewservice.service.EasyInterviewService;
import org.tmichael.interviewservice.service.HardInterviewService;
import org.tmichael.interviewservice.service.MediumInterviewService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("interview")
public class InterviewController {

    private final EasyInterviewService easyInterviewService;
    private final MediumInterviewService mediumInterviewService;
    private final HardInterviewService hardInterviewService;


    @Autowired
    public InterviewController(EasyInterviewService easyInterviewService, MediumInterviewService mediumInterviewService, HardInterviewService hardInterviewService) {
        this.easyInterviewService = easyInterviewService;
        this.mediumInterviewService = mediumInterviewService;
        this.hardInterviewService = hardInterviewService;
    }

    //
    // Hard questions
    //

    @GetMapping("hard/solveNQueens/{n}")
    public List<List<String>> solveNQueens(@PathVariable("n") int n) {
        return hardInterviewService.solveNQueens(n);
    }

    @PostMapping("hard/solveSudoku")
    public SudokuBoard solveSudoku(@RequestBody SudokuBoard board) {
        return convertToSudokuBoard(hardInterviewService.solveSudoku(convertFromSudokuBoard(board)));
    }

    @GetMapping("hard/firstMissingPositive")
    public Integer firstMissingPositive(@RequestParam("nums") Integer[] nums) {
        return hardInterviewService.firstMissingPositive(convertToPrimitive(nums));
    }

    @GetMapping("hard/trapWater")
    public Integer trapWater(@RequestParam("height") Integer[] height) {
        return hardInterviewService.trapWater(convertToPrimitive(height));
    }

    @GetMapping("hard/longestValidParen/{str}")
    public Integer longestValidParen(@PathVariable("str") String str) {
        return hardInterviewService.longestValidParentheses(str);
    }

    @PostMapping("hard/reverseKGroup")
    public ListNode reverseKGroup(@RequestBody ListNode head, @RequestParam("k") Integer k) {
        return hardInterviewService.reverseKGroup(head, k);
    }

    @PostMapping("hard/mergeKLists")
    public ListNode mergeKLists(@RequestBody ListNodeArray nodes) {
        return hardInterviewService.mergeKLists(nodes.getNodes().toArray(new ListNode[0]));
    }

    @GetMapping("hard/findMedianSortedArrays")
    public Double findMedianSortedArrays(@RequestParam("nums1") Integer[] nums1, @RequestParam("nums2") Integer[] nums2) {
        return hardInterviewService.findMedianSortedArrays(convertToPrimitive(nums1), convertToPrimitive(nums2));
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
        return Arrays.stream(easyInterviewService.twoSum(convertToPrimitive(nums), target)).boxed().collect(Collectors.toList());
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

    //
    // Util
    //

    private int[] convertToPrimitive(Integer[] nums) {
        int[] primNums = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            primNums[i] = nums[i];
        }

        return primNums;
    }

    private char[][] convertFromSudokuBoard(SudokuBoard board) {
        int boardSize = board.getRows().size();
        char[][] newBoard = new char[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            List<Character> row = board.getRows().get(i);
            for (int j = 0; j < boardSize; j++) {
                newBoard[i][j] = row.get(j);
            }
        }

        return newBoard;
    }

    private SudokuBoard convertToSudokuBoard(char[][] board) {
        SudokuBoard newBoard = new SudokuBoard();
        for (char[] row : board) {
            List<Character> newRow = new ArrayList<>();
            for (char cell : row) {
                newRow.add(cell);
            }
            newBoard.getRows().add(newRow);
        }

        return newBoard;
    }
}
