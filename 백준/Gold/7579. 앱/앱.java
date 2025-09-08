import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, W;
    static int[] weight, value;
    static int INF = 1_000_000_000;



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        value = new int[N+1];
        weight= new int[N+1];



        st=new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            value[i]= Integer.parseInt(st.nextToken());
        }
        int K=0;
        st=new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            weight[i]= Integer.parseInt(st.nextToken());
            K+=weight[i];
        }

        int[] dp = new int[K+1];
        
        for(int i=1; i<=N; i++){
            for(int w=K; w-weight[i]>=0; w--){
                dp[w] = Math.max(dp[w-weight[i]] + value[i], dp[w]);
            }
        }

        for(int i=0; i<=K; i++){
            if(dp[i]>=W){
                System.out.println(i);
                break;
            }
        }

        
    }
}
