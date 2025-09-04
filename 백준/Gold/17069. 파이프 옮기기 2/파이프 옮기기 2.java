import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 집의 크기 (N x N)


        
        int map[][] = new int[N][N]; // 집의 상태: 0=빈 칸, 1=벽

        // dp[dir][i][j] : 파이프의 끝이 (i,j)에 있고, 방향이 dir일 때 만들 수 있는 방법의 수
        // dir = 0 (가로), 1 (세로), 2 (대각선)
        //파이프 옮기기 1과 다른 점 정수 범위. int -> long
        long dp[][][] = new long[3][N][N]; 

        // 초기 상태: 파이프가 (0,0)~(0,1) 가로 방향으로 놓여 있음
        dp[0][0][1] = 1;
    
        // 집의 상태 입력
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 채우기
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                for(int k=0; k<3; k++){ // 현재 파이프의 방향
                    if(dp[k][i][j] != 0){ // 현재 칸에 올 수 있는 경우가 존재할 때만 진행
                        
                        if(k == 0){ // 가로 방향
                            // → 오른쪽으로 이동
                            if(j+1 < N && map[i][j+1] != 1){
                                dp[0][i][j+1] += dp[k][i][j];
                            }
                            // ↘ 대각선으로 이동
                            if(i+1 < N && j+1 < N && map[i][j+1] != 1 && map[i+1][j+1] != 1 && map[i+1][j] != 1){
                                dp[2][i+1][j+1] += dp[k][i][j];
                            }
                        } 
                        else if(k == 1){ // 세로 방향
                            // ↓ 아래로 이동
                            if(i+1 < N && map[i+1][j] != 1){
                                dp[1][i+1][j] += dp[k][i][j];
                            }
                            // ↘ 대각선으로 이동
                            if(i+1 < N && j+1 < N && map[i][j+1] != 1 && map[i+1][j+1] != 1 && map[i+1][j] != 1){
                                dp[2][i+1][j+1] += dp[k][i][j];
                            }
                        } 
                        else { // 대각선 방향
                            // → 오른쪽으로 이동
                            if(j+1 < N && map[i][j+1] != 1){
                                dp[0][i][j+1] += dp[k][i][j];
                            }
                            // ↓ 아래로 이동
                            if(i+1 < N && map[i+1][j] != 1){
                                dp[1][i+1][j] += dp[k][i][j];
                            }
                            // ↘ 대각선으로 이동
                            if(i+1 < N && j+1 < N && map[i][j+1] != 1 && map[i+1][j+1] != 1 && map[i+1][j] != 1){
                                dp[2][i+1][j+1] += dp[k][i][j];
                            }
                        }
                    }
                }
            }
        }

        // 최종적으로 (N-1, N-1)에 도달하는 모든 경우의 수 = 가로/세로/대각선 합
        System.out.println(dp[0][N-1][N-1] + dp[1][N-1][N-1] + dp[2][N-1][N-1]);
    }
}
