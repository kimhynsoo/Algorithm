import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static ArrayList<Edge>[] adj;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N + 1];
        adj = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }

        System.out.println(prim(1, 0));
    }

    static int prim(int start, int weight) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int mst_weight = 0;
        int max_edge = 0;
        pq.offer(new Edge(start, weight));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (visited[cur.to])
                continue;
            visited[cur.to] = true;
            mst_weight += cur.weight;
            max_edge = Math.max(max_edge, cur.weight);

            for (Edge next : adj[cur.to]) {
                if (!visited[next.to]) {
                    pq.offer(next);
                }
            }
        }
        return mst_weight - max_edge;
    }

}
