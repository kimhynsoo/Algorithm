import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int [] weights = new int[N];
        int [] values = new int[N];
        int max=0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            weights[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
            if(weights[i]>max) max = weights[i];
        }
        max = max*1000;
        int [] dp = new int[max+1]; //N원일 때 최대 사람
        for(int i=0; i<N; i++){
            for(int w=weights[i]; w<=max; w++){
                dp[w]=Math.max(dp[w],dp[w-weights[i]]+values[i] );
            }
        }

        for(int i=0; i<=max; i++){
            if (dp[i]>=C){
                System.out.println(i);
                break;
            }
        }

    }
}
