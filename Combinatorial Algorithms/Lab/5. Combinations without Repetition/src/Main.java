import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = consoleReader.readLine().split(" ");
        int k = Integer.parseInt(consoleReader.readLine());
        String[] result = new String[k];
        combinationsWithoutRepetitions(input, result, 0, 0);
    }

    private static void combinationsWithoutRepetitions(String[] arr, String[] result, int index, int border) {
        if (index >= result.length) {
            System.out.println(String.join(" ", result));
            return;
        }

        for (int i = border; i < arr.length; i++) {
            result[index] = arr[i];
            combinationsWithoutRepetitions(arr, result, index + 1, border + 1);
            border++;
        }
    }
}
