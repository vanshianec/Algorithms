import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(7).split(", ");
        int[] coins = new int[elements.length];
        for (int i = 0; i < coins.length; i++) {
            coins[i] = Integer.parseInt(elements[i]);
        }

        int targetSum = Integer.parseInt(in.nextLine().substring(5));


        Map<Integer, Integer> usedCoins = chooseCoins(coins, targetSum);
        usedCoins.forEach((k, v) -> System.out.println(k + " " + v));
    }

    public static Map<Integer, Integer> chooseCoins(int[] coins, int targetSum) {
        Map<Integer, Integer> coinsUsed = new LinkedHashMap<>();
        Arrays.sort(coins);
        int currentSum = 0;
        int largestCoinIndex = coins.length - 1;

        while (currentSum != targetSum && largestCoinIndex >= 0) {
            int currentCoin = coins[largestCoinIndex];
            int remainingSum = targetSum - currentSum;
            int coinsToTake = remainingSum / currentCoin;
            if (coinsToTake > 0) {
                coinsUsed.put(currentCoin, coinsToTake);
                currentSum += currentCoin * coinsToTake;
            }
            largestCoinIndex--;
        }

        if (currentSum != targetSum) {
            throw new IllegalArgumentException();
        }

        return coinsUsed;
    }
}
