import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N,M,K;
    static int[][] maps;
    static int[][] visited;
    static double max_sum=-10e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        maps = new int[N][M];
        visited = new int[N][M];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                maps[i][j] =Integer.parseInt(st.nextToken());
            }
        }
        find(0, 0, 0, 0);
        System.out.println((int)max_sum);


    }
    static void find(int cnt,int sum,int row, int col){
        if(cnt==K){
            if(sum>max_sum) max_sum=sum;
            return;
        }

        for(int i=row; i<N; i++){
            for(int j=(i==row?col:0); j<M; j++){
                if(visited[i][j]!=0) continue;
                put(i, j);
                find(cnt+1, sum+maps[i][j], i, j);
                remove(i, j);
            }
        }
    }


    static void put(int row, int col){
        visited[row][col]+=1;
        if(row>0){
            visited[row-1][col]+=1;
        }
        if(col>0){
            visited[row][col-1]+=1;
        }
        if(row<N-1){
            visited[row+1][col]+=1;
        }
        if(col<M-1){
            visited[row][col+1]+=1;
        }
    }
    static void remove(int row, int col){
        visited[row][col]-=1;
        
        if(row>0){
            visited[row-1][col]-=1;
        }
        if(col>0){
            visited[row][col-1]-=1;
        }
        if(row<N-1){
            visited[row+1][col]-=1;
        }
        if(col<M-1){
            visited[row][col+1]-=1;
        }
    }
}
