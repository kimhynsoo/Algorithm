import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int [][]board;
    static int [][]me;
    static int [] dx = {0,0,-1,1};
    static int [] dy = {1,-1,0,0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        me = new int[N][M];
        for(int i=0; i<N; i++){
            String []line = br.readLine().split("");
            for(int j=0; j<M; j++){
                board[i][j]=Integer.parseInt(line[j]);
            }
        }
        bfs();
        System.out.println(me[N-1][M-1]);

    }

    static void bfs(){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0,0});
        board[0][0]=0;
        me[0][0]=1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for(int i=0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>N-1 || ny > M-1) continue;
                if(board[nx][ny]==1){
                    
                    board[nx][ny]=0;
                    me[nx][ny]=me[cur[0]][cur[1]]+1;
                    queue.offer(new int[]{nx,ny});
                    if(nx == N-1 && ny==M-1) return;
                }
            }
            
        }

    }
}
