import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] unsortedArray = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        mergeSort(unsortedArray, 0, unsortedArray.length - 1);
        System.out.println(String.join(" ", Arrays.stream(unsortedArray).mapToObj(String::valueOf).toArray(String[]::new)));
    }

    private static void mergeSort(int[] array, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int middleIndex = (startIndex + endIndex) / 2;
        mergeSort(array, startIndex, middleIndex);
        mergeSort(array, middleIndex + 1, endIndex);
        merge(array, startIndex, middleIndex, endIndex);
    }

    private static void merge(int[] array, int startIndex, int middleIndex, int endIndex) {
        //already sorted
        if (middleIndex < 0 || middleIndex + 1 >= array.length || array[middleIndex] <= array[middleIndex + 1]) {
            return;
        }

        int[] unsortedValues = new int[array.length];
        for (int i = startIndex; i <= endIndex; i++) {
            unsortedValues[i] = array[i];
        }

        int leftIndex = startIndex;
        int rightIndex = middleIndex + 1;

        for (int i = startIndex; i <= endIndex; i++) {
            if (leftIndex > middleIndex) {
                array[i] = unsortedValues[rightIndex++];
            } else if (rightIndex > endIndex) {
                array[i] = unsortedValues[leftIndex++];
            } else if (unsortedValues[leftIndex] <= unsortedValues[rightIndex]) {
                array[i] = unsortedValues[leftIndex++];
            } else {
                array[i] = unsortedValues[rightIndex++];
            }
        }

    }
}
