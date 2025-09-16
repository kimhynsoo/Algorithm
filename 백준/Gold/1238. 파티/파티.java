
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

        for(int i=0; i<M; i++){
            st  = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[from].add(new Edge(to, weight));
        }
        dijkstra(X);
        int max=0;
        for(int i=1; i<=N; i++){
            if(i==X) continue;

            int dist = minDist[i]+dijkstra2(i, X);
            max = Math.max(max, dist);
        }

        System.out.println(max);

    }

    static void dijkstra(int start){
        boolean[] visited = new boolean[N+1];
        minDist = new int[N+1];
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

            for(Edge next : adj[to]){
                int nweight = weight+next.weight;
                if(!visited[next.to] && minDist[next.to]> nweight){
                    minDist[next.to] = nweight;

                    pq.offer(new Edge(next.to, nweight));
                }
            }
            
        }
    }

    static int dijkstra2(int start, int end){
        boolean []visited = new boolean[N+1];
        int []minDist2 = new int[N+1];
        Arrays.fill(minDist2, 1_000_000_000);
        minDist2[start]=0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int to = cur.to;
            int weight = cur.weight;

            if(visited[to]) continue;
            visited[to] = true;
            if(to==X){
                return minDist2[to];
            }
            for(Edge next : adj[to]){
                int nweight = weight+next.weight;
                if(!visited[next.to] && minDist2[next.to]> nweight){
                    minDist2[next.to] = nweight;

                    pq.offer(new Edge(next.to, nweight));
                }
            }
            
        }
        return -1;
    }
}
