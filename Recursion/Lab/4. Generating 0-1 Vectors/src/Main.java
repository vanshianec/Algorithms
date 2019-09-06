import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(consoleReader.readLine());
        int[] vector = new int[size];
        generateVectors(vector, 0);
    }

    private static void generateVectors(int[] vectors, int index) {
        if (index == vectors.length) {
            System.out.println(String.join("", Arrays.stream(vectors).mapToObj(String::valueOf).toArray(String[]::new)));
        } else {
            for (int i = 0; i <= 1; i++) {
                vectors[index] = i;
                generateVectors(vectors, index + 1);
            }
        }
    }
}
