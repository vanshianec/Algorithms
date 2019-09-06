import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] inputArray = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println(findSum(inputArray, 0));
    }

    private static int findSum(int[] array, int index) {
        if (index == array.length) {
            return 0;
        }
        return array[index] + findSum(array, index + 1);
    }
}
