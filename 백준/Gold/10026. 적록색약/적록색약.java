import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        visited = new boolean[N][N];

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    bfs(i, j);
                }
            }
        }

        int cnt2 = 0;
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    cnt2++;
                    bfs2(i, j);
                }
            }
        }

        System.out.println(cnt + " " + cnt2);

    }

    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };

    static void bfs(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { r, c });
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc])
                    continue;
                if (map[nr][nc] == map[cur[0]][cur[1]]) {
                    queue.add(new int[] { nr, nc });
                    visited[nr][nc] = true;
                }
            }

        }
    }

    static void bfs2(int r, int c) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] { r, c });
        visited[r][c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc])
                    continue;
                if (map[cur[0]][cur[1]] == 'R' || map[cur[0]][cur[1]] == 'G') {
                    if (map[nr][nc] == 'R' || map[nr][nc] == 'G') {
                        queue.add(new int[] { nr, nc });
                        visited[nr][nc] = true;
                    }
                } else {
                    if (map[nr][nc] == 'B') {
                        queue.add(new int[] { nr, nc });
                        visited[nr][nc] = true;
                    }
                }
            }

        }
    }
}