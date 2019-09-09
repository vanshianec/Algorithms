import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        //all combinations with duplicates of k elements from a set of n elements
        //(k <= n)

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(consoleReader.readLine());
        int n = Integer.parseInt(consoleReader.readLine());
        int[] array = new int[n];
        combinationsWithRepetition(array, 0, 1, k);
    }

    private static void combinationsWithRepetition(int[] array, int index, int border, int k) {
        if (index == array.length) {
            System.out.println(String.join(" ", Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new)));
            return;
        }

        for (int i = border; i <= k; i++) {
            array[index] = i;
            combinationsWithRepetition(array, index + 1, border, k);
            border++;
        }
    }
}
