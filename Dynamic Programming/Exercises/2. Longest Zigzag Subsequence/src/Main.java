import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

        int[] sequence = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        HashMap<Integer, Integer> longestZigZagSubSequence = new HashMap<>();
        int[] subSequencesLengths = new int[sequence.length];
        //first two values are always zig zag subsequence
        subSequencesLengths[0] = 1;
        subSequencesLengths[1] = 2;
        longestZigZagSubSequence.put(1, sequence[0]);
        longestZigZagSubSequence.put(2, sequence[1]);

        if (sequence.length <= 2) {
            System.out.println(String.join(" ", Arrays.stream(sequence).mapToObj(String::valueOf).toArray(String[]::new)));
            return;
        }

        for (int i = 2; i < sequence.length; i++) {

            int currentValue = sequence[i];
            subSequencesLengths[i] = 1;

            for (int j = 1; j < i; j++) {
                if (isValidZigZag(sequence[j - 1], sequence[j], currentValue) && subSequencesLengths[i] <= subSequencesLengths[j]) {
                    if (subSequencesLengths[j - 1] == subSequencesLengths[j]) {
                        longestZigZagSubSequence.put(subSequencesLengths[j], sequence[j]);
                        longestZigZagSubSequence.putIfAbsent(subSequencesLengths[j] + 1, sequence[i]);
                        subSequencesLengths[i] = subSequencesLengths[j] + 1;
                    } else if (subSequencesLengths[j - 1] + 1 == subSequencesLengths[j]) {
                        longestZigZagSubSequence.putIfAbsent(subSequencesLengths[j] + 1, sequence[i]);
                        subSequencesLengths[i] = subSequencesLengths[j] + 1;
                    }
                }
            }
        }

        System.out.println(String.join(" ", longestZigZagSubSequence.values().stream().map(String::valueOf).toArray(String[]::new)));
    }

    private static boolean isValidZigZag(int first, int middle, int last) {
        //valid examples
        //8 3 5
        //2 9 4
        return (middle < first && middle < last) || (middle > first && middle > last);
    }
}
