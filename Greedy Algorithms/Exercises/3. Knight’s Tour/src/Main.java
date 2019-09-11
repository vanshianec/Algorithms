import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static int[][] board;
    private static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(consoleReader.readLine());
        board = new int[n][n];
        board[0][0] = count;
        printKnightTour(0, 0);
    }

    private static void printKnightTour(int row, int col) {

        while (count < board.length * board.length) {
            List<String> possibleMoves = getPossibleMoves(row, col);
            List<String> bestMoves = possibleMoves
                    .stream()
                    .sorted(Comparator.comparingInt(a -> getNumberOfAllowedMoves(Integer.parseInt(a.split(" ")[0]), Integer.parseInt(a.split(" ")[1]))))
                    .collect(Collectors.toList());
            String bestMove = bestMoves.get(0);
            int r = Integer.parseInt(bestMove.split(" ")[0]);
            int c = Integer.parseInt(bestMove.split(" ")[1]);
            board[r][c] = ++count;
            row = r;
            col = c;
        }

        printBoard();
    }

    private static List<String> getPossibleMoves(int row, int col) {
        List<String> result = new ArrayList<>();
        if (!isOutOfBounds(row - 2, col - 1) && !isTaken(row - 2, col - 1)) {
            result.add(String.format("%d %d", row - 2, col - 1));
        }
        if (!isOutOfBounds(row - 1, col - 2) && !isTaken(row - 1, col - 2)) {
            result.add(String.format("%d %d", row - 1, col - 2));
        }
        if (!isOutOfBounds(row - 1, col + 2) && !isTaken(row - 1, col + 2)) {
            result.add(String.format("%d %d", row - 1, col + 2));
        }
        if (!isOutOfBounds(row - 2, col + 1) && !isTaken(row - 2, col + 1)) {
            result.add(String.format("%d %d", row - 2, col + 1));
        }
        if (!isOutOfBounds(row + 2, col - 1) && !isTaken(row + 2, col - 1)) {
            result.add(String.format("%d %d", row + 2, col - 1));
        }
        if (!isOutOfBounds(row + 1, col - 2) && !isTaken(row + 1, col - 2)) {
            result.add(String.format("%d %d", row + 1, col - 2));
        }
        if (!isOutOfBounds(row + 1, col + 2) && !isTaken(row + 1, col + 2)) {
            result.add(String.format("%d %d", row + 1, col + 2));
        }
        if (!isOutOfBounds(row + 2, col + 1) && !isTaken(row + 2, col + 1)) {
            result.add(String.format("%d %d", row + 2, col + 1));
        }

        return result;
    }

    private static int getNumberOfAllowedMoves(int row, int col) {
        int count = 0;
        if (!isOutOfBounds(row - 2, col - 1) && !isTaken(row - 2, col - 1)) {
            count++;
        }
        if (!isOutOfBounds(row - 1, col - 2) && !isTaken(row - 1, col - 2)) {
            count++;
        }
        if (!isOutOfBounds(row - 1, col + 2) && !isTaken(row - 1, col + 2)) {
            count++;
        }
        if (!isOutOfBounds(row - 2, col + 1) && !isTaken(row - 2, col + 1)) {
            count++;
        }
        if (!isOutOfBounds(row + 2, col - 1) && !isTaken(row + 2, col - 1)) {
            count++;
        }
        if (!isOutOfBounds(row + 1, col - 2) && !isTaken(row + 1, col - 2)) {
            count++;
        }
        if (!isOutOfBounds(row + 1, col + 2) && !isTaken(row + 1, col + 2)) {
            count++;
        }
        if (!isOutOfBounds(row + 2, col + 1) && !isTaken(row + 2, col + 1)) {
            count++;
        }
        return count;
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || col < 0 || row >= board.length || col >= board[0].length;
    }

    private static boolean isTaken(int row, int col) {
        return board[row][col] != 0;
    }

    private static void printBoard() {
        Arrays.stream(board)
                .forEach(row -> System.out.println(String.join(" ",
                        Arrays.stream(row).mapToObj(String::valueOf).toArray(String[]::new))));
    }

}
