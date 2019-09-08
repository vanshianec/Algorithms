import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] sortedArray = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int element = Integer.parseInt(consoleReader.readLine());
        int elementIndex = binarySearch(sortedArray, element);
        System.out.println(elementIndex);
    }

    private static int binarySearch(int[] array, int element) {
        return binarySearch(array, element, 0, array.length - 1);
    }

    private static int binarySearch(int[] array, int element, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return -1;
        }
        int middle = (startIndex + endIndex) / 2;
        if (element < array[middle]) {
            middle = binarySearch(array, element, startIndex, middle);
        } else if (element > array[middle]) {
            middle = binarySearch(array, element, middle + 1, endIndex);
        }
        return middle;
    }
}
