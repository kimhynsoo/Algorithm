import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    static int N, ROW, COL;
    static int result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            COL = Integer.parseInt(st.nextToken());
            ROW = Integer.parseInt(st.nextToken());

            int[][] board = new int[ROW][COL];
            for (int i = 0; i < ROW; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < COL; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = Integer.MAX_VALUE;
            go(0, board); // 재귀 탐색 시작

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    /**
     * 백트래킹을 이용한 완전 탐색 함수
     * @param cnt 현재까지 사용한 구슬의 수
     * @param map 현재 상태의 벽돌 맵
     */
    static void go(int cnt, int[][] map) {
        // [가지치기 1] 이미 최적의 해(남은 벽돌 0개)를 찾았다면 더 이상 탐색 불필요
        if (result == 0) {
            return;
        }

        int remainingBricks = count(map);
        // [가지치기 2] 남은 벽돌 개수가 현재까지 찾은 최적의 해보다 크지 않다면 더 이상 탐색 불필요
        if (remainingBricks == 0) {
            result = 0;
            return;
        }

        // [기저 조건] 구슬을 N개 모두 사용했다면 탐색 종료
        if (cnt == N) {
            result = Math.min(result, count(map));
            return;
        }

        // '빈 열에 쏘는 경우(패스)'를 현재 턴에서 한 번만 처리하기 위한 플래그
        boolean noOpHandled = false;

        // 모든 열에 구슬을 떨어뜨리는 경우를 시도
        for (int c = 0; c < COL; c++) {
            // 현재 열에 벽돌이 있는지 확인
            int topBrickRow = -1;
            for (int r = 0; r < ROW; r++) {
                if (map[r][c] != 0) {
                    topBrickRow = r;
                    break;
                }
            }

            if (topBrickRow == -1) { // 해당 열에 벽돌이 없는 경우 (패스)
                // '패스' 경우는 어느 빈 열을 선택하든 결과가 동일하므로,
                // 현재 턴(cnt)에서 딱 한 번만 처리한다.
                if (!noOpHandled) {
                    noOpHandled = true;
                    go(cnt + 1, map); // 구슬만 1개 사용하고, 맵은 그대로 다음 턴으로
                }
            } else { // 해당 열에 벽돌이 있는 경우
                int[][] nextMap = copy(map);
                attack(c, nextMap);
                clean(nextMap);
                go(cnt + 1, nextMap); // 구슬 1개 사용하고, 변경된 맵으로 다음 턴으로
            }
        }
    }

    // 벽돌 파괴 (BFS)
    static void attack(int col, int[][] temp) {
        Queue<int[]> q = new ArrayDeque<>();
        
        // attack 함수 내부에서도 맨 위 벽돌을 찾지만, go 함수 로직의 명확성을 위해 중복 허용
        int row = -1;
        for (int i = 0; i < ROW; i++) {
            if (temp[i][col] != 0) {
                row = i;
                break;
            }
        }
        if (row == -1) return;

        q.offer(new int[]{row, col, temp[row][col]});
        temp[row][col] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], power = cur[2];

            if (power <= 1) continue;

            for (int d = 0; d < 4; d++) {
                for (int k = 1; k < power; k++) {
                    int nx = x + dx[d] * k;
                    int ny = y + dy[d] * k;

                    if (nx < 0 || ny < 0 || nx >= ROW || ny >= COL) continue;
                    
                    if (temp[nx][ny] > 0) {
                        q.offer(new int[]{nx, ny, temp[nx][ny]});
                        temp[nx][ny] = 0;
                    }
                }
            }
        }
    }

    // 벽돌 정리 (중력 작용)
    static void clean(int[][] temp) {
        for (int c = 0; c < COL; c++) {
            int write = ROW - 1; // 벽돌을 새로 쓸 위치
            for (int r = ROW - 1; r >= 0; r--) { // 아래부터 읽는다
                if (temp[r][c] != 0) {
                    int val = temp[r][c];
                    temp[r][c] = 0;
                    temp[write--][c] = val; // 아래부터 차곡차곡 쓴다
                }
            }
        }
    }
    
    // 2차원 배열 깊은 복사
    static int[][] copy(int[][] original) {
        int[][] temp = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            System.arraycopy(original[i], 0, temp[i], 0, COL);
        }
        return temp;
    }

    // 남은 벽돌 개수 세기
    static int count(int[][] temp) {
        int cnt = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (temp[i][j] != 0) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}