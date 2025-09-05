
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static class Edge{
        int u,v;
        long w;
        Edge(int u,int v,long w){
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }

    static int V,E;
    static ArrayList<Edge> edges;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V= Integer.parseInt(st.nextToken());
        E= Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long w = Long.parseLong(st.nextToken());
            edges.add(new Edge(u, v, w));
        }
        int start = 1;
        long [] dist = new long[V+1];
        Arrays.fill(dist, INF);
        dist[start]=0;

        //V-1번 반복
        for(int i=1; i<V; i++){
            for(Edge e : edges){
                if(dist[e.u]!=INF && dist[e.v]>dist[e.u]+e.w){
                    dist[e.v] = dist[e.u] +e.w;
                }
            }
        }

        //음수 사이클 체크
        boolean hasCycle = false;
        for(Edge e : edges){
            if(dist[e.u]!=INF && dist[e.v]>dist[e.u]+e.w){
                hasCycle =true;
                break;
            }
        }

        if(hasCycle){
            System.out.println(-1);
        }else{
            for(int i=2; i<=V; i++){
                System.out.println((dist[i]==INF)?-1:dist[i]);
            }
        }


    }
}