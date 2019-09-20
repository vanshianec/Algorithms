import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {
        int n = graph.length;
        int[] distance = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[sourceNode] = 0;

        boolean[] used = new boolean[n];
        int[] previous = new int[n];
        for (int i = 0; i < n; i++) {
            previous[i] = -1;
        }

        while (true) {
            int minDistance = Integer.MAX_VALUE;
            int minNode = 0;
            for (int node = 0; node < n; node++) {
                if (!used[node] && distance[node] < minDistance) {
                    minDistance = distance[node];
                    minNode = node;
                }
            }

            if (minDistance == Integer.MAX_VALUE) {
                break;
            }

            used[minNode] = true;


            for (int i = 0; i < n; i++) {
                //iNode is connected to minNode
                if (graph[minNode][i] > 0) {
                    int newDistance = graph[minNode][i] + minDistance;
                    if (newDistance < distance[i]) {
                        distance[i] = newDistance;
                        previous[i] = minNode;
                    }
                }
            }
        }

        if (distance[destinationNode] == Integer.MAX_VALUE) {
            //no path found
            return null;
        }

        List<Integer> path = new ArrayList<>();
        int currentNode = destinationNode;
        while (currentNode != -1) {
            path.add(currentNode);
            currentNode = previous[currentNode];
        }

        Collections.reverse(path);
        return path;
    }
}
