import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String firstWord = consoleReader.readLine();
        String secondWord = consoleReader.readLine();

        int[][] commonSubSequencesLength = new int[secondWord.length() + 1][firstWord.length() + 1];

        for (int row = 1; row < commonSubSequencesLength.length; row++) {
            for (int col = 1; col < commonSubSequencesLength[0].length; col++) {
                if (firstWord.charAt(col - 1) == secondWord.charAt(row - 1)) {
                    commonSubSequencesLength[row][col] = commonSubSequencesLength[row - 1][col - 1] + 1;
                    continue;
                }
                commonSubSequencesLength[row][col] = Math.max(commonSubSequencesLength[row - 1][col], commonSubSequencesLength[row][col - 1]);
            }
        }

        System.out.println(commonSubSequencesLength[secondWord.length()][firstWord.length()]);
    }
}
