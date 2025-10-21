import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C;
    static char[][] map;
    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};
    static int X_cnt = 0;
    static boolean[][][] visited;
    static ArrayList<int[]> items = new ArrayList<>();

    // 위치, 이동 거리, 습득 상태(mask)를 저장하는 클래스
    static class Pair {
        int r, c, dist, mask;

        public Pair(int r, int c, int dist, int mask) {
            this.r = r;
            this.c = c;
            this.dist = dist;
            this.mask = mask;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Pair start = new Pair(0, 0, 0, 0);

        map = new char[R][C];

        // 지도 입력 및 시작점, 아이템 위치 기록
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char c = line.charAt(j);
                map[i][j] = c;

                if (c == 'X') {
                    X_cnt++;
                    items.add(new int[]{i, j});
                } else if (c == 'S') {
                    start = new Pair(i, j, 0, 0);
                }
            }
        }

        // 방문 상태 배열: [행][열][아이템 습득 상태]
        visited = new boolean[R][C][1 << X_cnt];

        bfs(start);
    }

    // BFS 탐색: 각 상태(좌표, mask)를 큐에 넣어 최소 이동 거리 탐색
    static void bfs(Pair start) {
        Queue<Pair> q = new ArrayDeque<>();
        q.offer(start);
        visited[start.r][start.c][0] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int dist = cur.dist;
            int mask = cur.mask;

            // 모든 아이템을 모은 후 E에 도착하면 종료
            if (map[r][c] == 'E' && mask == (1 << X_cnt) - 1) {
                System.out.println(dist);
                break;
            }

            // 4방향 탐색
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 경계 밖 또는 벽은 이동 불가
                if (nr < 0 || nc < 0 || nr >= R || nc >= C || map[nr][nc] == '#')
                    continue;

                int nextmask = mask;

                // 이동한 위치가 아이템이면 비트마스크 갱신
                for (int i = 0; i < X_cnt; i++) {
                    if (items.get(i)[0] == nr && items.get(i)[1] == nc) {
                        nextmask = mask | (1 << i);
                        break;
                    }
                }

                // 해당 상태를 처음 방문하는 경우만 큐에 추가
                if (!visited[nr][nc][nextmask]) {
                    visited[nr][nc][nextmask] = true;
                    q.offer(new Pair(nr, nc, dist + 1, nextmask));
                }
            }
        }
    }
}
