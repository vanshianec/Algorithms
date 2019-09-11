import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int numberOfTasks = Integer.parseInt(consoleReader.readLine().split(": ")[1]);
        List<String> tasks = new ArrayList<>();
        for (int i = 0; i < numberOfTasks; i++) {
            tasks.add(consoleReader.readLine());
        }

        int maxDeadline = Integer.parseInt(tasks.stream().min((a, b) -> getDeadline(b) - getDeadline(a)).get().split(" - ")[1]);
        List<String> sortedTasks = tasks.stream().sorted((a, b) -> getValue(b) - getValue(a)).collect(Collectors.toList());

        int totalValue = 0;
        LinkedHashMap<Integer, String> result = new LinkedHashMap<>();
        for (int i = 0; i < numberOfTasks; i++) {
            String task = sortedTasks.get(i);
            int value = Integer.parseInt(task.split(" - ")[0]);
            result.put(tasks.indexOf(task) + 1, task);
            totalValue += value;
            if (maxDeadline == i + 1) {
                break;
            }
        }

        List<String> resultIndices = new ArrayList<>();
        result.entrySet().stream()
                .sorted((a, b) -> compare(a.getValue(), b.getValue()))
                .forEach((kv) -> resultIndices.add(String.valueOf(kv.getKey())));

        System.out.printf("Optimal schedule: %s%n", String.join(" -> ", resultIndices));
        System.out.printf("Total value: %d%n", totalValue);
    }

    private static int getDeadline(String task) {
        return Integer.parseInt(task.split(" - ")[1]);
    }

    private static int getValue(String task) {
        return Integer.parseInt(task.split(" - ")[0]);
    }

    private static int compare(String a, String b) {
        int cmp = Integer.compare(getDeadline(a), getDeadline(b));
        if (cmp == 0) {
            cmp = Integer.compare(getValue(b), getValue(a));
        }
        return cmp;
    }
}
