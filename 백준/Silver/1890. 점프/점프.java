import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    

    static int N;
    static int [][]map;
    static long [][]visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new long[N][N];
        visited[0][0]=1;
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(i==0&&j==0) continue;
                long cnt1=0;
                for(int R=i-1; R>=0; R--){
                    if(R<0) break;
                    if(map[R][j]==i-R) cnt1+=visited[R][j];
                }
                long cnt2=0;
                for(int C=j-1; C>=0; C--){
                    if(C<0) break;
                    if(map[i][C]==j-C) cnt2+=visited[i][C];
                }
                visited[i][j]=cnt1+cnt2;
            }
        }
        System.out.println(visited[N-1][N-1]);


    }

}
