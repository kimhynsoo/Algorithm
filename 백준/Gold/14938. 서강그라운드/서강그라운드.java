import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Pair implements Comparable<Pair>{
        int to;
        int weight;
        Pair(int to, int weight){
            this.to=to;
            this.weight=weight;
        }

        @Override
        public int compareTo(Pair o) {
            return this.weight-o.weight;
        }
    }

    static ArrayList<Pair>[] adj;
    static int[] minDist,items;
    static int N,M;
    

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        adj = new ArrayList[N+1];
        minDist = new int[N+1];
        items = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            adj[i] = new ArrayList<>();
            items[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<=R; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adj[from].add(new Pair(to, weight));
            adj[to].add(new Pair(from, weight));

        }

        int max=0;
        for(int i=1; i<=N; i++){
            minDist = new int[N+1];
            Arrays.fill(minDist, Integer.MAX_VALUE);
            minDist[i]=0;
            int d = dijkstra(i);
            if(d>max) max=d;
        }

        System.out.println(max);
        
    }

    static int dijkstra(int start){
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(start, 0));
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();

            int to = cur.to;
            int weight = cur.weight;
            if(minDist[to]<weight) continue;
            minDist[to]=weight;

            for(Pair next:adj[to]){
                if(minDist[next.to]>weight+next.weight){
                    minDist[next.to]=next.weight+weight;
                    pq.add(new Pair(next.to, next.weight+weight));
                }
            }
            
        }
        int res=0;
        for(int i=1; i<=N; i++){
            if(minDist[i]<=M){
                res+=items[i];
            }
        }
        return res;
    }
}
