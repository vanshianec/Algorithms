import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static char[][] labyrinth;
    private static StringBuilder path = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        createLabyrinth(consoleReader);
        findAllPaths(0, 0, "");
    }

    private static void findAllPaths(int row, int col, String way) {
        if (isOutOfBounds(row, col)) {
            return;
        }

        path.append(way);

        if (labyrinth[row][col] == 'e') {
            System.out.println(path.toString());
        } else if (isNotVisited(row, col) && isPassable(row, col)) {
            mark(row, col);
            findAllPaths(row + 1, col, "D");
            findAllPaths(row - 1, col, "U");
            findAllPaths(row, col + 1, "R");
            findAllPaths(row, col - 1, "L");
            unmark(row, col);
        }

        path.replace(path.length() == 0 ? 0 : path.length() - 1, path.length(), "");
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || col < 0 || row == labyrinth.length || col == labyrinth[0].length;
    }

    private static boolean isPassable(int row, int col) {
        return labyrinth[row][col] != '*';
    }

    private static boolean isNotVisited(int row, int col) {
        return labyrinth[row][col] != 'x';
    }

    private static void mark(int row, int col) {
        labyrinth[row][col] = 'x';
    }

    private static void unmark(int row, int col) {
        labyrinth[row][col] = '-';
    }

    private static void createLabyrinth(BufferedReader consoleReader) throws IOException {
        int rowSize = Integer.parseInt(consoleReader.readLine());
        int colSize = Integer.parseInt(consoleReader.readLine());
        labyrinth = new char[rowSize][colSize];
        for (int i = 0; i < rowSize; i++) {
            labyrinth[i] = consoleReader.readLine().toCharArray();
        }
    }
}
