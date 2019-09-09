import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

        //REVERSE ARRAY USING RECURSION

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        reverseArray(array, 0);
        System.out.println(String.join(" ", Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new)));
    }

    public static void reverseArray(int[] array, int index) {
        if (index == array.length / 2) {
            return;
        }
        int temp = array[index];
        array[index] = array[array.length - 1 - index];
        array[array.length - 1 - index] = temp;
        reverseArray(array, index + 1);
    }

}
