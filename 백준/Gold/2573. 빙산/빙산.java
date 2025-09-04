import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int row,col;
    static int [][] board;
    static boolean [][]visited;
    static Queue<int[]> queue = new ArrayDeque<>();
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
        int cnt=1;
        int year=0;
        while(cnt==1){
            cnt=0;
            year++;
            timeflow();
            visited= new boolean[row][col];
            for(int i=0; i<row; i++){
                for(int j=0; j<col; j++){
                    if(board[i][j]!=0 && !visited[i][j]){
                        bfs(i, j);
                        cnt++;
                    }
                }
            }
        }
        if(cnt==0){
            System.out.println(0);
        }else{
            System.out.println(year);
        }
        



    }
    static int dx[] = {-1,1,0,0};
    static int dy[] = {0,0,-1,1};

    static void timeflow(){
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(board[i][j]!=0){
                    int cnt=0;
                    for(int d=0; d<4; d++){
                        int nx = i + dx[d];
                        int ny = j + dy[d];
                        if(nx<0 || ny < 0 || nx>=row || ny>=col|| board[nx][ny]!=0) continue;
                        cnt++;
                    }
                    queue.offer(new int[]{i,j,cnt});
                }
            }
        }

        while(!queue.isEmpty()){
            int cur[] = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            if(board[r][c]>=cnt){
                board[r][c]-=cnt;
            }else{
                board[r][c]=0;
            }
        }
    }

    static void bfs(int r,int c){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});
        visited[r][c] = true;
        while (!q.isEmpty()) {
            int cur[] = q.poll();
            int x = cur[0];
            int y = cur[1];
            for(int d=0; d<4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];
                
                if(nx<0 || ny<0 || nx>=row || ny>=col || board[nx][ny]==0 || visited[nx][ny]) continue;
                visited[nx][ny]=true;
                q.offer(new int[]{nx,ny});
            }
            
        }
    }
}
