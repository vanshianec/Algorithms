import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int capacity = Integer.parseInt(consoleReader.readLine());
        List<Item> items = new ArrayList<>();
        String item = consoleReader.readLine();
        while (!item.equals("end")) {
            String params[] = item.split(" ");
            items.add(new Item(params[0], Integer.parseInt(params[1]), Integer.parseInt(params[2])));
            item = consoleReader.readLine();
        }

        items.sort(Comparator.comparing(Item::getName));

        int[][] bestValues = new int[items.size() + 1][capacity + 1];
        boolean[][] itemsTaken = new boolean[items.size()][capacity];

        for (int row = 1; row < bestValues.length; row++) {

            int currentWeight = items.get(row - 1).getWeight();

            for (int col = 1; col < bestValues[0].length; col++) {
                if (currentWeight > col) {
                    continue;
                }

                int excluded = bestValues[row - 1][col];
                int included = items.get(row - 1).getValue() + bestValues[row - 1][col - currentWeight];

                if (included > excluded) {
                    bestValues[row][col] = included;
                    itemsTaken[row - 1][col - 1] = true;
                } else {
                    bestValues[row][col] = excluded;
                }
            }
        }

        int lastElementIndex = items.size() - 1;
        List<Item> itemsTakenResult = new ArrayList<>();

        while (lastElementIndex >= 0) {
            if (itemsTaken[lastElementIndex][capacity - 1]) {
                itemsTakenResult.add(items.get(lastElementIndex));
                capacity -= items.get(lastElementIndex).getWeight();
            }
            lastElementIndex--;
        }

        System.out.printf("Total Weight: %d%n", itemsTakenResult.stream().mapToInt(Item::getWeight).sum());
        System.out.printf("Total Value: %d%n", itemsTakenResult.stream().mapToInt(Item::getValue).sum());
        Collections.reverse(itemsTakenResult);
        for (Item i : itemsTakenResult) {
            System.out.println(i.getName());
        }
    }
}

