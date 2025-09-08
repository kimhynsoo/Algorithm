import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, W;
    static int[] weight, value;



    //M 바이트 이상의 메모리를 추가로 확보해야 한다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        value = new int[N];
        weight= new int[N];



        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            value[i]= Integer.parseInt(st.nextToken());
        }
        int K=0; //만들 수 있는 최대 비용
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            weight[i]= Integer.parseInt(st.nextToken());
            K+=weight[i];   
        }

        int[] dp = new int[K+1];
        
        for(int i=0; i<N; i++){
            for(int w=K; w-weight[i]>=0; w--){//중복 방지를 위해서 마지막부터 역순으로 탐색한다.
                dp[w] = Math.max(dp[w-weight[i]] + value[i], dp[w]);    //w비용으로 만들 수 있는 최대 바이트
            }
        }

        for(int i=0; i<=K; i++){
            if(dp[i]>=W){ //i 비용으로 만들 수 있는 바이트가 W를 넘으면 출력 후 break
                System.out.println(i); 
                break;
            }
        }

        
    }
}
