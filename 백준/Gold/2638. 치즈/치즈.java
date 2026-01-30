import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][] cheese;
    static int[][] airContact;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cheese = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;
        while (true) {
            airContact = new int[N][M];
            visited = new boolean[N][M];
            bfs(0, 0);

            boolean melted = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (cheese[i][j] == 1 && airContact[i][j] >= 2) {
                        cheese[i][j] = 0;
                        melted = true;
                    }
                }
            }

            if (!melted) break;
            time++;
        }

        System.out.println(time);
    }

    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            x = cur[0];
            y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) continue;

                if (cheese[nx][ny] == 0) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                } else if (cheese[nx][ny] == 1) {
                    airContact[nx][ny]++;
                }
            }
        }
    }
}