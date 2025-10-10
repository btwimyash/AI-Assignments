import java.util.*;

public class GraphColoring {

    // Number of vertices in the graph
    private int vertices;
    private int[][] graph; // adjacency matrix
    private int[] colors;  // color assignment for vertices
    private int numColors;

    public GraphColoring(int[][] graph, int numColors) {
        this.vertices = graph.length;
        this.graph = graph;
        this.numColors = numColors;
        this.colors = new int[vertices];
    }

    // Check if it's safe to assign a color to a vertex
    private boolean isSafe(int vertex, int color) {
        for (int i = 0; i < vertices; i++) {
            if (graph[vertex][i] == 1 && colors[i] == color) {
                return false; // adjacent vertex has same color
            }
        }
        return true;
    }

    // Solve graph coloring using backtracking
    private boolean solveColoring(int vertex) {
        if (vertex == vertices) {
            return true; // all vertices colored
        }

        for (int color = 1; color <= numColors; color++) {
            if (isSafe(vertex, color)) {
                colors[vertex] = color;

                if (solveColoring(vertex + 1)) {
                    return true;
                }

                colors[vertex] = 0; // backtrack
            }
        }
        return false;
    }

    public void solve() {
        if (!solveColoring(0)) {
            System.out.println("No solution exists.");
            return;
        }

        System.out.println("Graph coloring solution:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + " -> Color " + colors[i]);
        }
    }

    public static void main(String[] args) {
        // Example: Adjacency matrix of a graph
        int[][] graph = {
                {0, 1, 1, 1},
                {1, 0, 1, 0},
                {1, 1, 0, 1},
                {1, 0, 1, 0}
        };

        int numColors = 3; // Number of colors allowed

        GraphColoring gc = new GraphColoring(graph, numColors);
        gc.solve();
    }
}
