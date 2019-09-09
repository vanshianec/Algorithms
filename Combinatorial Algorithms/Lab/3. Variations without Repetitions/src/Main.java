import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = consoleReader.readLine().split(" ");
        int k = Integer.parseInt(consoleReader.readLine());
        String[] result = new String[k];
        boolean[] used = new boolean[input.length];
        variationsWithoutRepetitions(input, result, 0, used);
    }

    private static void variationsWithoutRepetitions(String[] arr, String[] result, int index, boolean[] used) {
        if (index >= result.length) {
            System.out.println(String.join(" ", result));
            return;
        }

        for (int i = 0; i < arr.length; i ++) {
            if (!used[i]) {
                used[i] = true;
                result[index] = arr[i];
                variationsWithoutRepetitions(arr, result, index + 1, used);
                used[i] = false;
            }
        }
    }

}
