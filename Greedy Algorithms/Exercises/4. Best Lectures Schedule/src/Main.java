import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfLectures = Integer.parseInt(consoleReader.readLine().split(": ")[1]);
        List<String> lectures = new ArrayList<>();
        for (int i = 0; i < numberOfLectures; i++) {
            lectures.add(consoleReader.readLine());
        }

        lectures.sort((a, b) -> getEndTime(a) - getEndTime(b));

        int lecturesCount = 0;

        List<String> result = new ArrayList<>();
        while (lectures.size() > 0) {
            String currentLecture = lectures.remove(0);
            int startTime = getStartTime(currentLecture);
            int endTime = getEndTime(currentLecture);
            String name = currentLecture.split(": ")[0];
            result.add(String.format("%d-%d -> %s", startTime, endTime, name));
            lecturesCount++;
            for (int i = 0; i < lectures.size(); i++) {
                int currentStartTime = getStartTime(lectures.get(i));
                if (currentStartTime <= endTime) {
                    lectures.remove(i);
                    i--;
                }
            }
        }

        System.out.printf("Lectures (%d):%n", lecturesCount);
        result.forEach(System.out::println);
    }

    private static int getEndTime(String lectureInfo) {
        return Integer.parseInt(lectureInfo.split(": ")[1].split(" - ")[1]);
    }

    private static int getStartTime(String lectureInfo) {
        return Integer.parseInt(lectureInfo.split(": ")[1].split(" - ")[0]);
    }


}
