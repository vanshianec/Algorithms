import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(consoleReader.readLine());
        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String input = consoleReader.readLine();
            if (input.equals("")) {
                graph.add(i, new ArrayList<>());
                continue;
            }
            List<Integer> childNodes = Arrays.stream(input.split(" ")).map(Integer::parseInt).collect(Collectors.toList());
            graph.add(i, childNodes);
        }

        List<Deque<Integer>> result = getConnectedComponents(graph);
        for (Deque<Integer> component : result) {
            System.out.printf("Connected component: %s%n", component.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }


    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> result = new ArrayList<>(graph.size());
        boolean visited[] = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            Deque<Integer> currentComponentNodes = new ArrayDeque<>();
            if (!visited[i]) {
                dfs(i, graph, currentComponentNodes, visited);
                result.add(currentComponentNodes);
            }
        }
        return result;
    }


    static void dfs(int n, List<List<Integer>> graph, Deque<Integer> currentComponentNodes, boolean[] visited) {
        if (!visited[n]) {
            visited[n] = true;
            for (Integer child : graph.get(n)) {
                dfs(child, graph, currentComponentNodes, visited);
            }
            currentComponentNodes.add(n);
        }
    }

}
