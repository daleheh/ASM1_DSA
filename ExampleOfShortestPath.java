import java.util.*;
public class ExampleOfShortestPath {

    class Graph {
        private int vertices;
        private LinkedList<Edge>[] adj;

        public Graph(int vertices) {
            this.vertices = vertices;
            adj = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int src, int dest, int weight) {
            adj[src].add(new Edge(dest, weight));
            adj[dest].add(new Edge(src, weight)); // Nếu là đồ thị vô hướng
        }

        public void dijkstra(int start) {
            int[] dist = new int[vertices];
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[start] = 0;

            PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
            pq.add(new Edge(start, 0));

            while (!pq.isEmpty()) {
                Edge edge = pq.poll();
                int u = edge.node;

                for (Edge e : adj[u]) {
                    int v = e.node;
                    int weight = e.weight;

                    if (dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                        pq.add(new Edge(v, dist[v]));
                    }
                }
            }

            System.out.println("Shortest distances from source:");
            for (int i = 0; i < vertices; i++) {
                System.out.println(i + " -> " + dist[i]);
            }
        }

        static class Edge {
            int node, weight;

            Edge(int node, int weight) {
                this.node = node;
                this.weight = weight;
            }
        }
    }

    // Sử dụng Dijkstra
    public class DijkstraDemo {
        public void main(String[] args) {
            Graph graph = new Graph(6);
            graph.addEdge(0, 1, 4);
            graph.addEdge(0, 2, 4);
            graph.addEdge(1, 2, 2);
            graph.addEdge(1, 3, 5);
            graph.addEdge(2, 3, 8);
            graph.addEdge(3, 4, 6);

            graph.dijkstra(0);
        }
    }

    class Prim {
        // Đỉnh và cạnh của đồ thị
        static class Edge implements Comparable<Edge> {
            int source, dest, weight;

            Edge(int source, int dest, int weight) {
                this.source = source;
                this.dest = dest;
                this.weight = weight;
            }

            @Override
            public int compareTo(Edge other) {
                return Integer.compare(this.weight, other.weight);
            }
        }

        public static void primAlgorithm(int vertices, List<Edge>[] graph) {
            // Mảng để lưu các đỉnh đã được duyệt
            boolean[] visited = new boolean[vertices];
            // Priority Queue cho các cạnh, sắp xếp theo trọng số
            PriorityQueue<Edge> pq = new PriorityQueue<>();

            // Chọn đỉnh đầu tiên (có thể là đỉnh 0)
            visit(0, graph, visited, pq);

            List<Edge> mst = new ArrayList<>();
            int totalWeight = 0;

            // Lặp qua các cạnh trong Priority Queue
            while (!pq.isEmpty() && mst.size() < vertices - 1) {
                Edge edge = pq.poll(); // Lấy cạnh có trọng số nhỏ nhất

                if (visited[edge.dest]) continue; // Bỏ qua cạnh nếu đỉnh đã được duyệt

                // Thêm cạnh vào MST
                mst.add(edge);
                totalWeight += edge.weight;

                // Tiếp tục thăm đỉnh đích
                visit(edge.dest, graph, visited, pq);
            }

            // Kết quả MST
            System.out.println("Minimum Spanning Tree:");
            for (Edge edge : mst) {
                System.out.println(edge.source + " - " + edge.dest + " : " + edge.weight);
            }
            System.out.println("Total Weight: " + totalWeight);
        }

        private static void visit(int vertex, List<Edge>[] graph, boolean[] visited, PriorityQueue<Edge> pq) {
            visited[vertex] = true;
            for (Edge edge : graph[vertex]) {
                if (!visited[edge.dest]) {
                    pq.add(edge);
                }
            }
        }

        public static void main(String[] args) {
            int vertices = 6;

            // Đồ thị được biểu diễn dưới dạng danh sách kề
            List<Edge>[] graph = new List[vertices];
            for (int i = 0; i < vertices; i++) {
                graph[i] = new ArrayList<>();
            }

            // Thêm các cạnh
            addEdge(graph, 0, 1, 4);
            addEdge(graph, 0, 2, 4);
            addEdge(graph, 1, 2, 2);
            addEdge(graph, 1, 3, 6);
            addEdge(graph, 2, 3, 8);
            addEdge(graph, 3, 4, 9);
            addEdge(graph, 4, 5, 10);

            // Gọi thuật toán Prim
            primAlgorithm(vertices, graph);
        }

        // Hàm tiện ích để thêm cạnh
        private static void addEdge(List<Edge>[] graph, int source, int dest, int weight) {
            graph[source].add(new Edge(source, dest, weight));
            graph[dest].add(new Edge(dest, source, weight)); // Đồ thị vô hướng
        }

    }
}
