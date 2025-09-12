import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] weight,value;
    static int[][] knapsack;

    static int N,K;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //총 무게
        K = Integer.parseInt(st.nextToken());
        
        weight = new int[K+1];
        value = new int[K+1];
        for(int i=1; i<=K; i++){
            st = new StringTokenizer(br.readLine());
            value[i] = Integer.parseInt(st.nextToken());
            weight[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(dp_2());
    }

    static int dp_1(){
        int dp[][] = new int[K+1][N+1];

        for(int i=1; i<=K; i++){
            for(int w=1; w<=N; w++){
                if(w>= weight[i]){
                    dp[i][w]=Math.max(dp[i-1][w], dp[i-1][w-weight[i]]+value[i]);
                }
                else{
                    dp[i][w] = dp[i-1][w];
                }
            }
        }

        return dp[K][N];
    }

    static int dp_2(){
        int dp[] = new int[N+1];
        for(int i=1; i<=K; i++ ){
            for(int w=N; w>=weight[i]; w--){
                dp[w]=Math.max(dp[w], dp[w-weight[i]]+value[i]);
            }
        }
        return dp[N];
    }
}
