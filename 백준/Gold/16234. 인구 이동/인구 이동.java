import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R;
    static int[][] map;
    static boolean[][] isOpened;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static boolean moved;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        do {
            isOpened = new boolean[N][N];
            moved = false;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!isOpened[i][j]) BFS(i, j);
                }
            }

            if (moved) cnt++;
        } while (moved);

        System.out.println(cnt);
    }

    static void BFS(int x, int y) {
        List<int[]> union = new ArrayList<>();
        Deque<int[]> q = new ArrayDeque<>();

        // 시작 지점 방문 처리
        isOpened[x][y] = true;
        q.offer(new int[]{x, y});
        union.add(new int[]{x, y});
        int sum = map[x][y];

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (isOpened[nx][ny]) continue;

                int diff = Math.abs(map[cx][cy] - map[nx][ny]);
                if (diff >= L && diff <= R) {
                    isOpened[nx][ny] = true; // 큐에 넣을 때 방문 체크
                    q.offer(new int[]{nx, ny});
                    union.add(new int[]{nx, ny});
                    sum += map[nx][ny];
                }
            }
        }

        if (union.size() > 1) { // 연합이 형성된 경우
            moved = true;
            int avg = sum / union.size();
            for (int[] c : union) {
                map[c[0]][c[1]] = avg;
            }
        }
    }
}
