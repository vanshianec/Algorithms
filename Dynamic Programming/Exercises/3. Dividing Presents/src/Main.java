import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] sequence = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int totalSum = Arrays.stream(sequence).sum();
        int targetSum = totalSum / 2;
        int closestTargetSum = Integer.MAX_VALUE;

        HashMap<Integer, Integer> possibleSums = new HashMap<>();
        HashSet<Integer> sums = new HashSet<>();

        for (int i = 0; i < sequence.length; i++) {
            HashSet<Integer> currentSums = new HashSet<>();
            for (Integer sum : sums) {
                int currentSum = sequence[i] + sum;
                int currentDifference = Math.abs(targetSum - currentSum);
                if (currentDifference <= closestTargetSum) {
                    closestTargetSum = currentDifference;
                }
                currentSums.add(currentSum);
                possibleSums.putIfAbsent(currentSum, sequence[i]);
            }
            int currentDifference = Math.abs(targetSum - sequence[i]);
            if (currentDifference <= closestTargetSum) {
                closestTargetSum = currentDifference;
            }
            possibleSums.putIfAbsent(sequence[i], sequence[i]);
            sums.add(sequence[i]);
            sums.addAll(currentSums);
        }

        int firstSums = targetSum - closestTargetSum;
        int secondSums = totalSum - firstSums;
        int firstSumCopy = firstSums;

        List<Integer> result = new ArrayList<>();
        while (firstSumCopy > 0) {
            int currentNumber = possibleSums.get(firstSumCopy);
            result.add(currentNumber);
            firstSumCopy -= currentNumber;
        }

        System.out.printf("Difference: %d%nAlan:%d Bob:%d%nAlan takes: %s%nBob takes the rest.%n",
                secondSums - firstSums, firstSums, secondSums, result.stream().map(String::valueOf).collect(Collectors.joining(" ")));

    }
}
