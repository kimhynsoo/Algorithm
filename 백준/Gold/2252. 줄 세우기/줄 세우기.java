import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int V,E;
    static int[] inDegree;
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        inDegree = new int[V+1];
        for(int i=0; i<=V; i++){
            adj.add(new ArrayList<Integer>());
        }
        for(int i=0; i< E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj.get(from).add(to);
            inDegree[to]++;

        }
        sort();

    }

    static void sort(){
        Queue <Integer> q = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        for(int i=1; i<=V; i++){
            if(inDegree[i]==0){
                q.offer(i);
                
            }
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur);
            for(int next : adj.get(cur)){
                if(--inDegree[next]==0){
                    q.offer(next);
                }
            }
            
        }

        for(int i=0; i<V; i++){
            System.out.print(result.get(i) +" ");
        }
    }
}
