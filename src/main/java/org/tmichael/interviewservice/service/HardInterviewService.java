package org.tmichael.interviewservice.service;

import org.springframework.stereotype.Component;
import org.tmichael.interviewservice.dao.ListNode;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class HardInterviewService {

    public char[][] solveSudoku(char[][] board) {
        boolean solved;
        do {
            solved = true;
            for (int i = 0; i < board.length; i++) {
                char[] row = board[i];
                for (int j = 0; j < row.length; j++) {
                    char cell = row[j];
                    if ('.' == cell) {
                        Set<Character> possibleVals = initPossibleVals();
                        filterPossibleValsFromRow(possibleVals, row);
                        filterPossibleValsFromCol(possibleVals, board, j);
                        filterPossibleValsFromGrid(possibleVals, board, i, j);
                        if (possibleVals.size() == 1) {
                            board[i][j] = possibleVals.iterator().next();
                        } else {
                            solved = false;
                        }
                    }
                }
            }
        } while (!solved);

        return board;
    }

    public int firstMissingPositive(int[] nums) {
        int firstMissPos = 1;
        for (int num : nums) {
            if (num <= firstMissPos && num > 0) {
                firstMissPos++;
            }
        }

        return firstMissPos;
    }

    public int trapWater(int[] height) {
        int waterUnits = 0;
        int count;
        boolean found;
        do {
            found = false;
            count = 0;
            for (int i = 0; i < height.length; i++) {
                if (height[i] > 0) {
                    if (!found) {
                        found = true;
                    } else {
                        waterUnits += count;
                        count = 0;
                    }
                    height[i] = height[i] - 1;
                } else if (height[i] == 0 && found) {
                    count++;
                }
            }
        } while (found);

        return waterUnits;
    }

    public int longestValidParentheses(String s) {
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (sub.length() > longest && isValid(sub)) {
                    longest = sub.length();
                }
            }
        }

        return longest;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        Map<Integer, ListNode> map = new HashMap<>(k);
        ListNode current = head;
        int idx = 0;
        while (current != null) {
            if (idx >= k) {
                swapVals(map, k);
                idx = 0;
            }
            map.put(idx, current);
            current = current.getNext();
            idx++;
        }
        if (idx >= k) {
            swapVals(map, k);
        }

        return head;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        ListNode mergedList = new ListNode();
        List<Integer> values = new ArrayList<>();
        for (ListNode list : lists) {
            ListNode node = list;
            while (node != null) {
                values.add(node.getVal());
                node = node.getNext();
            }
        }
        Collections.sort(values);
        ListNode node = mergedList;
        for (int i = 0; i < values.size(); i++) {
            node.setVal(values.get(i));
            if (i < values.size() - 1) {
                node.setNext(new ListNode());
                node = node.getNext();
            }
        }

        return mergedList;
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double median = 0;
        List<Integer> merged = new ArrayList<>();
        merged.addAll(Arrays.stream(nums1).boxed().collect(Collectors.toList()));
        merged.addAll(Arrays.stream(nums2).boxed().collect(Collectors.toList()));
        Collections.sort(merged);
        int idx = merged.size() / 2;
        if (merged .size() > 0) {
            if (merged.size() % 2 == 0) {
                int upperMid = merged.get(idx);
                if (idx > 0) {
                    int lowerMid = merged.get(idx - 1);
                    median = (upperMid + lowerMid) / 2.0;
                } else {
                    median = upperMid;
                }
            } else {
                median = merged.get(idx);
            }
        }

        return median;
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<>();
        solveNQueensInner(solutions, new int[n][n], n);

        return solutions;
    }

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> fullJustify = new ArrayList<>();
        List<String> wordsOnLine = new ArrayList<>();
        int remainingWidth = maxWidth;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.length() <= remainingWidth) {
                wordsOnLine.add(word);
                remainingWidth -= i + 1 == words.length ? word.length() : word.length() + 1;
            } else {
                fullJustify.add(addLine(wordsOnLine, maxWidth, false));
                wordsOnLine.clear();
                wordsOnLine.add(word);
                remainingWidth = i + 1 == words.length ? maxWidth - word.length() : maxWidth - word.length() - 1;
            }
        }
        fullJustify.add(addLine(wordsOnLine, maxWidth, true));

        return fullJustify;
    }

    private String addLine(List<String> wordsOnLine, int maxWidth, boolean lastLine) {
        StringBuilder builder = new StringBuilder();
        int numSpaces = maxWidth - wordsOnLine.stream().map(String::length).reduce(0, Integer::sum);
        int gaps = Math.max(1, wordsOnLine.size() - 1);
        int numSpacesBetweenWords = numSpaces / gaps;
        int remainderSpaces = numSpaces % gaps;
        for (int i = 0; i < wordsOnLine.size(); i++) {
            String word = wordsOnLine.get(i);
            builder.append(word);
            if (i + 1 == wordsOnLine.size()) {
                int remainingSpaces = maxWidth - builder.length();
                builder.append(" ".repeat(remainingSpaces));
            } else if (lastLine) {
                builder.append(" ");
            } else if (remainderSpaces > 0) {
                builder.append(" ".repeat(numSpacesBetweenWords + 1));
                remainderSpaces--;
            } else {
                builder.append(" ".repeat(numSpacesBetweenWords));
            }
        }

        return builder.toString();
    }

    public boolean isNumber(String s) {
        boolean isNumber;
        s = s.toLowerCase();
        if (s.contains("e")) {
            String prefix = s.substring(0, s.indexOf("e"));
            String suffix = s.substring(s.indexOf("e") + 1);
            isNumber = (isDecimal(prefix) || isInteger(prefix)) && isInteger(suffix);
        } else {
            isNumber = isDecimal(s) || isInteger(s);
        }

        return isNumber;
    }

    private boolean isDecimal(String s) {
        boolean isDecimal = !s.isEmpty() && !".".equals(s);
        List<Character> validDigits = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        List<Character> validSigns = Arrays.asList('-', '+');
        boolean foundDecimal = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '.' && !foundDecimal) {
                foundDecimal = true;
                continue;
            } else if (c == '.') {
                isDecimal = false;
                break;
            }
            if ((i == 0 && (!validDigits.contains(c) && !validSigns.contains(c))) || (i > 0 && !validDigits.contains(c))) {
                isDecimal = false;
                break;
            }
        }

        return isDecimal;
    }

    private boolean isInteger(String s) {
        boolean isInteger = !s.isEmpty();
        List<Character> validDigits = Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
        List<Character> validSigns = Arrays.asList('-', '+');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if ((i == 0 && (!validDigits.contains(c) && !validSigns.contains(c))) || (i > 0 && !validDigits.contains(c))) {
                isInteger = false;
                break;
            }
        }

        return isInteger;
    }

    public String getPermutation(int n, int k) {
        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            builder.append(i);
        }
        List<String> permutations = new ArrayList<>();
        getPermutationsInner(permutations, "", builder.toString());

        return permutations.get(k);
    }

    private void getPermutationsInner(List<String> permutations, String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                getPermutationsInner(permutations, prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

    private void solveNQueensInner(List<List<String>> solutions, int[][] board, int n) {
        if (n <= 1) {   // base case
            boolean placed = false;
            for (int i = 0; i < board.length;  i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 0) {
                        placeQueen(board, i, j);
                        List<String> solution = buildSolution(board);
                        if (!solutions.contains(solution)) {
                            solutions.add(solution);
                        }
                        placed = true;
                        break;
                    }
                }
                if (placed) {
                    break;
                }
            }
        } else {        // recursive step
            for (int i = 0; i < board.length;  i++) {
                for (int j = 0; j < board.length; j++) {
                    if (board[i][j] == 0) {
                        int[][] backup = copyBoard(board);
                        placeQueen(board, i, j);
                        solveNQueensInner(solutions, board, n - 1);
                        board = backup;
                    }
                }
            }
        }
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[board.length][board.length];
        for (int i = 0; i < board.length; i++) {
            System.arraycopy(board[i], 0, copy[i], 0, board.length);
        }

        return copy;
    }

    private void placeQueen(int[][] board, int x, int y) {
        board[x][y] = 2;
        for (int i = 0; i < board.length; i++) {
            if (board[x][i] == 0) { // Update row
                board[x][i] = 1;
            }
            if (board[i][y] == 0) { // Update column
                board[i][y] = 1;
            }
            if (i >= 1) {           // Update diag
                // Bottom right diag
                int diagX = x + i;
                int diagY = y + i;
                if (diagX < board.length && diagY < board.length && board[diagX][diagY] == 0) {
                    board[diagX][diagY] = 1;
                }
                // Upper left diag
                diagX = x - i;
                diagY = y - i;
                if (diagX >= 0 && diagY >= 0 && board[diagX][diagY] == 0) {
                    board[diagX][diagY] = 1;
                }
                // Bottom left diag
                diagX = x + i;
                diagY = y - i;
                if (diagX < board.length && diagY >= 0 && board[diagX][diagY] == 0) {
                    board[diagX][diagY] = 1;
                }
                // Upper right diag
                diagX = x - i;
                diagY = y + i;
                if (diagX >= 0 && diagY < board.length && board[diagX][diagY] == 0) {
                    board[diagX][diagY] = 1;
                }
            }
        }
    }

    private List<String> buildSolution(int[][] board) {
        List<String> solution = new ArrayList<>();
        for (int[] row : board) {
            StringBuilder builder = new StringBuilder();
            for (int cell : row) {
                builder.append(cell == 2 ? "Q" : ".");
            }
            solution.add(builder.toString());
        }

        return solution;
    }

    private Set<Character> initPossibleVals() {
        Set<Character> possibleVals = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            possibleVals.add(Character.forDigit(i, 10));
        }

        return possibleVals;
    }

    private void filterPossibleValsFromRow(Set<Character> possibleVals, char[] row) {
        for (char cell : row) {
            possibleVals.remove(cell);
        }
    }

    private void filterPossibleValsFromCol(Set<Character> possibleVals, char[][] board, int col) {
        for (char[] row : board) {
            possibleVals.remove(row[col]);
        }
    }

    private void filterPossibleValsFromGrid(Set<Character> possibleVals, char[][] board, int row, int col) {
        char[] grid;
        if (row < 3) {
            if (col < 3) {
                grid = getGrid(board, 0, 3, 0, 3);
            } else if (col < 6) {
                grid = getGrid(board, 0, 3, 3, 6);
            } else {
                grid = getGrid(board, 0, 3, 6, 9);
            }
        } else if (row < 6) {
            if (col < 3) {
                grid = getGrid(board, 3, 6, 0, 3);
            } else if (col < 6) {
                grid = getGrid(board, 3, 6, 3, 6);
            } else {
                grid = getGrid(board, 3, 6, 6, 9);
            }
        } else {
            if (col < 3) {
                grid = getGrid(board, 6, 9, 0, 3);
            } else if (col < 6) {
                grid = getGrid(board, 6, 9, 3, 6);
            } else {
                grid = getGrid(board, 6, 9, 6, 9);
            }
        }
        for (char cell : grid) {
            possibleVals.remove(cell);
        }
    }

    private char[] getGrid(char[][] board, int rowStart, int rowEnd, int colStart, int colEnd) {
        char[] grid = new char[9];
        int idx = 0;
        for (int i = rowStart; i < rowEnd; i++) {
            for (int j = colStart; j < colEnd; j++) {
                grid[idx] = board[i][j];
                idx++;
            }
        }

        return grid;
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            char top = stack.isEmpty() ? ' ' : stack.peek();
            if (c == '(') {
                stack.push(c);
            } else if ((c == ')' && top == '(')) {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }

    private void swapVals(Map<Integer, ListNode> map, int k) {
        for (int i = 0; i < k / 2; i++) {
            int val1 = map.get(i).getVal();
            int val2 = map.get(k - i - 1).getVal();
            map.get(i).setVal(val2);
            map.get(k - i - 1).setVal(val1);
        }
    }
}
