import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int N,K,W;
        int [] cost,dp;
        int [] indegree;
        ArrayList<Integer> [] graph;
        for(int tc = 0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cost = new int[N+1];
            dp = new int[N+1];
            indegree = new int[N+1];
            graph = new ArrayList[N+1];
            for(int i=1; i<=N; i++){
                graph[i]=new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for(int n=1; n<=N; n++){
                cost[n] = Integer.parseInt(st.nextToken());
                dp[n] = cost[n];
            }
            for(int k=0; k<K; k++){
                st= new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                indegree[to]++;
            }
            W = Integer.parseInt(br.readLine());
            if(indegree[W]==0){
                System.out.println(cost[W]);
                continue;
            }
            Queue<Integer> q= new ArrayDeque<>();
            for(int i=1; i<=N; i++){
                if(indegree[i]==0){
                    q.add(i);
                }
            }
            
            while (!q.isEmpty()) {
                
                

                int cur = q.poll();
                
                for(int next : graph[cur]){
                    dp[next]=Math.max(dp[next],dp[cur]+cost[next]);
                    
                    if(--indegree[next]==0){
                        q.offer(next);
                    }
                }
            }
            System.out.println(dp[W]);
            

        }
    }
}
