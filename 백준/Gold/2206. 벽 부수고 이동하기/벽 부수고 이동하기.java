import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static int board[][];

    static boolean visited[][][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        visited = new boolean[R][C][2];
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){

                board[i][j] = line.charAt(j)-'0'; 
            }
        }
        visited[0][0][1]=true;
        System.out.println(bfs());
        
    }
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};
    static int bfs(){
        Queue<int []> q = new ArrayDeque<>();
        
        q.offer(new int[]{0,0,1,1});

        while (!q.isEmpty()) {
            int cur[] = q.poll();
            int x = cur[0];
            int y = cur[1];
            int k = cur[2];
            int dist = cur[3];
            if(x==R-1&& y==C-1) return dist;
            for(int d=0; d<4; d++){
                int nx = x+dx[d];
                int ny = y+dy[d];

                if(nx<0 || ny<0 || nx>=R || ny>=C || visited[nx][ny][k]) continue;
                if(board[nx][ny]==1 ){
                    if(k>0){
                        if(!visited[nx][ny][k-1])
                        {
                            visited[nx][ny][k-1]=true;
                            q.offer(new int[]{nx,ny,0,dist+1});
                        }
                    }
                }else{
                    visited[nx][ny][k]=true;
                    q.offer(new int[]{nx,ny,k,dist+1});
                }
            }
        }
        return -1;
    }
}
