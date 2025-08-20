
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int row,col;
    static int[][] board;
    static int cnt=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        
        while(row!=0 && col!=0){
            
            cnt =0;
            board= new int[row][col];
            for(int i=0; i<row; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<col; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if(board[i][j]==1){
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
            System.out.println(cnt);

            st = new StringTokenizer(br.readLine());
            col = Integer.parseInt(st.nextToken());
            row = Integer.parseInt(st.nextToken());

        }
        
        
    }
    static int [] dx ={0,0,1,-1,-1,-1,1,1};
    static int [] dy ={1,-1,0,0,-1,1,-1,1};
    static void bfs(int r,int c){
        board[r][c]=0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});

        while (!q.isEmpty()) {
            int [] cur = q.poll();

            for(int i=0; i<8; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx<0 || ny<0 || nx>row-1 || ny >col-1) continue;
                if(board[nx][ny] ==1){
                    board[nx][ny]=0;
                    q.offer(new int[] {nx,ny});
                }
            }
            
        }

    }

}
