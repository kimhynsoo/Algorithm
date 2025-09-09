import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // 좌표와 먼지량을 담는 클래스
    static class Pair {
        int r, c;     // 좌표
        int A;        // 먼지의 양
        int spread;   // 확산되는 양 (A/5)
        Pair(int r, int c, int A) {
            this.r = r;
            this.c = c;
            this.A = A;
            this.spread = A / 5;
        }
    }

    static int R, C, T;          // 행, 열, 시간
    static int[][] board;        // 방의 상태
    static Queue<Pair> q;        // 먼지 확산 큐
    static int[] air_clear = new int[2]; // 공기청정기 위치 (두 줄)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        board = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 공기청정기 위치 저장 (맨 왼쪽 열에 두 칸 연속)
        int idx = 0;
        for (int i = 0; i < R; i++) {
            if (board[i][0] == -1) {
                air_clear[idx++] = i;
            }
        }

        // 확산 방향 (상,하,좌,우)
        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };

        // T초 동안 시뮬레이션
        while (T-- > 0) {
            q = new ArrayDeque<>();

            // 현재 먼지 위치 큐에 저장
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (board[i][j] > 0) {
                        q.offer(new Pair(i, j, board[i][j]));
                    }
                }
            }

            // 1. 미세먼지 확산
            while (!q.isEmpty()) {
                Pair cur = q.poll();
                int r = cur.r;
                int c = cur.c;
                int remove = cur.spread; // 한 칸에 퍼질 양
                int cnt = 0;             // 실제 퍼진 방향 개수

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    // 범위 밖이거나 공기청정기 칸이면 확산 불가
                    if (nr < 0 || nc < 0 || nr >= R || nc >= C || board[nr][nc] == -1) continue;
                    cnt++;
                    board[nr][nc] += remove; // 확산
                }
                board[r][c] -= cnt * remove; // 퍼진 만큼 현재 칸에서 감소
            }

            // 2. 공기청정기 작동 (위쪽: 반시계, 아래쪽: 시계)
            rotate();   // 위쪽 반시계 회전
            rotate2();  // 아래쪽 시계 회전
        }

        // 모든 먼지 합산
        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (board[i][j] > 0) {
                    sum += board[i][j];
                }
            }
        }
        System.out.println(sum);
    }

    // 위쪽 공기청정기 (반시계 방향 회전)
    static void rotate() {
        int r = air_clear[0];

        // 왼쪽 줄: 위 → 아래
        for (int i = r - 1; i > 0; i--) {
            board[i][0] = board[i - 1][0];
        }

        // 윗줄: 왼쪽 ← 오른쪽
        for (int i = 0; i < C - 1; i++) {
            board[0][i] = board[0][i + 1];
        }

        // 오른쪽 줄: 아래 ← 위
        for (int i = 0; i < r; i++) {
            board[i][C - 1] = board[i + 1][C - 1];
        }

        // 청정기 줄: 오른쪽 ← 왼쪽
        for (int i = C - 1; i > 1; i--) {
            board[r][i] = board[r][i - 1];
        }

        board[r][1] = 0; // 공기청정기 바로 옆칸은 깨끗한 공기
    }

    // 아래쪽 공기청정기 (시계 방향 회전)
    static void rotate2() {
        int r = air_clear[1];

        // 왼쪽 줄: 아래 → 위
        for (int i = r + 1; i < R - 1; i++) {
            board[i][0] = board[i + 1][0];
        }

        // 아랫줄: 왼쪽 ← 오른쪽
        for (int i = 0; i < C - 1; i++) {
            board[R - 1][i] = board[R - 1][i + 1];
        }

        // 오른쪽 줄: 위 ← 아래
        for (int i = R - 1; i > r; i--) {
            board[i][C - 1] = board[i - 1][C - 1];
        }

        // 청정기 줄: 오른쪽 ← 왼쪽
        for (int i = C - 1; i > 1; i--) {
            board[r][i] = board[r][i - 1];
        }

        board[r][1] = 0; // 공기청정기 바로 옆칸은 깨끗한 공기
    }
}
