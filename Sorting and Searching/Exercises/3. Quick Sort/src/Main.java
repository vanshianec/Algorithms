import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(consoleReader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        quickSort(array, 0, array.length - 1);
        System.out.println(String.join(" ", Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new)));
    }

    private static void quickSort(int[] array, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int pivot = partition(array, startIndex, endIndex);
            quickSort(array, startIndex, pivot);
            quickSort(array, pivot + 1, endIndex);
        }
    }

    private static int partition(int[] array, int startIndex, int endIndex) {
        //OPTIONAL RANDOM PIVOT
        //BETTER WHEN INPUT IS NEARLY SORTED OTHERWISE USE START/END PIVOT

        //Random random = new Random();
        //int pivotIndex = startIndex + random.nextInt(endIndex - startIndex);
        //swap(array, startIndex, pivotIndex);

        int pivot = array[startIndex];
        int leftWall = startIndex + 1;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (array[i] < pivot) {
                swap(array, i, leftWall);
                leftWall++;
            }
        }
        //swap pivot
        leftWall--;
        array[startIndex] = array[leftWall];
        array[leftWall] = pivot;
        return leftWall;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[j];
        array[j] = array[i];
        array[i] = temp;
    }
}
