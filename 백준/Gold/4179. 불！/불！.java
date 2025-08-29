import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int x,y,time;
        Point(int x,int y,int time){
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static char board[][];
    static boolean visited[][];
    static int R,C;
    static Queue<Point> jList = new ArrayDeque<>();
    static Queue<Point> fList = new ArrayDeque<>();
    static int[] dx ={-1,1,0,0};
    static int[] dy ={0,0,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                char  c = line.charAt(j);
                board[i][j] = c;
                if(c=='F'){
                    fList.offer(new Point(i, j, 0));
                }
                else if(c=='J'){
                    jList.offer(new Point(i, j, 0));
                    visited[i][j]=true;
                }
            }
        }
        bfs();


    }
    static void bfs(){
        while(!jList.isEmpty()){
            int f_size = fList.size();
            for(int i=0; i<f_size; i++){
                Point cur = fList.poll();
                int x = cur.x;
                int y = cur.y;
                for(int d=0; d<4; d++){
                    int nx = x +dx[d];
                    int ny = y +dy[d];
                    if(nx<0 || ny <0 || nx>=R || ny>=C) continue;
                    if(board[nx][ny] =='.' || board[nx][ny] == 'J' ){
                        board[nx][ny] = 'F';
                        fList.offer(new Point(nx, ny, 0));
                    }   
                }

            }

            int j_size = jList.size();
            for(int i=0; i<j_size; i++){
                Point cur = jList.poll();
                int x = cur.x;
                int y = cur.y;
                int ntime = cur.time+1;
                for(int d=0; d<4; d++){
                    int nx = x +dx[d];
                    int ny = y +dy[d];
                    if(nx<0 || ny <0 || nx>=R || ny>=C){
                        System.out.println(ntime);
                        return;
                    }
                    if(visited[nx][ny]) continue;
                    if(board[nx][ny] =='.'){
                        visited[nx][ny] = true;
                        jList.offer(new Point(nx, ny, ntime));
                    }   
                }

            }
        }
        System.out.println("IMPOSSIBLE");
    }

}
