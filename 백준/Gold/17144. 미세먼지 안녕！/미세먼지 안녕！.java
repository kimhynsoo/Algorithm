import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pair{
        int r,c;
        int A;
        int spread;
        Pair(int r,int c, int A){
            this.r= r;
            this.c=c;
            this.A=A;
            this.spread=A/5;
        }
    }
    static int R,C,T;
    static int[][] board;
    static Queue<Pair> q;
    static int [] air_clear = new int[2];
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C=Integer.parseInt(st.nextToken());
        T=Integer.parseInt(st.nextToken());
        board= new int[R][C];
        for(int i=0; i<R; i++){
            st =new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int idx=0;
        for(int i=0; i<R; i++){
            if(board[i][0]==-1){
                air_clear[idx++]=i;
            }
        }
        int []dr={-1,1,0,0};
        int []dc={0,0,-1,1};
        while (T-->0) {
            q= new ArrayDeque<>();
            for(int i=0; i<R; i++){
                for(int j=0; j<C; j++){
                    if(board[i][j]>0){
                        q.offer(new Pair(i, j, board[i][j]));
                    }
                }
            }

            //전파
            while (!q.isEmpty()) {
                Pair cur = q.poll();
                int r = cur.r;
                int c = cur.c;
                int remove =cur.spread;
                int cnt=0;
                for(int d=0; d<4; d++){
                    int nr =r +dr[d];
                    int nc =c +dc[d];
                    if(nr<0 || nc<0 ||nr>=R || nc>=C || board[nr][nc]==-1) continue;
                    cnt++;
                    board[nr][nc]+=remove;
                }
                board[r][c]-=cnt*remove;
            }
            rotate();
            rotate2();
            

            //공기 청정기
            
        }
        int sum=0;
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(board[i][j]>0){
                    sum+=board[i][j];
                }
            }
        }
        System.out.println(sum);
    }
    static void rotate(){ //반시계
        int r = air_clear[0];
        //왼쪽줄 아래로
        for(int i= r-1; i>0; i--){
            board[i][0]=board[i-1][0];
        }


        //윗줄 왼쪽으로
        for(int i=0; i<C-1; i++){
            board[0][i]=board[0][i+1];
        }
        //오른줄 위로
        for(int i=0; i< r; i++){
            board[i][C-1] = board[i+1][C-1];
        }

        //아랫줄 오른쪽으로
        for(int i=C-1; i>1; i--){
            board[r][i]=board[r][i-1];
        }
        board[r][1]=0;
        
    }

    static void rotate2(){//시계
        int r = air_clear[1];
        //왼쪽줄 위로
        for(int i=r+1; i<R-1; i++){
            board[i][0]=board[i+1][0];
        }

        //아랫줄 왼쪽
        for(int i=0; i<C-1; i++){
            board[R-1][i]= board[R-1][i+1];
        }
        //왼쪽줄 아래로
        for(int i=R-1; i>r; i--){
            board[i][C-1]=board[i-1][C-1];
        }

        //위줄 오른쪽
        for(int i=C-1; i>1; i--){
            board[r][i]= board[r][i-1];
        }
        board[r][1]=0;
    }
}
