import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class  Solution {
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
            go(0, board); // 재귀 호출 시작

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    /**
     * @param cnt 현재까지 사용한 구슬의 수
     * @param map 현재 상태의 벽돌 맵
     */
    static void go(int cnt, int[][] map) {
        // [가지치기 1] 이미 최적의 해(남은 벽돌 0개)를 찾았다면 더 이상 탐색 불필요
        if (result == 0) {
            return;
        }

        // [기저 조건] 구슬을 N개 모두 사용했다면
        if (cnt == N) {
            // 남은 벽돌 수를 계산하여 최솟값 갱신
            int remainingBricks = count(map);
            result = Math.min(result, remainingBricks);
            return;
        }

        // 모든 열에 대해 구슬을 떨어뜨리는 경우를 시도
        for (int c = 0; c < COL; c++) {
            // 1. 현재 맵 상태를 깊은 복사하여 다음 시뮬레이션에 사용
            int[][] nextMap = copy(map);
            
            // 2. 해당 열(c)에 구슬을 떨어뜨려 벽돌을 파괴
            attack(c, nextMap);
            
            // 3. 파괴 후 벽돌들을 아래로 정리
            clean(nextMap);
            
            // 4. 다음 구슬을 사용하기 위해 재귀 호출
            go(cnt + 1, nextMap);
        }
    }

    static void attack(int col, int[][] temp) {
        // col 열에서 가장 위 벽돌 찾기
        int row = -1;
        for (int i = 0; i < ROW; i++) {
            if (temp[i][col] != 0) {
                row = i;
                break;
            }
        }
        // [가지치기 2] 해당 열에 벽돌이 없으면 아무 일도 일어나지 않음
        if (row == -1) return;

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{row, col, temp[row][col]});
        temp[row][col] = 0; // 시작 벽돌 제거

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], power = cur[2];

            if (power <= 1) continue;

            // 4방향으로 power-1 칸씩 전파
            for (int d = 0; d < 4; d++) {
                int nx = x;
                int ny = y;
                for (int k = 1; k < power; k++) {
                    nx += dx[d];
                    ny += dy[d];
                    if (nx < 0 || ny < 0 || nx >= ROW || ny >= COL) break;
                    if (temp[nx][ny] > 0) {
                        q.offer(new int[]{nx, ny, temp[nx][ny]});
                        temp[nx][ny] = 0; // 큐에 넣으면서 바로 제거 처리
                    }
                }
            }
        }
    }

    static void clean(int[][] temp) {
        for (int c = 0; c < COL; c++) {
            // 아래에서부터 살아있는 벽돌을 담을 큐 (또는 스택, 리스트)
            ArrayDeque<Integer> survivors = new ArrayDeque<>();
            for (int r = ROW - 1; r >= 0; r--) {
                if (temp[r][c] > 0) {
                    survivors.add(temp[r][c]);
                }
            }
            // 현재 열을 0으로 초기화
            for (int r = 0; r < ROW; r++) {
                temp[r][c] = 0;
            }
            // 큐에 담아둔 벽돌을 아래부터 다시 채우기
            int r = ROW - 1;
            while (!survivors.isEmpty()) {
                temp[r--][c] = survivors.poll();
            }
        }
    }
    
    static int[][] copy(int[][] original) {
        int[][] temp = new int[ROW][COL];
        for (int i = 0; i < ROW; i++) {
            System.arraycopy(original[i], 0, temp[i], 0, COL);
        }
        return temp;
    }

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