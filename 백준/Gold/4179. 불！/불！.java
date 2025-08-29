import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_4179 {
    // 좌표(x, y)와 시간(time)을 담는 클래스
    static class Point {
        int x, y, time;
        Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static char board[][];          // 지도 상태 ('.', '#', 'J', 'F')
    static boolean visited[][];     // 지훈이 방문 여부 체크
    static int R, C;                // 행, 열 크기
    static Queue<Point> jList = new ArrayDeque<>(); // 지훈이의 이동 BFS 큐
    static Queue<Point> fList = new ArrayDeque<>(); // 불의 확산 BFS 큐
    static int[] dx = {-1,1,0,0};   // 상하좌우
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        visited = new boolean[R][C];

        // 입력 처리
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                board[i][j] = c;

                if (c == 'F') { // 불 시작 위치
                    fList.offer(new Point(i, j, 0));
                } 
                else if (c == 'J') { // 지훈이 시작 위치
                    jList.offer(new Point(i, j, 0));
                    visited[i][j] = true; // 방문 처리
                }
            }
        }

        // BFS 실행
        bfs();
    }

    static void bfs() {
        // 지훈이가 이동할 수 없을 때까지 반복
        while (!jList.isEmpty()) {

            // 1. 먼저 불 확산
            int f_size = fList.size();
            for (int i = 0; i < f_size; i++) {
                Point cur = fList.poll();
                int x = cur.x;
                int y = cur.y;

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    // 범위를 벗어나면 패스
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;

                    // 빈 칸('.')이나 지훈이('J')가 있는 칸만 불이 번짐
                    if (board[nx][ny] == '.' || board[nx][ny] == 'J') {
                        board[nx][ny] = 'F'; // 불로 채움
                        fList.offer(new Point(nx, ny, 0));
                    }
                }
            }

            // 2. 지훈이 이동
            int j_size = jList.size();
            for (int i = 0; i < j_size; i++) {
                Point cur = jList.poll();
                int x = cur.x;
                int y = cur.y;
                int ntime = cur.time + 1; // 한 칸 이동 → 시간 +1

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    // 범위를 벗어났다면 탈출 성공
                    if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                        System.out.println(ntime);
                        return;
                    }

                    // 이미 방문했으면 패스
                    if (visited[nx][ny]) continue;

                    // 불도 없고 벽도 없는 빈 칸일 때만 이동 가능
                    if (board[nx][ny] == '.') {
                        visited[nx][ny] = true;
                        jList.offer(new Point(nx, ny, ntime));
                    }
                }
            }
        }
        // 탈출 불가능할 경우
        System.out.println("IMPOSSIBLE");
    }
}
