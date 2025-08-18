import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N;
    static int [][] board;
    static int []cols;
    static int cnt;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int tc = 1; tc<=T; tc++){
            st =new StringTokenizer(br.readLine());
            N  = Integer.parseInt(st.nextToken());
            board = new int[N][N];
            cols = new int[N];
            cnt =0 ;
            nQueen(0);
            System.out.printf("#%d %d\n",tc,cnt);

        }

    }

    static void nQueen(int row){
        if(row == N){
            cnt++;
            return;
        }

        for(int i=0; i<N; i++){
            cols[row]=i;
            if(isOk(row)){
                nQueen(row+1);
            }
        }
    }

    static boolean isOk(int row){
        for(int i=0; i<row; i++){
            //같은 행
            if(cols[i]==cols[row]){
                return false;
            }
             //대각선
            if(Math.abs(i-row)==Math.abs(cols[i]-cols[row])){
                return false;
            }
           

        }
        return true;
    }
}
