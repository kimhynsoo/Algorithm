import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // N: 세로선 개수
    // M: 이미 놓여진 가로선 개수
    // H: 세로선마다 가로선을 놓을 수 있는 위치(행) 개수
    static int N, M, H;

    // board[r][c] = 1: (r,c)에서 오른쪽으로 가로선 존재
    // board[r][c] = -1: (r,c)에서 왼쪽으로 연결되는 가로선 존재
    // board[r][c] = 0: 가로선 없음
    static int[][] board;

    // 정답: 최소 가로선 추가 수. -1이면 불가능
    static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력: N, M, H
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        board = new int[H + 1][N + 1]; // 1-based index 사용

        // 이미 설치된 M개의 가로선 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            board[r][c] = 1;     // 오른쪽으로 연결
            board[r][c + 1] = -1; // 왼쪽으로 연결
        }

        // 0~3개까지 가로선을 추가해보면서 조합 탐색
        for (int i = 0; i <= 3; i++) {
            comb(0, 1, 1, i); // index=0, 시작 위치 (1,1), 추가할 개수=i
            if (answer != -1) { // 성공하면 즉시 출력
                System.out.println(answer);
                return;
            }
        }
        // 3개 이하로 불가능하면 -1 출력
        System.out.println(answer);
    }

    /**
     * 조합 + 백트래킹
     * @param index 현재까지 추가한 가로선 수
     * @param startR 탐색 시작 행
     * @param startC 탐색 시작 열
     * @param cnt 목표로 추가할 가로선 수
     */
    static void comb(int index, int startR, int startC, int cnt) {
        if (answer != -1) return; // 이미 정답 찾으면 종료

        if (index == cnt) { // 목표 수만큼 가로선을 추가했으면
            if (find()) {  // 사다리 조건 확인
                answer = cnt; // 조건 만족하면 정답 갱신
            }
            return;
        }

        // 현재 위치부터 가로선을 놓을 수 있는 모든 위치 탐색
        for (int i = startR; i <= H; i++) {
            for (int j = (i == startR ? startC : 1); j < N; j++) {
                // 연속된 가로선이 없으면 설치 가능
                if (board[i][j] == 0 && board[i][j + 1] == 0) {
                    board[i][j] = 1;
                    board[i][j + 1] = -1;
                    comb(index + 1, i, j, cnt); // 재귀 호출
                    board[i][j] = 0;   // 백트래킹
                    board[i][j + 1] = 0;
                }
            }
        }
    }

    /**
     * 사다리 조건 검사
     * 각 세로선이 시작점과 동일한 세로선으로 도착하는지 확인
     */
    static boolean find() {
        for (int start = 1; start <= N; start++) {
            int cur_c = start; // 현재 열 위치

            // 위에서 아래로 이동
            for (int cur_r = 1; cur_r <= H; cur_r++) {
                if (board[cur_r][cur_c] == 1) cur_c++;     // 오른쪽 이동
                else if (board[cur_r][cur_c] == -1) cur_c--; // 왼쪽 이동
            }

            // 도착 열이 시작 열과 다르면 실패
            if (cur_c != start) return false;
        }
        return true; // 모든 세로선 조건 만족
    }
}
