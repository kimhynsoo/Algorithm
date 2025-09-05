import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K,V,E;
    static ArrayList<Integer>[] graph;
    static int[] colors;
    static int RED=1, BLUE=-1;
    static boolean isbinary;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            graph = new ArrayList[V+1];
            colors = new int[V+1];
            for(int i=1; i<=V; i++){
                graph[i] = new ArrayList<>();
            }
            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }
            isbinary = true;
            for(int i=1; i<=V; i++){
                if(!isbinary){
                    break;
                }
                if(colors[i]==0){
                    bfs(i, RED);
                }
            }
            System.out.println(isbinary?"YES":"NO");
        }
    }

    static void bfs(int start,int color){
        colors[start] =color;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for(int adj : graph[cur]){
                if(colors[adj]==0){
                    colors[adj]=colors[cur]*-1;
                    q.offer(adj);
                }else if(colors[adj]==colors[cur]){
                    isbinary=false;
                    return;
                }
            }
            
        }
    }
}
