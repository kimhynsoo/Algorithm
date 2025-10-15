 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Edge{
        int to,cost;
        Edge(int to,int cost){
            this.to=to;

            this.cost=cost;
        }
    }
    static ArrayList<Edge>[] graph ;
    static boolean[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int N = Integer.parseInt(br.readLine());
        graph = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i=0; i<=N ; i++){
            graph[i]= new ArrayList<>();
        }

        for(int i=1; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, cost));
            graph[to].add(new Edge(from, cost));
        }
        visited[1]=true;
        dfs(1, 0);

        Arrays.fill(visited, false);

        visited[maxEdge.to]=true;
        dfs(maxEdge.to, 0);

        System.out.println(maxEdge.cost);

    }
    static Edge maxEdge = new Edge(0, 0);

    static void dfs(int start, int dist){
        for(Edge child : graph[start]){
            if(!visited[child.to]){
                visited[child.to]=true;
                dfs(child.to, child.cost+dist);

            }
        }

        if(maxEdge.cost<dist){
            maxEdge.to=start;
            maxEdge.cost=dist;
        }
    }
}
