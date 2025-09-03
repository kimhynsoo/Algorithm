import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        //2*N의 직사각형을 1*2와 2*1의 직사각형으로 채우는 방법.
        //2*i의 직사각형 가장오른쪽에 2*1(세로)직사각형 하나를 세우면 i-1의 갈이가 남는다.
        //2*i의 직사각형 가장오른쪽에 1*2(가로)직사각형 두갸를 세우면 i-2의 길이가 남는다.
        //따라서 2*i의 직사각형을 채우기 위해선 2*(i-1)의 직사각형을 채우는 방법과 2*(i-2)의 직사각형을 채우는 방법을 합하면된다.
        //계산은 피보나치 수열과 같다/
        int dp[] = new int[1001];
        dp[1]=1;
        dp[2]=2;
        for(int i=3; i<=N; i++){
            dp[i] = (dp[i-1]+dp[i-2])%10007;
        }
        
        System.out.println(dp[N]);
    }
}
