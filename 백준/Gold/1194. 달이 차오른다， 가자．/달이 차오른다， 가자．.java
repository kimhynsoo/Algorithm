import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pair{
        int r,c,dist,key;

        public Pair(int r, int c, int dist, int key) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.key = key;
        }
        
    }
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int R,C;
    static char[][] board;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board=new char[R][C];
        Pair start=null;
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                char c=line.charAt(j);
                board[i][j] = c;
                if(c=='0'){
                    start = new Pair(i, j, 0, 0);
                }
            }
        }
        System.out.println(bfs(start));
    }


    static int bfs(Pair start){
        int maxKeys = 6; 
        boolean [][][] visited = new boolean[R][C][1<<maxKeys]; 
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.r][start.c][0] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int key = cur.key;
            int dist = cur.dist;
            
            for(int d=0; d<4; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(nr<0 || nc<0 || nr>=R || nc>=C) continue;
                if(board[nr][nc]=='#') continue;

                // 목적지
                if(board[nr][nc]=='1') return dist+1;

                int nkey = key;

                // 열쇠
                if(board[nr][nc] >= 'a' && board[nr][nc] <= 'f'){
                    nkey = key | (1 << (board[nr][nc]-'a'));
                }

                // 문
                if(board[nr][nc] >= 'A' && board[nr][nc] <= 'F'){
                    if((key & (1 << (board[nr][nc]-'A'))) == 0) continue; 
                }

                if(!visited[nr][nc][nkey]){
                    visited[nr][nc][nkey] = true;
                    q.offer(new Pair(nr, nc, dist+1, nkey));
                }
            }
        }
        return -1;
    }

}
