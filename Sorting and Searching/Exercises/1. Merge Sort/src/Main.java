import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] unsortedArray = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        mergeSort(unsortedArray);
        System.out.println(String.join(" ", Arrays.stream(unsortedArray).mapToObj(String::valueOf).toArray(String[]::new)));
    }

    private static void mergeSort(int[] array) {
        if (array.length == 1) {
            return;
        }

        int subLeft[] = new int[array.length / 2];
        for (int i = 0; i < array.length / 2; i++) {
            subLeft[i] = array[i];
        }

        int subRight[] = new int[array.length - subLeft.length];
        for (int i = 0; i < subRight.length; i++) {
            subRight[i] = array[subLeft.length + i];
        }

        mergeSort(subLeft);
        mergeSort(subRight);
        merge(array, subLeft, subRight);
    }

    private static void merge(int[] array, int[] left, int[] right) {

        int leftIndex = left.length - 1;
        int rightIndex = right.length - 1;

        for (int i = array.length - 1; i >= 0; i--) {
            if (leftIndex < 0) {
                while (rightIndex >= 0) {
                    array[i--] = right[rightIndex--];
                }
                break;
            } else if (rightIndex < 0) {
                while (leftIndex >= 0) {
                    array[i--] = left[leftIndex--];
                }
                break;
            }
            if (left[leftIndex] > right[rightIndex]) {
                array[i] = left[leftIndex--];
            } else {
                array[i] = right[rightIndex--];
            }
        }
    }
}
