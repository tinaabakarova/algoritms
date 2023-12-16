package graphs_Korasaju;

import java.util.*;
import java.util.LinkedList;

class Graph {
    private int verticesCount;
    private LinkedList<Integer>[] adjacentVertices;

    Graph(int count) {
        verticesCount = count;
        adjacentVertices = new LinkedList[count];
        for (int i = 0; i < count; ++i)
            adjacentVertices[i] = new LinkedList();
    }

    void addEdge(int s, int d) {
        adjacentVertices[s].add(d);
    }

    // DFS
    void dfsSearch(int s, boolean[] visitedVertices, int k, int[] connected) {
        visitedVertices[s] = true;
        connected[s] = k;
        int n;

        Iterator<Integer> i = adjacentVertices[s].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visitedVertices[n])
                dfsSearch(n, visitedVertices, k, connected);
        }
    }

    Graph transpose() {
        Graph g = new Graph(verticesCount);
        for (int s = 0; s < verticesCount; s++) {
            Iterator<Integer> i = adjacentVertices[s].listIterator();
            while (i.hasNext())
                g.adjacentVertices[i.next()].add(s);
        }
        return g;
    }

    void fillOrder(int s, boolean[] visitedVertices, Stack stack) {
        visitedVertices[s] = true;

        Iterator<Integer> i = adjacentVertices[s].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visitedVertices[n])
                fillOrder(n, visitedVertices, stack);
        }
        stack.push(s);
    }

    int[] getSCC() {
        Stack stack = new Stack();
        int[] connected = new int[verticesCount];
        int k = 1;

        boolean[] visitedVertices = new boolean[verticesCount];
        for (int i = 0; i < verticesCount; i++)
            visitedVertices[i] = false;

        for (int i = 0; i < verticesCount; i++)
            if (!visitedVertices[i])
                fillOrder(i, visitedVertices, stack);

        Graph gr = transpose();

        for (int i = 0; i < verticesCount; i++)
            visitedVertices[i] = false;

        while (!stack.isEmpty()) {
            int s = stack.pop();

            if (!visitedVertices[s]) {
                gr.dfsSearch(s, visitedVertices, k, connected);
                k++;
            }
        }

        return connected;
    }

    public static void main(String[] args) {
        Graph g = new Graph(8);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(3, 0);
        g.addEdge(4, 5);
        g.addEdge(5, 6);
        g.addEdge(6, 4);
        g.addEdge(6, 7);

        System.out.println("Strongly Connected Components:");
        int[] connected = g.getSCC();
        for (int i = 0; i < connected.length; i++) {
            System.out.println(i + " : " + connected[i]);
        }
    }
}
