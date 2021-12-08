package org.tmichael.interviewservice.service;

import org.springframework.stereotype.Component;
import org.tmichael.interviewservice.dao.ListNode;

import java.util.ArrayList;
import java.util.List;

@Component
public class MediumInterviewService {

    public int reverseNum(int x) {
        StringBuilder builder = new StringBuilder(Integer.toString(x));
        String reverse = builder.reverse().toString();
        if (reverse.endsWith("-")) {
            reverse = "-" + reverse.substring(0, reverse.length() - 1);
        }

        return Integer.parseInt(reverse);
    }

    public String convertToZigZag(String s, int numRows) {
        List<List<Character>> zigZag = new ArrayList<>();
        List<Character> col = new ArrayList<>(numRows);
        boolean isZig = false;
        int iZig = numRows - 2;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (col.size() < numRows) {
                if (!isZig) {
                    col.add(c);
                } else {
                    for (int j = 0; j < numRows; j++) {
                        if (j == iZig) {
                            col.add(c);
                        } else {
                            col.add(' ');
                        }
                    }
                    zigZag.add(col);
                    col = new ArrayList<>(numRows);
                    if (iZig - 1 > 0) {
                        iZig--;
                    } else {
                        iZig = numRows - 2;
                        isZig = false;
                    }
                }
            } else {
                zigZag.add(col);
                col = new ArrayList<>(numRows);
                for (int j = 0; j < numRows; j++) {
                    if (j == iZig) {
                        col.add(c);
                    } else {
                        col.add(' ');
                    }
                }
                zigZag.add(col);
                col = new ArrayList<>(numRows);
                if (iZig - 1 > 0) {
                    iZig--;
                    isZig = true;
                } else {
                    iZig = numRows - 2;
                }
            }
        }
        zigZag.add(col);

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (List<Character> col2 : zigZag) {
                if (i < col2.size()) {
                    builder.append(col2.get(i));
                }
            }
        }

        return builder.toString().replace(" ", "");
    }

    public String longestPalindrome(String s) {
        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j+1);
                if (isPalindrome(sub) && sub.length() >= longest.length()) {
                    longest = sub;
                }
            }
        }

        return longest;
    }

    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (builder.toString().contains(Character.toString(c))) {
                if (builder.toString().length() > longest) {
                    longest = builder.toString().length();
                }
                builder = new StringBuilder();
            }
            builder.append(c);
        }

        return longest;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = convertNodeToNumber(l1) + convertNodeToNumber(l2);
        String sumStr = Integer.toString(sum);
        ListNode start = new ListNode();
        ListNode node = start;
        for (int i = sumStr.length() - 1; i >= 0; i--) {
            node.setVal(Integer.parseInt(Character.toString(sumStr.charAt(i))));
            if (i > 0) {
                node.setNext(new ListNode());
                node = node.getNext();
            }
        }

        return start;
    }

    private boolean isPalindrome(String s) {
        StringBuilder builder = new StringBuilder(s);
        String reverse = builder.reverse().toString();

        return s.equals(reverse);
    }

    private int convertNodeToNumber(ListNode n) {
        int num = 0;
        int i = 1;
        ListNode node = n;
        while (node != null) {
            num += i * node.getVal();
            i *= 10;
            node = node.getNext();
        }

        return num;
    }
}
