import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map;
    static int[][] owner; // 어떤 섬에서 확장된 칸인지
    static int[][] dist;  // 해당 섬에서 바다를 몇 칸 건너왔는지

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        owner = new int[N][N];
        dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = -1;
            }
        }

        int islandNum = 1;

        // 1. 섬 번호 붙이기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && owner[i][j] == 0) {
                    labelIsland(i, j, islandNum++);
                }
            }
        }

        // 2. 모든 육지를 시작점으로 multi-source BFS
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (owner[i][j] > 0) {
                    q.offer(new int[]{i, j});
                    dist[i][j] = 0;
                }
            }
        }

        int answer = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;

                // 아직 아무 섬도 도달하지 않은 바다라면 확장
                if (owner[nr][nc] == 0) {
                    owner[nr][nc] = owner[r][c];
                    dist[nr][nc] = dist[r][c] + 1;
                    q.offer(new int[]{nr, nc});
                }
                // 이미 다른 섬이 확장한 곳과 만났다면 다리 길이 갱신
                else if (owner[nr][nc] != owner[r][c]) {
                    answer = Math.min(answer, dist[nr][nc] + dist[r][c]);
                }
            }
        }

        System.out.println(answer);
    }

    static void labelIsland(int sr, int sc, int num) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{sr, sc});
        owner[sr][sc] = num;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if (map[nr][nc] == 1 && owner[nr][nc] == 0) {
                    owner[nr][nc] = num;
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }
}