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

    public ListNode swapListNodePairs(ListNode head) {
        ListNode previous = null;
        ListNode current = head;
        ListNode next = current == null ? null : current.getNext();
        boolean first = true;
        while (current != null) {
            boolean success = performSwap(previous, current, next);
            if (first && success) {
                head = next;
                first = false;
            }
            previous = current;
            current = current.getNext();
            next = current == null ? null : current.getNext();
        }

        return head;
    }

    public int searchRotatedArray(int[] nums, int target) {
        return searchRotatedArrayInner(nums, 0, nums.length, target);
    }

    public int jumpMin(int[] nums) {
        return jumpMinInner(nums, 0, 0);
    }

    private int jumpMinInner(int[] nums, int idx, int jumpCount) {
        int min;
        if (idx == nums.length - 1) {
            min = jumpCount;
        } else if (idx < nums.length - 1) {
            int possibleJumps = nums[idx];
            int[] counts = new int[possibleJumps];
            for (int i = 1; i <= possibleJumps; i++) {
                counts[i - 1] = jumpMinInner(nums, idx + i, jumpCount + 1);
            }
            min = Arrays.stream(counts).min().orElse(Integer.MAX_VALUE);
        } else {
            min = Integer.MAX_VALUE;
        }

        return min;
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> combos = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSumInner(combos, candidates, candidates.length, target);

        return combos;
    }

    private void combinationSumInner(List<List<Integer>> combos, int[] candidates, int size, int target) {
        if (size == 1 && target % candidates[0] == 0) {
            List<Integer> combo = new ArrayList<>();
            for (int i = 0; i < target / candidates[0]; i++) {
                combo.add(candidates[0]);
            }
            combos.add(combo);
        } else if (size > 1) {
            int total = candidates[size - 1];
            if (total == target) {
                combos.add(Arrays.asList(total));
            } else if (total < target) {
                int remainder = target - total;
                for (int i = size - 2; i >= 0; i--) {
                    int candidate = candidates[i];
                    if (candidate <= remainder) {
                        if (remainder % candidate == 0) {
                            List<Integer> combo = new ArrayList<>();
                            combo.add(candidates[size - 1]);
                            for (int j = 0; j < remainder / candidate; j++) {
                                combo.add(candidate);
                            }
                            combos.add(combo);
                        }
                        remainder -= candidate;
                    }
                }
            }
            combinationSumInner(combos, candidates, size - 1, target);
        }
    }

    public String countAndSay(int n) {
        StringBuilder builder = new StringBuilder();
        if (n <= 1) {
            builder.append("1");
        } else {
            String prefix = countAndSay(n - 1);
            Character c = prefix.charAt(0);
            int count = 0;
            for (int i = 0; i < prefix.length(); i++) {
                if (c == prefix.charAt(i)) {
                    count++;
                } else {
                    builder.append(count);
                    builder.append(c);
                    c = prefix.charAt(i);
                    count = 1;
                }
            }
            builder.append(count);
            builder.append(c);
        }

        return builder.toString();
    }

    public int integerDivide(int dividend, int divisor) {
        int output = 0;
        boolean isNegative = (dividend < 0 || divisor < 0) && !(dividend < 0 && divisor < 0);
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);
        while (dividend >= 0) {
            dividend -= divisor;
            if (dividend >= 0) {
                output++;
            }
        }

        return isNegative ? -1 * output : output;
    }

    public int[] searchArrayRange(int[] nums, int target) {
        int[] range = new int[2];
        range[0] = searchArrayRangeInnerLeft(nums, 0, nums.length, target);
        range[1] = searchArrayRangeInnerRight(nums, nums.length - 1, nums.length, target);

        return range;
    }

    private int searchArrayRangeInnerLeft(int[] nums, int idx, int size, int target) {
        int lowIdx = -1;
        if (size > 0 && nums[idx] == target) {
            lowIdx = idx;
        } else if (size > 1) {
            int halfSize = size / 2;
            int remainder = size % 2;
            lowIdx = searchArrayRangeInnerLeft(nums, idx, halfSize, target);
            if (lowIdx == -1) {
                lowIdx = searchArrayRangeInnerLeft(nums, idx + halfSize, halfSize + remainder, target);
            }
        }

        return lowIdx;
    }

    private int searchArrayRangeInnerRight(int[] nums, int idx, int size, int target) {
        int hiIdx = -1;
        if (size > 0 && nums[idx] == target) {
            hiIdx = idx;
        } else if (size > 1) {
            int halfSize = size / 2;
            int remainder = size % 2;
            hiIdx = searchArrayRangeInnerRight(nums, idx, halfSize + remainder, target);
            if (hiIdx == -1) {
                hiIdx = searchArrayRangeInnerRight(nums, idx - halfSize - remainder, halfSize, target);
            }
        }

        return hiIdx;
    }

    private int searchRotatedArrayInner(int[] nums, int startIdx, int size, int target) {
        int idx = -1;
        if (nums[startIdx] == target) {
            idx = startIdx;
        } else if (size > 1 && !canSkip(nums, startIdx, size, target)) {
            int halfSize = size / 2;
            int remainder = size % 2;
            idx = searchRotatedArrayInner(nums, startIdx, halfSize, target);
            if (idx == -1) {
                idx = searchRotatedArrayInner(nums, startIdx + halfSize, halfSize + remainder, target);
            }
        }

        return idx;
    }

    private boolean canSkip(int[] nums, int startIdx, int size, int target) {
        return nums[startIdx] < nums[startIdx + size - 1]                           // array is sorted
                && (target < nums[startIdx] || target > nums[startIdx + size - 1]); // target is outside array bounds
    }

    private boolean performSwap(ListNode previous, ListNode current, ListNode next) {
        boolean canSwap = next != null;
        if (canSwap) {
            current.setNext(next.getNext());
            next.setNext(current);
            if (previous != null) {
                previous.setNext(next);
            }
        }

        return canSwap;
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
