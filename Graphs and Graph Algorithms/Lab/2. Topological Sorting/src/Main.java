import java.util.*;

@SuppressWarnings("unchecked")
public class Main {

    public static void main(String[] args) {
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("D", "E"));
        graph.put("C", Arrays.asList("F"));
        graph.put("D", Arrays.asList("C", "F"));
        graph.put("E", Arrays.asList("D"));
        graph.put("F", new ArrayList<>());

        Collection<String> result = topSort(graph);
        for (String s : result) {
            System.out.println(s);
        }

    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {

        Map<String, Integer> predecessorCount = getPredecessorCount(graph);

        List<String> sorted = new ArrayList<>();

        while (true) {
            String nodeToRemove = predecessorCount.keySet().stream().filter(k -> predecessorCount.get(k) == 0).findFirst().orElse("");

            if (nodeToRemove.equals("")) {
                break;
            }

            for (String childNode : graph.get(nodeToRemove)) {
                predecessorCount.put(childNode, predecessorCount.get(childNode) - 1);
            }

            graph.remove(nodeToRemove);
            predecessorCount.remove(nodeToRemove);
            sorted.add(nodeToRemove);
        }

        //cycle detected
        if (graph.size() > 0) {
            throw new IllegalArgumentException();
        }

        return sorted;
    }

    private static Map<String, Integer> getPredecessorCount(Map<String, List<String>> graph) {
        Map<String, Integer> predecessorCount = new HashMap<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            predecessorCount.putIfAbsent(node.getKey(), 0);
            for (String child : node.getValue()) {
                predecessorCount.putIfAbsent(child, 0);
                predecessorCount.put(child, predecessorCount.get(child) + 1);
            }
        }

        return predecessorCount;
    }
}
