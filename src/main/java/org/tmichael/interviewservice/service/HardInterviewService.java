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
        int block = 0;
        while (block < n * n) {
            int queensPlaced = 0;
            int[][] board = initBoard(n, block);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] == 0) {
                        placeQueen(board, i, j);
                        queensPlaced++;
                    }
                }
            }
            if (queensPlaced >= n) {
                solutions.add(buildSolution(board));
            }
            block++;
        }

        return solutions;
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

    private void placeQueen(int[][] board, int x, int y) {
        board[x][y] = 2;
        for (int j = 0; j < board.length; j++) {        // Update row
            if (board[x][j] == 0) {
                board[x][j] = 1;
            }
        }
        for (int i = 0; i < board.length; i++) {        // Update column
            if (board[i][y] == 0) {
                board[i][y] = 1;
            }
        }
        for (int i = 1; i < board.length; i++) {        // Update diag
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

    private int[][] initBoard(int n, int block) {
        int[][] board = new int[n][n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i + j >= block || count >= block) {
                    board[i][j] = 0;
                } else {
                    board[i][j] = 1;
                    count++;
                }
            }
        }

        return board;
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
