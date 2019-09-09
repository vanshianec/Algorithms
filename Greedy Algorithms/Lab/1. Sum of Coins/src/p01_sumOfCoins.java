import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class p01_sumOfCoins {

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
