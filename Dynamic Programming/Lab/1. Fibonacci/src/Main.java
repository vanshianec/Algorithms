import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(consoleReader.readLine());
        int[] usedValues = new int[n + 1];
        System.out.println(fibonacci(n, usedValues));
    }

    private static int fibonacci(int n, int[] usedValues) {
        if (usedValues[n] != 0) {
            return usedValues[n];
        }

        if (n == 1 || n == 2) {
            return 1;
        }

        int currentValue = fibonacci(n - 1, usedValues) + fibonacci(n - 2, usedValues);
        usedValues[n] = currentValue;
        return currentValue;
    }
}
