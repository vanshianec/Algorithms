import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(consoleReader.readLine());
        recursiveDrawing(n);
    }

    private static void recursiveDrawing(int n) {
        if (n == 0) {
            return;
        }
        printDrawing(n, "*");
        recursiveDrawing(n - 1);
        printDrawing(n, "#");
    }

    private static void printDrawing(int n, String symbol) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < n; i++) {
            result.append(symbol);
        }
        System.out.println(result.toString());
    }
}
