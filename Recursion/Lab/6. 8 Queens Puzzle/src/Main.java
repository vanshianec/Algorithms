import java.util.Arrays;

public class Main {

    private static String[][] board = new String[8][8];

    public static void main(String[] args) {
        fillBoard();
        solveEightQueens(0);
    }

    private static void solveEightQueens(int row) {
        if (row == board.length) {
            printBoard();
        } else {
            for (int col = 0; col < board[row].length; col++) {
                if (canPlaceQueen(row, col)) {
                    markVisitedPosition(row, col);
                    solveEightQueens(row + 1);
                    unmarkVisitedPosition(row, col);
                }
            }
        }
    }

    private static boolean canPlaceQueen(int row, int col) {
        return isRowFree(col) && isColFree(row)
                && isTopLeftDiagonalFree(row, col) && isTopRightDiagonalFree(row, col)
                && isBottomLeftDiagonalFree(row, col) && isBottomRightDiagonalFree(row, col);
    }

    private static void markVisitedPosition(int row, int col) {
        board[row][col] = "*";
    }

    private static void unmarkVisitedPosition(int row, int col) {
        board[row][col] = "-";
    }

    private static boolean isRowFree(int col) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col].equals("*")) {
                return false;
            }
        }
        return true;
    }

    private static boolean isColFree(int row) {
        for (int i = 0; i < board[0].length; i++) {
            if (board[row][i].equals("*")) {
                return false;
            }
        }
        return true;
    }

    private static boolean isTopLeftDiagonalFree(int row, int col) {
        while (--col > -1 && --row > -1) {
            if (board[row][col].equals("*")) {
                return false;
            }
        }
        return true;
    }

    private static boolean isTopRightDiagonalFree(int row, int col) {
        while (++col < 8 && --row > -1) {
            if (board[row][col].equals("*")) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBottomLeftDiagonalFree(int row, int col) {
        while (--col > -1 && ++row < 8) {
            if (board[row][col].equals("*")) {
                return false;
            }
        }
        return true;
    }

    private static boolean isBottomRightDiagonalFree(int row, int col) {
        while (++col < 8 && ++row < 8) {
            if (board[row][col].equals("*")) {
                return false;
            }
        }
        return true;
    }

    private static void fillBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = "-";
            }
        }
    }

    private static void printBoard() {
        StringBuilder result = new StringBuilder();
        Arrays.stream(board).forEach(r -> result.append(String.join(" ", r)).append(" ").append(System.lineSeparator()));
        System.out.println(result.toString());
    }
}
