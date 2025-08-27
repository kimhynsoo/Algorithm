import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;
    static int[] visited = new int[3];

    static int row,col;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        board = new int[row][col];
        for(int i=0; i<row; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<col; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        comb(0, 0);
        System.out.println(max);
    }

    static void comb(int cnt, int start){
        if(cnt == 3){
            int [][] temp = copyMap();
            for(int i=0; i<3; i++){
                int r = visited[i]/col;
                int c = visited[i]%col;
                temp[r][c]=1;
            }
            boolean [][] visited = new boolean[row][col];
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if( temp[i][j]==2 && !visited[i][j] ){
                        spread(temp,i,j,visited);
                    }
                }
                
            }
            check(temp);
            return;
        }

        int N = row*col;
        for(int i=start; i<N; i++){
            int r = i/col;
            int c = i%col;
            if(board[r][c]==0){
                visited[cnt] = i;
                comb(cnt+1, i+1);
            }
            
        }
    }
    static int[] dx ={0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static void spread(int [][] temp,int r, int c,boolean [][] visited){ //bfs
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});
        while (!q.isEmpty()) {
            int [] cur = q.poll();
            for(int i=0; i<4; i++){
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(nx<0 || ny<0 || nx>=row || ny>=col || visited[nx][ny] || temp[nx][ny] == 1) continue;
                q.offer(new int[]{nx,ny});
                visited[nx][ny] = true;
                temp[nx][ny]=2;
            }
        }
    }

    static void check(int[][] temp){ //0의 개수 체크
        int cnt=0;
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(temp[i][j]==0) cnt++;
            }
        }
        max = Math.max(max, cnt);
    }

    static int[][] copyMap(){
        int temp[][] = new int[row][col];
        for(int i=0; i<row; i++){
            temp[i] = Arrays.copyOf(board[i], col);
        }
        return temp;

    }

}
