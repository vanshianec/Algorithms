import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = consoleReader.readLine().split(" ");
        permutationsWithRepetitions(input, 0);
    }

    private static void permutationsWithRepetitions(String[] arr, int index) {
        if (index >= arr.length) {
            System.out.println(String.join(" ", arr));
            return;
        }

        Set<String> swapped = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (!swapped.contains(arr[i])) {
                swap(arr, index, i);
                permutationsWithRepetitions(arr, index + 1);
                swap(arr, index, i);
                swapped.add(arr[i]);
            }
        }


    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
