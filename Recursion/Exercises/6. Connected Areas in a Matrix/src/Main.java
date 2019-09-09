import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static char[][] matrix;


    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        fillMatrix(consoleReader);
        List<Area> areas = addAreas();
        printResult(areas);
    }

    private static void fillMatrix(BufferedReader consoleReader) throws IOException {
        int height = Integer.parseInt(consoleReader.readLine());
        int width = Integer.parseInt(consoleReader.readLine());
        matrix = new char[height][width];
        for (int i = 0; i < height; i++) {
            matrix[i] = consoleReader.readLine().toCharArray();
        }
    }

    private static List<Area> addAreas() {
        List<Area> areas = new ArrayList<>();
        String startingLocation = getStartingLocation();
        while (!startingLocation.equals("")) {
            int startingRow = Integer.parseInt(startingLocation.split(" ")[0]);
            int startingCol = Integer.parseInt(startingLocation.split(" ")[1]);
            int areaSize = markArea(startingRow, startingCol, 0);
            Area area = new Area(startingRow, startingCol, areaSize);
            areas.add(area);
            startingLocation = getStartingLocation();
        }
        return areas;
    }

    private static void printResult(List<Area> areas) {
        areas = areas.stream().sorted((a, b) -> b.getSize() - a.getSize()).collect(Collectors.toList());

        System.out.printf("Total areas found: %d%n", areas.size());
        int count = 1;
        for (Area area : areas) {
            System.out.printf("Area #%d at %s%n", count, area.toString());
            count++;
        }
    }

    private static String getStartingLocation() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (isPassable(i, j) && !isMarked(i, j)) {
                    return i + " " + j;
                }
            }
        }
        return "";
    }

    private static int markArea(int row, int col, int size) {
        if (isOutOfBounds(row, col) || !isPassable(row, col)) {
            return 0;
        }

        if (isMarked(row, col)) {
            return 0;
        }

        markCell(row, col);
        size++;
        size += markArea(row + 1, col, 0);
        size += markArea(row - 1, col, 0);
        size += markArea(row, col + 1, 0);
        size += markArea(row, col - 1, 0);
        return size;
    }

    private static boolean isOutOfBounds(int row, int col) {
        return row < 0 || col < 0 || row >= matrix.length || col >= matrix[0].length;
    }

    private static void markCell(int row, int col) {
        matrix[row][col] = 'X';
    }

    private static boolean isPassable(int row, int col) {
        return matrix[row][col] != '*';
    }

    private static boolean isMarked(int row, int col) {
        return matrix[row][col] == 'X';
    }
}

