import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = consoleReader.readLine().split(" ");
        permutationsWithoutRepetitions(input, 0);
    }

    private static void permutationsWithoutRepetitions(String[] arr, int index) {
        if (index == arr.length) {
            System.out.println(String.join(" ", arr));
            return;
        }

        permutationsWithoutRepetitions(arr, index + 1);
        for (int i = index + 1; i < arr.length; i++) {
            swap(arr, index, i);
            permutationsWithoutRepetitions(arr, index + 1);
            swap(arr, index, i);
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
