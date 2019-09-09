import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String[] elements = in.nextLine().substring(10).split(", ");
        int[] universe = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            universe[i] = Integer.parseInt(elements[i]);
        }

        int numberOfSets = Integer.parseInt(in.nextLine().substring(16));
        List<int[]> sets = new ArrayList<>();
        for (int i = 0; i < numberOfSets; i++) {
            String[] setElements = in.nextLine().split(", ");
            int[] set = new int[setElements.length];
            for (int j = 0; j < setElements.length; j++) {
                set[i] = Integer.parseInt(setElements[i]);
            }
        }

        List<int[]> choosenSets = chooseSets(sets, universe);
    }

    public static List<int[]> chooseSets(List<int[]> sets, int[] universe) {

        List<Integer> usedNumbers = Arrays.stream(universe).boxed().collect(Collectors.toList());
        List<int[]> setsToRemove = new ArrayList<>(sets);

        List<int[]> selectedSets = new ArrayList<>();
        int universeElementsRemaining = universe.length;
        while (universeElementsRemaining > 0) {
            int[] currentSet = setsToRemove.stream()
                    .sorted((a, b) -> getElementsCount(b, usedNumbers) - getElementsCount(a, usedNumbers))
                    .findFirst()
                    .orElse(new int[0]);
            if (currentSet.length > 0) {
                selectedSets.add(currentSet);
                setsToRemove.remove(currentSet);
                for (int i = 0; i < currentSet.length; i++) {
                    if (usedNumbers.contains(currentSet[i])) {
                        usedNumbers.remove((Integer) currentSet[i]);
                        universeElementsRemaining--;
                    }
                }
            }
        }
        return selectedSets;
    }

    private static int getElementsCount(int[] set, List<Integer> usedNumbers) {
        int count = 0;
        for (int i = 0; i < set.length; i++) {
            if (usedNumbers.contains(set[i])) {
                count++;
            }
        }
        return count;
    }
}
