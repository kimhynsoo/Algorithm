import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge>{
        
        int to;
        int weight;
        public Edge(int to, int weight) {
            
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            
            return this.weight-o.weight;
        }
    }

    static ArrayList<Edge>[] graph;

    static int N,E;
    static int v1,v2;
    static final int INF = 200_000_000;
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph= new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight) );
            graph[to].add(new Edge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        v1= Integer.parseInt(st.nextToken());
        v2= Integer.parseInt(st.nextToken());

        int[] distFrom1 = dijkstra(1);
        int[] distFromV1 = dijkstra(v1);
        int[] distFromV2 = dijkstra(v2);

        long path1 = distFrom1[v1]+distFromV1[v2]+distFromV2[N];
        long path2 = distFrom1[v2]+distFromV2[v1]+distFromV1[N];
        
        if(distFrom1[v1]>=INF || distFromV1[v2]>=INF || distFromV2[N]>=INF) path1 = Long.MAX_VALUE;
        if(distFrom1[v2]>=INF || distFromV2[v1]>=INF || distFromV1[N]>=INF) path2 = Long.MAX_VALUE;
        
        long ans = Math.min(path1, path2);
        System.out.println(ans == Long.MAX_VALUE?-1 :ans);
    }

    static int[] dijkstra(int start){
        int dist[] = new int[N+1];
        Arrays.fill(dist, INF);
        dist[start]=0;
        PriorityQueue<Edge> pq =new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int to = cur.to;
            int weight = cur.weight;
            if(dist[to]< weight) continue;

            for(Edge next : graph[cur.to]){
                int nd = weight+next.weight;

                if(dist[next.to]>nd){
                    dist[next.to] = nd;
                    pq.offer(new Edge(next.to, nd));
                }
            }
            
        }
        return dist;
    }
}
