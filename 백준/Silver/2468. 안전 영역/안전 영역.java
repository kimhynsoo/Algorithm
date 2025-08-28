import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int N;
    static boolean[][] visited;
    static int result=1;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        
        StringTokenizer st ;
        int min=101;
        int max=0;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num < min){
                    min = num;
                }
                if(num > max){
                    max =num;
                }
                board[i][j] = num;
            }
        }
        for(int t= 0; t<max; t++){
            int cnt=0;
            visited = new boolean[N][N];
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(board[i][j]>t && !visited[i][j]){
                        bfs(i,j,t);
                        cnt++;
                    }
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);


    }
    static int []dx={-1,1,0,0};
    static int []dy={0,0,-1,1};

    static void bfs(int r,int c, int t){
        
        Queue <int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});
        visited[r][c]=true;
        while (!q.isEmpty()) {
            int [] cur = q.poll();
            for(int i=0; i<4; i++){
                int nx = cur[0]+dx[i];
                int ny = cur[1]+dy[i];
                if(nx<0 || ny<0 || nx>=N|| ny>=N || visited[nx][ny]||board[nx][ny]<=t ) continue;
                q.offer(new int[]{nx,ny});
                visited[nx][ny]=true;

            }
            
        }
    }
}
