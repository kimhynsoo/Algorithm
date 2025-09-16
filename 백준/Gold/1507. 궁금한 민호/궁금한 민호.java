import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_000_000_000; // 불필요한 간선을 제거할 때 사용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine()); // 도시 수

        int[][] board = new int[N][N]; // 입력으로 주어진 최단 거리 행렬
        int[][] arr = new int[N][N];   // 원래 그래프(간선) 후보

        // 입력 처리
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                arr[i][j] = board[i][j]; // 초기에는 그대로 복사
            }
        }

        boolean flag = true; // 모순 여부 체크용

        // 플로이드-워셜 아이디어 활용
        // board[i][j]가 board[i][k] + board[k][j]보다 크면 모순 (불가능한 데이터)
        // board[i][j] == board[i][k] + board[k][j]이면 (i-j 직통 간선 불필요)
        floyd: for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(i==k || j==k || i==j) continue; // 자기 자신이나 중복 케이스 건너뜀

                    // 모순 발견: 직접 거리보다 경유한 거리가 더 짧으면 불가능
                    if(board[i][j] > board[i][k] + board[k][j]){
                        flag = false;
                        break floyd;
                    }

                    // 불필요한 간선 제거: 직접 거리 == 다른 경유 경로
                    if(board[i][j] == board[i][k] + board[k][j]){
                        arr[i][j] = INF; // i→j 직통 간선이 없어도 됨
                    }
                }
            }
        }

        int ans = 0;
        boolean[][] check  = new boolean[N][N]; // 중복 합 방지 (무방향 그래프라서)

        if(flag){ // 모순이 없을 경우
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    // 남아 있는 유효한 간선만 더함
                    if(arr[i][j] != INF && i != j && !check[i][j]){
                        ans += arr[i][j];
                        check[i][j] = check[j][i] = true; // 대칭 간선은 한 번만 합산
                    }
                }
            }
            System.out.println(ans); // 원래 그래프의 총 간선 길이 출력
        }else{
            System.out.println(-1); // 모순이 있으면 불가능
        }
    }
}
