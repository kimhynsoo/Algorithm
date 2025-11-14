import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int INF =   1_000_000_000 ; 
    public static void main(String[] args)throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][]arr= new int[N+1][N+1];
        for(int i=1;i<=N;i++){
            Arrays.fill(arr[i], INF);
            arr[i][i]=0;
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a][b]=c;
        }

        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                if(i==k)continue;
                for(int j=1; j<=N; j++){
                    if(i==j) continue;
                    arr[i][j]=Math.min(arr[i][j], arr[i][k]+arr[k][j]);
                }
            }
        }
        int min =INF;
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i==j) continue;
                if(arr[i][j]!=INF && arr[j][i]!=INF){
                    min = Math.min(arr[i][j]+arr[j][i], min);
                }
            }
        }
        if(min==INF){
            System.out.println(-1);
        }
        else{
            System.out.println(min);
        }

    }
}
