import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { 
    // row: 상자의 세로 크기, col: 상자의 가로 크기
    static int row, col;
    // board: 토마토 상자의 상태를 저장하는 2차원 배열
    static int[][] board;
    // dx, dy: 상하좌우 탐색을 위한 방향 배열
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};
    // visited: 이 풀이에서는 board의 값(0->1) 변경으로 방문 여부를 확인하므로 실제로는 사용되지 않음
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        board = new int[row][col];

        // 토마토 상자의 초기 상태를 입력받음
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // BFS를 실행하여 모든 토마토가 익는 데 걸리는 최소 날짜를 계산
        int days = bfs();

        // --- BFS 종료 후, 모든 토마토가 익었는지 최종 확인하는 과정 ---
        // 이 부분이 이 문제의 핵심 중 하나입니다.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 만약 익지 않은 토마토(0)가 하나라도 남아있다면
                if (board[i][j] == 0) {
                    // 결과값을 -1로 변경 (토마토가 모두 익는 것이 불가능함)
                    days = -1;
                    break; // 내부 반복문 종료
                }
            }
            if (days == -1) {
                break; // 외부 반복문도 종료
            }
        }
        
        // 최종 결과 출력
        System.out.println(days);
    }

    static int bfs() {
        // BFS를 위한 큐(Queue) 생성
        Queue<int[]> q = new ArrayDeque<>();
        int days = 0; // 경과한 날짜를 저장할 변수

        // 초기에 익어있는 토마토(1)를 모두 찾아서 큐에 추가
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 1) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        // 큐가 빌 때까지 (더 이상 익을 토마토가 없을 때까지) 반복
        while (!q.isEmpty()) {
            // 하루 단위로 처리하기 위해 현재 큐의 사이즈를 저장
            int qSize = q.size();
            // 해당 날짜에 새로 익은 토마토가 있는지 확인하기 위한 플래그
            boolean ripened = false;

            // 현재 큐에 있는 모든 토마토(하루 동안 영향을 주는 토마토들)에 대해 반복
            while (--qSize >= 0) {
                int[] cur = q.poll();

                // 상하좌우 네 방향으로 탐색
                for (int i = 0; i < 4; i++) {
                    int nx = cur[0] + dx[i];
                    int ny = cur[1] + dy[i];

                    // 상자 범위를 벗어나면 건너뜀
                    if (nx < 0 || ny < 0 || nx >= row || ny >= col) continue;

                    // 만약 주변에 안 익은 토마토(0)가 있다면
                    if (board[nx][ny] == 0) {
                        board[nx][ny] = 1;      // 토마토를 익음(1) 상태로 변경
                        q.add(new int[]{nx, ny}); // 새로 익은 토마토를 큐에 추가
                        ripened = true;         // 하루 동안 새로 익은 토마토가 있다고 표시
                    }
                }
            }
            
            // 만약 이번 날짜에 새로 익은 토마토가 있었다면 날짜를 1일 증가
            if (ripened) {
                days++;
            }
        }

        // 최종적으로 계산된 날짜를 반환
        return days;
    }
}