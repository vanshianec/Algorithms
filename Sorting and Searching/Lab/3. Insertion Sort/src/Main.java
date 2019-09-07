import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] unsortedArray = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        insertionSort(unsortedArray);
        System.out.println(String.join(" ", Arrays.stream(unsortedArray).mapToObj(String::valueOf).toArray(String[]::new)));
    }

    private static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i;
            while (--j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
            }
            array[j + 1] = current;
        }
    }
}
