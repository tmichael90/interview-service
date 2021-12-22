package org.tmichael.interviewservice.service;

import org.springframework.stereotype.Component;
import org.tmichael.interviewservice.dao.ListNode;

import java.util.*;

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

    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < height.length; j++) {
                if (i == j) {
                    continue;
                }
                int waterHeight = Math.min(height[i], height[j]);
                int waterLength = Math.abs(j - i);
                int area = waterHeight * waterLength;
                if (area > max) {
                    max = area;
                }
            }
        }

        return max;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> sum = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                for (int k = 0; k < nums.length; k++) {
                    if (i != j && i != k && j != k && nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> potential = Arrays.asList(nums[i], nums[j], nums[k]);
                        boolean duplicate = false;
                        for (List<Integer> triplet : sum) {
                            if (triplet.containsAll(potential)) {
                                duplicate = true;
                                break;
                            }
                        }
                        if (!duplicate) {
                            sum.add(potential);
                        }
                    }
                }
            }
        }

        return sum;
    }

    public List<String> letterCombinations(String digits) {
        List<String> combos = new ArrayList<>();
        Map<String,List<String>> letterMap = new HashMap<>();
        letterMap.put("0", Arrays.asList(" "));
        letterMap.put("1", Arrays.asList());
        letterMap.put("2", Arrays.asList("a", "b", "c"));
        letterMap.put("3", Arrays.asList("d", "e", "f"));
        letterMap.put("4", Arrays.asList("g", "h", "i"));
        letterMap.put("5", Arrays.asList("j", "k", "l"));
        letterMap.put("6", Arrays.asList("m", "n", "o"));
        letterMap.put("7", Arrays.asList("p", "q", "r", "s"));
        letterMap.put("8", Arrays.asList("t", "u", "v"));
        letterMap.put("9", Arrays.asList("w", "x", "y", "z"));

        letterCombinationsInner(letterMap, combos, "", digits);

        return combos;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode previous = null;
        ListNode current = head;
        while (current != null) {
            if (shouldRemove(current, n)) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                break;
            }
            previous = current;
            current = current.getNext();
        }

        return head;
    }

    public List<String> generateParenthesis(int n) {
        List<String> parens = new ArrayList<>();
        generateParenthesisInner(parens, "", n * 2);

        return parens;
    }

    private void generateParenthesisInner(List<String> parens, String paren, int n) {
        if (n == 0 && isParenValid(paren)) {
            parens.add(paren);
        } else if (n > 0) {
            for (String s : Arrays.asList("(", ")")) {
                generateParenthesisInner(parens, paren + s, n - 1);
            }
        }
    }

    private boolean isParenValid(String s) {
        Stack<Character> stack = new Stack<>();
        boolean isValid = true;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char top = stack.isEmpty() ? ' ' : stack.peek();
            if (c == '(') {
                stack.push(c);
            } else if (c == ')' && top == '(') {
                stack.pop();
            } else if (c== ')') {
                isValid = false;
                break;
            }
        }

        return isValid && stack.isEmpty();
    }

    private boolean shouldRemove(ListNode node, int n) {
        boolean shouldRemove = false;
        if (node.getNext() == null && n == 1) {
            shouldRemove = true;
        } else if (node.getNext() != null && n > 1) {
            shouldRemove = shouldRemove(node.getNext(), n - 1);
        }

        return shouldRemove;
    }

    private void letterCombinationsInner(Map<String,List<String>> letterMap, List<String> combos, String combo, String digits) {
        if (digits.isEmpty()) {
            if (!combo.isEmpty()) {
                combos.add(combo);
            }
        } else {
            String digit = Character.toString(digits.charAt(0));
            for (String letter : letterMap.get(digit)) {
                letterCombinationsInner(letterMap, combos, combo + letter, digits.substring(1));
            }
        }
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
