
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge>{
        int to,weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    static int N,M,X;
    static int[] minDist;
    
    static ArrayList<Edge> adj[];
    static ArrayList<Edge> reverse_adj[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        adj = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            adj[i] = new ArrayList<>();
        }
        reverse_adj = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            reverse_adj[i] = new ArrayList<>();
        }

        for(int i=0; i<M; i++){
            st  = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(to, weight));
            reverse_adj[to].add(new Edge(from, weight));
        }
        int []go = dijkstra(X,adj);
        int []back = dijkstra(X, reverse_adj);
        int max=0;
        for(int i=1; i<=N; i++){
            if(i==X) continue;

            int dist = go[i]+back[i];
            max = Math.max(max, dist);
        }

        System.out.println(max);

    }

    static int[] dijkstra(int start, ArrayList<Edge>[] list){
        boolean[] visited = new boolean[N+1];
        int []minDist = new int[N+1];
        Arrays.fill(minDist, 1_000_000_000);
        minDist[start]=0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int to = cur.to;
            int weight = cur.weight;

            if(visited[to]) continue;
            visited[to] = true;

            for(Edge next : list[to]){
                int nweight = weight+next.weight;
                if(!visited[next.to] && minDist[next.to]> nweight){
                    minDist[next.to] = nweight;

                    pq.offer(new Edge(next.to, nweight));
                }
            }
            
        }

        return minDist;
    }

    
}
