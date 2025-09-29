
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int []dp = new int[N+1];
        
        
        int []parents = new int[N+1];
        for(int i=2; i<=N; i++){
            dp[i]= dp[i-1]+1;
            parents[i]=i-1;


            if(i%3==0 && dp[i/3]+1<dp[i]){
                dp[i]=dp[i/3]+1;
                parents[i]=i/3;
            }
            if(i%2==0 && dp[i/2]+1<dp[i]){
                dp[i]=dp[i/2]+1;
                parents[i]=i/2;
            }
        }
        System.out.println(dp[N]);
        StringBuilder sb = new StringBuilder();
        int i=N;
        while (i>=1) {
            sb.append(i).append(" ");
            i=parents[i];
        }
        System.out.println(sb);
    }
}
