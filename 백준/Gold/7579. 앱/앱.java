import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, W;
    static int[] weight, value;
    static int INF = 1_000_000_000;
    // static int [][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        weight= new int[N+1];
        value = new int[N+1];
        // dp = new int[N+1][W+1];


        st=new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            weight[i]= Integer.parseInt(st.nextToken());
        }

        st=new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++){
            value[i]= Integer.parseInt(st.nextToken());
        }
        // for(int i=0; i<=N; i++){
        //     Arrays.fill(dp[i], INF);
        //     dp[i][0]=0;
        // }
        // for(int i=1; i<=N; i++){
        //     for(int w= weight[i]; w<=W; w++){
        //         dp[i][w] = dp[i-1][w];

        //         if(dp[i-1][w-weight[i]]!=INF){
        //             dp[i][w] = Math.min(dp[i][w], dp[i-1][w-weight[i]]+value[i]);
        //         }
        //     }
        // }
        // System.out.println(dp[N][W]);
        int sumWeight = 0;
        for(int i=1; i<=N; i++) sumWeight += weight[i];

        int[] dp = new int[sumWeight+1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for(int i=1; i<=N; i++){
            for(int w=sumWeight; w>=weight[i]; w--){
                dp[w] = Math.min(dp[w-weight[i]] + value[i], dp[w]);
            }
        }

        int ans = INF;
        for(int w=W; w<=sumWeight; w++){
            ans = Math.min(ans, dp[w]);
        }

        System.out.println(ans);
    }
}
