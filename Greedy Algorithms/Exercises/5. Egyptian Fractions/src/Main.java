import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fraction = consoleReader.readLine();
        int p = Integer.parseInt(fraction.split("/")[0]);
        int q = Integer.parseInt(fraction.split("/")[1]);
        String startValues = p + "/" + q;

        if (p >= q) {
            System.out.println("Error (fraction is equal to or greater than 1)");
            return;
        }

        int nextQ = 2;
        List<String> result = new ArrayList<>();

        while (p > 0) {
            int currentP = p * nextQ;
            if (currentP >= q) {
                result.add("1/" + nextQ);
                p = currentP - q;
                q *= nextQ;
            }
            nextQ++;
        }

        System.out.printf("%s = %s%n", startValues, String.join(" + ", result));
    }
}
