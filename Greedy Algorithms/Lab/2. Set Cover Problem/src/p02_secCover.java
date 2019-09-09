import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class p02_secCover {

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
