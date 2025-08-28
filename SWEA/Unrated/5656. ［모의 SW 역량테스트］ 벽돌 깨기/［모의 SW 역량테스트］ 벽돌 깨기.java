import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N,ROW,COL;
    static int[][]  board;
    static int[] visited;
    static int result;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st ;
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            COL = Integer.parseInt(st.nextToken());
            ROW = Integer.parseInt(st.nextToken());

            board = new int[ROW][COL];

            for(int i=0; i<ROW; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<COL; j++){
                    board[i][j] =Integer.parseInt(st.nextToken());
                }
            }
            result = Integer.MAX_VALUE;
            visited = new int[N];
            comb(0);

            System.out.printf("#%d %d\n",tc,result);
        }

    }

    static void comb(int cnt){
        if(cnt == N){
            int [][] temp = copy();
            for(int n : visited){
                attack(n,temp);
                clean(temp);
            }
            count(temp);
            return;
        }

        for(int i=0; i<COL; i++){
            visited[cnt] = i;
            comb(cnt+1);
        }
    }

    static int[][] copy(){
        int[][]temp = new int[ROW][COL];
        for(int i=0; i<ROW; i++){
            temp[i] = Arrays.copyOfRange(board[i], 0, COL);
        }
        return temp;
    }
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static void attack(int col, int[][] temp){
        // col 열에서 가장 위 벽돌 찾기
        int row = -1;
        for(int i=0; i<ROW; i++){
            if(temp[i][col] != 0){
                row = i;
                break;
            }
        }
        if(row == -1) return; // 해당 열에 벽돌 없음

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col, temp[row][col]});
        temp[row][col] = 0; // 시작 벽돌 제거

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], power = cur[2];

            // 파워가 1이면 자기 자신만 터지고 끝
            if(power <= 1) continue;

            // 4방향으로 power-1 칸씩 전파
            for(int d=0; d<4; d++){
                int nx = x;
                int ny = y;
                for(int k=1; k<power; k++){
                    nx += dx[d];
                    ny += dy[d];
                    if(nx<0 || ny<0 || nx>=ROW || ny>=COL) break;
                    if(temp[nx][ny] > 0){
                        q.offer(new int[]{nx, ny, temp[nx][ny]});
                        temp[nx][ny] = 0; // 큐에 넣으면서 바로 제거 처리
                    }
                }
            }
        }
    }
    static void clean(int [][] temp){
    for(int c=0; c<COL; c++){
        int[] stack = new int[ROW];
        int idx = ROW-1;
        for(int r=ROW-1; r>=0; r--){
            if(temp[r][c] != 0){
                stack[idx--] = temp[r][c];
            }
        }
        for(int r=0; r<ROW; r++){
            temp[r][c] = stack[r];
        }
    }
}


    static void count(int [][]temp){
        int cnt=0;
        for(int i=0; i<ROW; i++){
            for(int j=0; j<COL; j++){
                if(temp[i][j]!=0){
                    cnt++;
                }
            }
        }
        result = Math.min(cnt, result);
    }



}
