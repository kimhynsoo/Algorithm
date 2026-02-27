import java.io.BufferedReader;
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [] num = new int[N];
        for(int i=0; i<N; i++){
            num[i] = Integer.parseInt(br.readLine());
        }
        int[] dp = new int[K+1];
        dp[0]=1;

        for(int i=0; i<N; i++){
            for(int j=num[i]; j<=K; j++){
                dp[j]+=dp[j-num[i]];
            }
        }

        System.out.println(dp[K]);

    }
}
