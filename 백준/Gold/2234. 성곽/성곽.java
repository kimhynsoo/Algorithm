import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R, C; // 행, 열
    static boolean[][] visited; // 방문 여부
    static int[][] map; // 입력된 성의 구조 정보
    static int[][] regionId; // 각 칸의 영역(방) 번호
    static Map<Integer, Integer> regionSize; // 영역 번호별 방 크기 저장

    // 방향: 서, 북, 동, 남
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {-1, 0, 1, 0};

    // 방향에 해당하는 벽 비트마스크
    static int[] mask = {1, 2, 4, 8};

    static int max = 0;       // 가장 큰 방의 크기
    static int max_size = 0;  // 벽을 하나 제거했을 때의 최대 방 크기

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력: 열(C), 행(R)
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        regionId = new int[R][C];
        regionSize = new HashMap<>();

        // 성곽 정보 입력
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 1단계: BFS로 방 나누기
        int cnt = 0; // 방의 개수
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j]) {
                    cnt++;
                    bfs(i, j, cnt);
                }
            }
        }

        // 2단계: 벽 하나를 제거했을 때의 최대 방 크기 계산
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    max_find(i, j);
                }
            }
        }

        // 결과 출력
        System.out.println(cnt);       // 방의 개수
        System.out.println(max);       // 가장 넓은 방의 크기
        System.out.println(max_size);  // 벽 제거 후 가장 넓은 방의 크기
    }

    // BFS를 이용해 하나의 방(영역)을 탐색하고 크기를 계산
    static void bfs(int r, int c, int r_id) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r, c});
        visited[r][c] = true;
        regionId[r][c] = r_id;

        int cnt = 0; // 현재 방의 크기

        while (!queue.isEmpty()) {
            cnt++;
            int[] cur = queue.poll();
            int c_r = cur[0];
            int c_c = cur[1];
            regionId[c_r][c_c] = r_id;

            // 4방향 탐색
            for (int d = 0; d < 4; d++) {
                // 벽이 없는 경우에만 이동 가능
                if ((mask[d] & map[c_r][c_c]) == 0) {
                    int nr = c_r + dr[d];
                    int nc = c_c + dc[d];
                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                    if (!visited[nr][nc]) {
                        visited[nr][nc] = true;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
        }

        // 가장 큰 방 크기 갱신
        if (cnt > max) {
            max = cnt;
        }

        // 현재 방의 크기 저장
        regionSize.put(r_id, cnt);
    }

    // 벽을 하나 제거했을 때 두 방을 합친 크기를 계산
    static void max_find(int r, int c) {
        for (int d = 0; d < 4; d++) {
            // 해당 방향에 벽이 있는 경우만 확인
            if ((mask[d] & map[r][c]) != 0) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 범위를 벗어나거나 같은 방인 경우 제외
                if (nr < 0 || nc < 0 || nr >= R || nc >= C) continue;
                if (regionId[nr][nc] == regionId[r][c]) continue;

                // 두 방의 크기 합산
                int size = regionSize.get(regionId[r][c]) + regionSize.get(regionId[nr][nc]);

                // 최대 크기 갱신
                if (size > max_size) {
                    max_size = size;
                }
            }
        }
    }
}
