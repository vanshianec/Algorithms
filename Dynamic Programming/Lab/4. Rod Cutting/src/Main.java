import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] rodPrices = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int length = Integer.parseInt(consoleReader.readLine());
        int[] bestPrice = new int[length + 1];
        int[] bestCombo = new int[length + 1];

        for (int i = 1; i <= length; i++) {
            int currentBest;
            for (int j = 1; j <= i; j++) {
                currentBest = Math.max(bestPrice[i], rodPrices[j] + bestPrice[i - j]);
                if (currentBest > bestPrice[i]) {
                    bestPrice[i] = currentBest;
                    bestCombo[i] = j;
                }
            }
        }

        System.out.println(bestPrice[length]);
        while (length - bestCombo[length] > 0) {
            System.out.printf("%d ", bestCombo[length]);
            length -= bestCombo[length];
        }
        System.out.printf("%d%n", bestCombo[length]);

    }
}
