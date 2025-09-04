import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int map[][] = new int[N+1][3];
        int dp[][] = new int[N+1][3];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=1; i<=N; i++){
            for(int j=0; j<3; j++){
                if(j==0){
                    dp[i][j]=Math.min(dp[i-1][1], dp[i-1][2])+map[i][j];
                }else if(j==1){
                    dp[i][j]=Math.min(dp[i-1][0], dp[i-1][2])+map[i][j];
                }else{
                    dp[i][j]=Math.min(dp[i-1][0], dp[i-1][1])+map[i][j];
                }
            }

                
        }

        int min = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            if(min> dp[N][i]){
                min = dp[N][i]; 
            }
        }

        System.out.println(min);
    }
}
