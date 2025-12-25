import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    

    static class Edge implements Comparable<Edge>{
        int to,weight;
        Edge(int to, int weight){
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight-o.weight;
        }
    }

    static ArrayList<Edge> [] adj;
    static boolean [] visited;

    public static void main(String[] args)throws IOException {
        int V,E;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adj = new ArrayList[V+1];
        visited = new boolean[V+1];
        for(int i=1; i<=V; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[from].add(new Edge(to, weight));
            adj[to].add(new Edge(from, weight));
        }
        System.out.println(prim());
    }

    static int prim(){
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));
        int mst_w=0;
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if(visited[cur.to]) continue;
            visited[cur.to]=true;

            mst_w+=cur.weight;

            for(Edge next : adj[cur.to]){
                if(!visited[next.to]){
                    pq.offer(next);
                }
            }
            
        }
        return mst_w;
    }
}
