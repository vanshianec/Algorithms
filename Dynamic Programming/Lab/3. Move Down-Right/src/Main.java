import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int rows = Integer.parseInt(consoleReader.readLine());
        int cols = Integer.parseInt(consoleReader.readLine());

        int matrix[][] = new int[rows][cols];

        readInput(consoleReader, rows, matrix);

        fillHighestSumOfCells(rows, cols, matrix);

        List<String> result = new ArrayList<>();
        int currentRow = matrix.length - 1;
        int currentCol = matrix[0].length - 1;

        backtrackHighestSumPath(matrix, result, currentRow, currentCol);

        printPath(result);
    }

    private static void readInput(BufferedReader consoleReader, int rows, int[][] matrix) throws IOException {
        for (int i = 0; i < rows; i++) {
            matrix[i] = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }

    private static void fillHighestSumOfCells(int rows, int cols, int[][] matrix) {
        for (int i = 1; i < rows; i++) {
            matrix[i][0] = matrix[i - 1][0] + matrix[i][0];
        }

        for (int i = 1; i < cols; i++) {
            matrix[0][i] = matrix[0][i - 1] + matrix[0][i];
        }

        for (int row = 1; row < matrix.length; row++) {
            for (int col = 1; col < matrix[0].length; col++) {
                matrix[row][col] = Math.max(matrix[row - 1][col], matrix[row][col - 1]) + matrix[row][col];
            }
        }
    }

    private static void backtrackHighestSumPath(int[][] matrix, List<String> result, int currentRow, int currentCol) {
        result.add(String.format("[%d, %d]", currentRow, currentCol));
        while (currentCol != 0 || currentRow != 0) {

            if (currentRow - 1 < 0) {
                result.add(String.format("[%d, %d]", currentRow, currentCol - 1));
                currentCol--;
            } else if (currentCol - 1 < 0) {
                result.add(String.format("[%d, %d]", currentRow - 1, currentCol));
                currentRow--;
            } else if (matrix[currentRow - 1][currentCol] <= matrix[currentRow][currentCol - 1]) {
                result.add(String.format("[%d, %d]", currentRow, currentCol - 1));
                currentCol--;
            } else {
                result.add(String.format("[%d, %d]", currentRow - 1, currentCol));
                currentRow--;
            }
        }
    }

    private static void printPath(List<String> result) {
        Collections.reverse(result);
        System.out.println(String.join(" ", result));
    }

}
