import java.util.*;

public class GraphHelper {

    /**
     * Non-directional + unweighted graph
     * Parallel and self-loops not allowed.
     * <p>
     * Map Key   = Vertex
     * Map Value = set of Vertices that key connects to. (aka edges from A)
     */
    Map<Integer, List<Integer>> graph;

    GraphHelper(int indices, int vertices) {
        graph = new HashMap<>(indices);
    }

    /**
     * Adds an edge to the graph. In case of self-loops method will fail silently. Only 1 edge per vertex will be added.
     * @param vertexA
     * @param vertexB
     */
    public void addEdge(int vertexA, int vertexB) {

        // Add the vertices if it doesn't exist in graph
        if(!graph.containsKey(vertexA))
            graph.put(vertexA, null);

        if(!graph.containsKey(vertexB))
            graph.put(vertexB, null);

        // Self-loops will be rejected.
        if (vertexA == vertexB)
            return;

        // Get edges from A, add B to it, then put the edges back for A
        List<Integer> edges = graph.get(vertexA);
        if (edges == null)
            edges = new ArrayList<>();
        edges.add(vertexB);
        graph.put(vertexA, edges);

        // Get edges from A, add B to it, then put the edges back for A
        edges = graph.get(vertexB);
        if (edges == null)
            edges = new ArrayList<>();
        edges.add(vertexA);
        graph.put(vertexB, edges);
    }

    public int graphSize(){
        return graph.size();
    }

    public List<Integer> getEdges(int vertex){
        return graph.get(vertex);
    }

    public int getAVertex(int startingVertex, int vertexCount){
        return graph.get(startingVertex).get(vertexCount);
    }

}
