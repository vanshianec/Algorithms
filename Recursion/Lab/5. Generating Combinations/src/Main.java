import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] combinationNumbers = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int size = Integer.parseInt(consoleReader.readLine());
        int[] array = new int[size];
        generateCombinations(array, combinationNumbers, 0, 0);
    }

    private static void generateCombinations(int[] array, int[] combinationNumbers, int index, int border) {
        if (index == array.length) {
            System.out.println(String.join(" ", Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new)));
        } else {
            for (int i = border; i < combinationNumbers.length; i++) {
                array[index] = combinationNumbers[i];
                generateCombinations(array, combinationNumbers, index + 1, border + 1);
                border++;
            }
        }
    }
}
