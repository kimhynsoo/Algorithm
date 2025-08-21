import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static int[][] board;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        int lastCheese = 0;

        while (true) {
            boolean[][] visited = new boolean[R][C];
            List<int[]> meltList = new ArrayList<>();

            // 외부 공기 표시 BFS
            bfsAir(0, 0, visited, meltList);

            if (meltList.isEmpty()) break; // 남은 치즈 없음

            lastCheese = meltList.size();

            // 치즈 녹이기
            for (int[] pos : meltList) {
                board[pos[0]][pos[1]] = 0;
            }

            time++;
        }

        System.out.println(time);
        System.out.println(lastCheese);
    }

    static void bfsAir(int r, int c, boolean[][] visited, List<int[]> meltList) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny]) continue;

                visited[nx][ny] = true;

                if (board[nx][ny] == 0) { 
                    // 외부 공기이면 큐에 추가
                    q.offer(new int[]{nx, ny});
                } else if (board[nx][ny] == 1) {
                    // 외부 공기와 접한 치즈 → 녹일 리스트에 추가
                    meltList.add(new int[]{nx, ny});
                }
            }
        }
    }
}
