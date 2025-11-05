import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Integer>[] adj;
    static int[] inDegree,times;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        adj = new ArrayList[N+1];
        inDegree = new int[N+1];
        times = new int[N+1];

        for(int i=0; i<N+1; i++){
            adj[i] = new ArrayList<>();
        }

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int time =Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            times[i]= time;
            for(int j=0; j<k; j++){
                int pre = Integer.parseInt(st.nextToken());
                adj[pre].add(i);
                inDegree[i]++;
            }
        }
        sort();
        
    }

    static void sort(){
        int [] dp = new int[N+1];
        Queue<Integer> q= new ArrayDeque<>();
        for(int i=1; i<=N;i++){
            if(inDegree[i]==0){
                q.offer(i);
                dp[i]=times[i];
            }
        }
        
        while (!q.isEmpty()) {
            int cur = q.poll();
            

            for(int next : adj[cur]){
                dp[next]=Math.max(dp[next],dp[cur]+times[next]);
                if(--inDegree[next]==0){
                    q.offer(next);
                }
            }
            
        }
        int result=0;
        for(int i=1; i<=N; i++){
            result=Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
