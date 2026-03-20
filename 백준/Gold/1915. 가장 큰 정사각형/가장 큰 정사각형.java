import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    
    static int N,M;

    static int arr[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        int max=0;
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                arr[i][j]=line.charAt(j)-'0';

                if(arr[i][j]==1 && i>0 && j>0){
                    arr[i][j]=Math.min(Math.min(arr[i-1][j],arr[i][j-1]), arr[i-1][j-1])+1;

                }
                max = Math.max(max, arr[i][j]);
            }
        }
        System.out.println(max*max);

    }
    
}
