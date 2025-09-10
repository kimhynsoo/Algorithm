import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[][] graph;
    static int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

    
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new int[N+1][N+1];
        
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from][to]=1;
        }
        //플로이드 와샬
        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(graph[i][k]==1 && graph[k][j]==1){
                        //나보다 작은 키가 있다면
                        graph[i][j] = 1;
                        
                    }
                }
            }
            
        }
        int ans =0;
        for(int i=1; i<=N; i++){
            int cnt=0;
            for(int j=1; j<=N; j++){
                //나보다 큰 사람과 작은 사람을 더한다.
                cnt+=graph[i][j]+graph[j][i];
            }
            if(cnt==N-1) ans++; //정보가 나를 빼고 모두 있으면 나의 등수를 알 수 있다.
            
        }

        System.out.println(ans);
    }
}
