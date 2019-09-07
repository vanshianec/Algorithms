import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] unsortedArray = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        bubbleSort(unsortedArray);
        System.out.println(String.join(" ", Arrays.stream(unsortedArray).mapToObj(String::valueOf).toArray(String[]::new)));
    }

    private static void bubbleSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean sorted = true;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
    }
}
