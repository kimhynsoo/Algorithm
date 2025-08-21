import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int V,E;
    static ArrayList<ArrayList<Integer>> adj;
    static ArrayList<Integer> result;
    static int [] inDegree;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;

        for(int tc=1; tc<=10; tc++){
            adj = new ArrayList<>();
            result = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            inDegree = new int[V+1];
            for(int i=0; i<=V; i++){
                adj.add(new ArrayList<>());
            }
            st = new StringTokenizer(br.readLine());
            for(int i =0 ; i<E; i++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                adj.get(from).add(to);
                inDegree[to]++;
            }
            System.out.printf("#%d ",tc);
            StringBuilder sb = new StringBuilder();
            for(int n : sort()){
                sb.append(n).append(" ");
            }
            System.out.println(sb);
        }
        
    }

    static ArrayList<Integer> sort(){
        Queue<Integer> q = new ArrayDeque<>();
        ArrayList<Integer> res = new ArrayList<>();
        for(int i=1; i<=V; i++){
            if(inDegree[i]==0) q.offer(i);
        }

        while(!q.isEmpty()){
            int cur = q.poll();
            res.add(cur);

            for(int next : adj.get(cur)){
                if(--inDegree[next]==0){
                    q.offer(next);
                }
            }
        }

        return res;


    }
}
