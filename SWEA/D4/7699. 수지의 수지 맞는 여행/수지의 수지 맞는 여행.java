import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int R,C;
    static int[][] board;
    static int max_cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++){
            StringTokenizer st =new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            board =new int[R][C];
            //보드입력처리 0~25 (A~Z)
            for(int i=0; i<R; i++){
                String line = br.readLine();
                for(int j=0; j<C; j++){
                    board[i][j] = line.charAt(j) - 'A';
                }
            }
            max_cnt=0;
            //시작점 (0,0) 방문처리.
            dfs(0, 0, 1, 1<<board[0][0]);
            System.out.printf("#%d %d\n",tc,max_cnt);
        }
        


    }
    static int dx[] = {0,0,-1,1};
    static int dy[] = {1,-1,0,0};
    static void dfs(int r, int c, int cnt, int bitmask){
        if(max_cnt==26) return; //최대가 알파벳의 총 개수 일때 return
        if(cnt > max_cnt){
            max_cnt=cnt;
            
        }
        for(int i=0; i<4; i++){
            int nx = r + dx[i];
            int ny = c + dy[i];
            if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
            int nextChar = board[nx][ny];
            //아직 방문하지 않은 알파벳이면 이동 가능
            if((bitmask & (1<<nextChar))==0){
                dfs(nx, ny, cnt+1, bitmask|(1<<nextChar));
            }
        }
    }
}
