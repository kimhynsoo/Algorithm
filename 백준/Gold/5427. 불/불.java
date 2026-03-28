import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int r,c,time;
        Point(int r,int c,int time){
            this.r=r;
            this.c=c;
            this.time=time;
        }
    }

    static char board[][];
    static boolean visited[][];
    static int R,C;
    static Queue<Point> fire_list;
    static Queue<Point> sang_list;
    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int tc = Integer.parseInt(br.readLine());
        for(int t=0; t<tc; t++){
            fire_list = new ArrayDeque<>();
            sang_list = new ArrayDeque<>();
            st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            board = new char[R][C];
            visited = new boolean[R][C];
            for(int i=0; i<R; i++){
                String line = br.readLine();
                for(int j=0; j<C; j++){
                    char c =line.charAt(j);
                    board[i][j]=c;
                    if(c=='*'){
                        fire_list.offer(new Point(i, j, 0));
                    }else if(c=='@'){
                        sang_list.offer(new Point(i, j, 0));
                    }
                }
            }
            bfs();

        }
    }
    static void bfs(){
        while (!sang_list.isEmpty()) {
            int f_size = fire_list.size();
            for(int i=0; i<f_size; i++){

                Point cur = fire_list.poll();
                int r = cur.r;
                int c = cur.c;
                for(int d=0; d<4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr<0 || nc<0 || nr>=R || nc>=C || board[nr][nc]=='#') continue;
                    if(board[nr][nc]=='.'||board[nr][nc]=='@'){
                        board[nr][nc]='*';
                        fire_list.offer(new Point(nr, nc, 0));
                    }
                }
            }

            int s_size = sang_list.size();
            for(int i=0; i<s_size; i++){

                Point cur = sang_list.poll();
                int r = cur.r;
                int c = cur.c;
                for(int d=0; d<4; d++){
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr<0 || nc<0 || nr>=R || nc>=C ) {
                        System.out.println(cur.time+1);
                        return;
                    }
                    if(board[nr][nc]=='.'&& !visited[nr][nc]){
                        visited[nr][nc]=true;
                        sang_list.offer(new Point(nr, nc, cur.time+1));
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}
