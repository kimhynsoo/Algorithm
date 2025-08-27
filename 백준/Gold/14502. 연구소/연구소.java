import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] board;               // 원본 연구소 지도
    static int[] visited = new int[3];  // 선택한 벽 3개 위치 저장 (1차원 index 값으로 저장)

    static int row, col;                // 연구소 크기
    static int max = Integer.MIN_VALUE; // 안전영역 최댓값 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());  // 행 개수
        col = Integer.parseInt(st.nextToken());  // 열 개수

        // 연구소 지도 입력
        board = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 벽 3개를 세우는 모든 조합 시도
        comb(0, 0);

        // 결과 출력
        System.out.println(max);
    }

    // 벽 3개 조합 선택
    static void comb(int cnt, int start) {
        if (cnt == 3) {  // 벽 3개를 모두 선택한 경우
            int[][] temp = copyMap();  // 원본 board 복사
            // 선택된 위치에 벽 세우기
            for (int i = 0; i < 3; i++) {
                // visited[i]는 1차원 index (0 ~ row*col-1)
                // 행 좌표 = index / col  → 몇 번째 행인지 계산
                int r = visited[i] / col;
                // 열 좌표 = index % col  → 행 내에서 몇 번째 열인지 계산 (모듈러 연산)
                int c = visited[i] % col;

                temp[r][c] = 1; // 해당 위치에 벽 세우기
            }

            // BFS 탐색을 위한 방문 배열
            boolean[][] visited = new boolean[row][col];

            // 연구소의 모든 위치 탐색
            // 바이러스(2)를 발견하면 BFS로 퍼뜨림
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (temp[i][j] == 2 && !visited[i][j]) {
                        spread(temp, i, j, visited);
                    }
                }
            }

            // 안전영역 크기 계산
            check(temp);
            return;
        }

        // 연구소 전체 칸을 탐색하며 벽 세울 위치 고르기
        int N = row * col;
        for (int i = start; i < N; i++) {
            int r = i / col;    // 행 좌표 = index / col  → 몇 번째 행인지 계산
            int c = i % col;    // 열 좌표 = index % col  → 행 내에서 몇 번째 열인지 계산 (모듈러 연산)
            if (board[r][c] == 0) {   // 빈 칸(0)인 경우만 벽 설치 가능
                visited[cnt] = i;     // 선택한 위치 저장
                comb(cnt + 1, i + 1); // 다음 벽 선택
            }
        }
    }

    // 네 방향 이동 (상, 하, 좌, 우)
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    // 바이러스 퍼뜨리기 (BFS)
    static void spread(int[][] temp, int r, int c, boolean[][] visited) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                // 범위 벗어나거나 이미 방문했거나 벽이면 skip
                if (nx < 0 || ny < 0 || nx >= row || ny >= col || visited[nx][ny] || temp[nx][ny] == 1) continue;

                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
                temp[nx][ny] = 2; // 바이러스 확산
            }
        }
    }

    // 안전 영역(0의 개수) 계산
    static void check(int[][] temp) {
        int cnt = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (temp[i][j] == 0) cnt++;
            }
        }
        max = Math.max(max, cnt);
    }

    // 원본 지도 복사 (deep copy)
    static int[][] copyMap() {
        int temp[][] = new int[row][col];
        for (int i = 0; i < row; i++) {
            temp[i] = Arrays.copyOf(board[i], col);
        }
        return temp;
    }
}
