import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] sequence = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] increasingSequenceLength = new int[sequence.length];
        int[] prevElementsIndices = new int[sequence.length];
        int maxLength = 0;
        int lastIndex = -1;

        for (int i = 0; i < sequence.length; i++) {

            increasingSequenceLength[i] = 1;
            prevElementsIndices[i] = -1;

            for (int j = 0; j < i; j++) {
                if (sequence[i] > sequence[j] && increasingSequenceLength[i] <= increasingSequenceLength[j]) {
                    increasingSequenceLength[i] = increasingSequenceLength[j] + 1;
                    prevElementsIndices[i] = j;
                }
            }

            if (increasingSequenceLength[i] > maxLength) {
                maxLength = increasingSequenceLength[i];
                lastIndex = i;
            }
        }

        List<Integer> longestSequence = new ArrayList<>();
        while (lastIndex != -1) {
            longestSequence.add(sequence[lastIndex]);
            lastIndex = prevElementsIndices[lastIndex];
        }

        Collections.reverse(longestSequence);

        System.out.println(longestSequence.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}