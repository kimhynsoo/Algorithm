import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][N];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long [][][]dp = new long[3][N][N];
        dp[0][0][1]=1;

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int k=0; k<3; k++){
                    if(dp[k][i][j]!=0){
                        if(k==0){//가로
                            if(j+1<N &&board[i][j+1]!=1){
                                dp[0][i][j+1]+=dp[k][i][j];
                                if(i+1<N && board[i+1][j+1]!=1&& board[i+1][j]!=1){
                                    dp[2][i+1][j+1]+=dp[k][i][j];
                                }
                            }
                        }else if(k==1){//세로
                            if(i+1<N &&board[i+1][j]!=1){
                                dp[1][i+1][j]+=dp[k][i][j];
                                if(j+1<N && board[i+1][j+1]!=1 && board[i][j+1]!=1){
                                    dp[2][i+1][j+1]+=dp[k][i][j];
                                }
                            }
                        }else if(k==2){//대각선
                            if(j+1<N &&board[i][j+1]!=1){
                                dp[0][i][j+1]+=dp[k][i][j];
                                if(i+1<N && board[i+1][j+1]!=1 && board[i+1][j]!=1){
                                    dp[2][i+1][j+1]+=dp[k][i][j];
                                }
                            }
                            if(i+1<N && board[i+1][j]!=1){
                                dp[1][i+1][j]+=dp[k][i][j];
                            }
                        }
                    }
                }
            }
        }
        System.out.println(dp[0][N-1][N-1]+dp[1][N-1][N-1]+dp[2][N-1][N-1]);
    }
}
