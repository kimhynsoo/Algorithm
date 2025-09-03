import java.util.Scanner;

public class Main {
    //11726번과 비슷하다.
    //이번엔 2*2의 직사각형이 추가되었다.
    //따라서 2*i일 때 가장 오른쪽에 2칸을 차지하는 경우가 하나 추가 된 것이다.
    //따라서 dp[i] =dp[i-1] + 2*dp[i-2]이다. 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int dp[] = new int[1001];
        dp[1]=1;
        dp[2]=3;
        for(int i=3; i<=N; i++){
            dp[i] = (dp[i-1] + 2*dp[i-2] % 10007 )%10007;
        }

        System.out.println(dp[N]);
    }
}
