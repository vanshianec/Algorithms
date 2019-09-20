import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {
        Collections.sort(edges);
        //set each node to be parent of itself at first
        int[] parent = new int[numberOfVertices];
        for (int i = 0; i < numberOfVertices; i++) {
            parent[i] = i;
        }

        List<Edge> spanningTree = new ArrayList<>();
        for (Edge edge : edges) {
            int rootStartNode = findRoot(edge.getStartNode(), parent);
            int rootEndNode = findRoot(edge.getEndNode(), parent);
            //no cycles found
            if (rootStartNode != rootEndNode) {
                spanningTree.add(edge);
                //mark one root as parent of the other (merge trees)
                //doesn't matter which of the two is chosen to be root
                parent[rootStartNode] = rootEndNode;
            }
        }

        return spanningTree;
    }

    public static int findRoot(int node, int[] parent) {
        int root = node;
        while (parent[root] != root) {
            root = parent[root];
        }
        return root;
    }
}
