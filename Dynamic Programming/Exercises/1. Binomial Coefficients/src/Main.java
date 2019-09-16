import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(consoleReader.readLine());
        int k = Integer.parseInt(consoleReader.readLine());

        long[][] memoization = new long[n + 1][k + 1];
        long result = binomialCoefficient(n, k, memoization);
        System.out.println(result);

    }

    private static long binomialCoefficient(int n, int k, long[][] memoization) {
        if (memoization[n][k] != 0) {
            return memoization[n][k];
        }

        if (k < 1 || n - 1 < k) {
            return 1;
        }

        long currentValue = binomialCoefficient(n - 1, k - 1, memoization) + binomialCoefficient(n - 1, k, memoization);
        memoization[n][k] = currentValue;
        return currentValue;
    }
}
