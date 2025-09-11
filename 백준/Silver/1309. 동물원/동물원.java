import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int MOD = 9901;
        int [][] dp = new int[N+1][3];
        dp[1][0] = 1; // 아무것도 안놓음
        dp[1][1] = 1; // 왼쪽에 하나 놓음
        dp[1][2] = 1; // 오른쪽에 하나 놓음
        for(int i=2;i<=N;i++){
            dp[i][0] = (dp[i-1][0] + dp[i-1][1] + dp[i-1][2]) % MOD;   // 아무것도 안놓음
            dp[i][1] = (dp[i-1][0] + dp[i-1][2]) % MOD;   // 왼쪽에 하나 놓음
            dp[i][2] = (dp[i-1][0] + dp[i-1][1]) % MOD;   // 오른쪽에 하나 놓음
        }
        int ans = (dp[N][0] + dp[N][1] + dp[N][2]) % MOD; // 최종 경우의 수
        System.out.println(ans);
        sc.close();
    }
}
