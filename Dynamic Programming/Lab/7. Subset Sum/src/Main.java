import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] sequence = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        if (sequence.length == 0) {
            System.out.println(0);
            return;
        }
        int targetSum = Integer.parseInt(consoleReader.readLine());

        HashSet<Integer> sums = new HashSet<>();
        HashMap<Integer, Integer> possibleSums = new HashMap<>();

        for (int i = 0; i < sequence.length; i++) {
            HashSet<Integer> newSum = new HashSet<>();
            for (Integer sum : sums) {
                int currentSum = sum + sequence[i];
                newSum.add(currentSum);
                possibleSums.putIfAbsent(currentSum, sequence[i]);
            }
            sums.add(sequence[i]);
            possibleSums.putIfAbsent(sequence[i], sequence[i]);
            sums.addAll(newSum);
        }

        if (!possibleSums.containsKey(targetSum)) {
            System.out.println("No results found.");
            return;
        }

        Stack<Integer> numbersFormingTargetSum = new Stack<>();

        while (targetSum > 0) {
            int lastNumber = possibleSums.get(targetSum);
            numbersFormingTargetSum.push(lastNumber);
            targetSum -= lastNumber;
        }

        //print the numbers which sum gives the target sum
        StringBuilder result = new StringBuilder();
        while (!numbersFormingTargetSum.isEmpty()) {
            result.append(String.format("%d ", numbersFormingTargetSum.pop()));
        }

        System.out.println(result.toString().trim());
    }
}
