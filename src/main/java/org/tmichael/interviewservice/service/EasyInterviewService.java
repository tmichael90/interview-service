package org.tmichael.interviewservice.service;

import org.springframework.stereotype.Component;

import java.util.Stack;

@Component
public class EasyInterviewService {

    public boolean isParenValid(String s) {
        Stack<Character> stack = new Stack<>();
        boolean isValid = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char top = stack.isEmpty() ? ' ' : stack.peek();
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if ((c == ')' && top == '(') || (c == ']' && top == '[') || (c == '}' && top == '{')) {
                stack.pop();
            } else if (c== ')' || c == ']' || c == '}') {
                isValid = false;
                break;
            }
        }

        return isValid && stack.isEmpty();
    }

    public String longestCommonPrefix(String[] strs) {
        StringBuilder commonPrefix = new StringBuilder();
        boolean exit = false;
        int i = 0;
        while (!exit) {
            Character c = null;
            for (String str : strs) {
                if (i >= str.length()) {
                    exit = true;
                    break;
                }
                if (c == null) {
                    c = str.charAt(i);
                } else if (c != str.charAt(i)) {
                    exit = true;
                    break;
                }
            }
            if (!exit) {
                commonPrefix.append(c);
            }
            i++;
        }

        return commonPrefix.toString();
    }

    public int[] twoSum(int[] nums, int target) {
        int[] output = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] + nums[j] == target) {
                    output[0] = i;
                    output[1] = j;
                }
            }
        }

        return output;
    }

    public String fizzBuzz() {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= 100; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                builder.append("FizzBuzz,");
            } else if (i % 3 == 0) {
                builder.append("Fizz,");
            } else if (i % 5 == 0) {
                builder.append("Buzz,");
            } else {
                builder.append(i).append(",");
            }
        }

        return builder.toString();
    }

    public boolean isPalindrome(int x) {
        String xStr = Integer.toString(x);
        StringBuilder yStr = new StringBuilder();
        for (int i = xStr.length() - 1; i >= 0; i--) {
            yStr.append(xStr.charAt(i));
        }

        return xStr.equals(yStr.toString());
    }

    public int romanToInt(String s) {
        int total = 0;
        boolean skip = false;
        for (int i = 0; i < s.length(); i++) {
            if (skip) {
                skip = false;
                continue;
            }
            char c = s.charAt(i);
            switch (c) {
                case 'I':
                    if (i+1 < s.length() && 'V' == s.charAt(i+1)) {
                        total += 4;
                        skip = true;
                    } else if (i+1 < s.length() && 'X' == s.charAt(i+1)) {
                        total += 9;
                        skip = true;
                    } else {
                        total += 1;
                    }
                    break;
                case 'V':
                    total += 5;
                    break;
                case 'X':
                    if (i+1 < s.length() && 'L' == s.charAt(i+1)) {
                        total += 40;
                        skip = true;
                    } else if (i+1 < s.length() && 'C' == s.charAt(i+1)) {
                        total += 90;
                        skip = true;
                    } else {
                        total += 10;
                    }
                    break;
                case 'L':
                    total += 50;
                    break;
                case 'C':
                    if (i+1 < s.length() && 'D' == s.charAt(i+1)) {
                        total += 400;
                        skip = true;
                    } else if (i+1 < s.length() && 'M' == s.charAt(i+1)) {
                        total += 900;
                        skip = true;
                    } else {
                        total += 100;
                    }
                    break;
                case 'D':
                    total += 500;
                    break;
                case 'M':
                    total += 1000;
                    break;
            }
        }

        return total;
    }

    public int fibSequence(int fibIdx) {
        int fibNumber;
        if (fibIdx <= 1) {
            fibNumber = fibIdx;
        } else {
            fibNumber = fibSequence(fibIdx - 1) + fibSequence(fibIdx - 2);
        }

        return fibNumber;
    }

    public int searchInsert(int[] nums, int target) {
        int idx = nums.length;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                idx = i;
                break;
            }
        }

        return idx;
    }
}
