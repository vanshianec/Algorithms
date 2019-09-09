import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        //EXECUTE N NESTED FOR LOOPS FROM 1 TO N WITH RECURSION

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLoops = Integer.parseInt(consoleReader.readLine());
        int[] arr = new int[numberOfLoops];
        nestedLoops(arr, 0);
    }

    private static void nestedLoops(int[] arr, int index) {
        if (index == arr.length) {
            System.out.println(String.join(" ", Arrays.stream(arr).mapToObj(String::valueOf).toArray(String[]::new)));
            return;
        }

        for (int i = 1; i <= arr.length; i++) {
            arr[index] = i;
            nestedLoops(arr, index + 1);
        }
    }
}
