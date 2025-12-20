import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int from, to, weight;
        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            List<Edge> edges = new ArrayList<>();

            // 도로 (양방향)
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(u, v, w));
                edges.add(new Edge(v, u, w));
            }

            // 웜홀 (단방향, 음수)
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges.add(new Edge(u, v, -w));
            }

            int[] dist = new int[N + 1];
            Arrays.fill(dist, 0); // 모든 정점을 시작점처럼

            boolean hasNegativeCycle = false;

            for (int i = 1; i <= N; i++) {
                for (Edge e : edges) {
                    if (dist[e.to] > dist[e.from] + e.weight) {
                        dist[e.to] = dist[e.from] + e.weight;
                        if (i == N) {
                            hasNegativeCycle = true;
                        }
                    }
                }
            }

            System.out.println(hasNegativeCycle ? "YES" : "NO");
        }
    }
}
