import java.util.*;

public class Main {
    static int n;
    static int[][] map; // 바다 상태 (0=빈칸, 물고기 크기, 9=아기상어 초기 위치)
    // 탐색 순서: ↑, ←, →, ↓  (문제 조건: 위쪽, 왼쪽 우선)
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    // 아기상어 정보
    static class Shark {
        int x, y;   // 현재 위치
        int size;   // 현재 크기
        int eat;    // 지금 크기에서 먹은 물고기 수

        Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2; // 시작 크기는 2
            this.eat = 0;
        }
    }

    // 먹을 수 있는 물고기 정보
    static class Fish implements Comparable<Fish> {
        int x, y;   // 물고기 위치
        int dist;   // 상어와의 거리

        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        // 정렬 기준: 1. 거리 짧은 순 → 2. 위쪽(y좌표 작은 순) → 3. 왼쪽(x좌표 작은 순)
        @Override
        public int compareTo(Fish o) {
            if (this.dist == o.dist) {
                if (this.x == o.x) return this.y - o.y; 
                return this.x - o.x;
            }
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        map = new int[n][n];
        Shark shark = null;

        // 지도 입력
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 9) {
                    // 아기상어 초기 위치 발견
                    shark = new Shark(i, j);
                    map[i][j] = 0; // 상어 위치는 빈 칸으로 초기화
                }
            }
        }

        int time = 0; // 총 이동 시간
        while (true) {
            // BFS로 현재 위치에서 가장 가까운 먹을 수 있는 물고기 찾기
            Fish target = bfs(shark);
            if (target == null) break; // 더 이상 먹을 수 있는 물고기 없음 → 종료

            // 상어 위치 갱신
            shark.x = target.x;
            shark.y = target.y;
            // 이동 시간 누적
            time += target.dist;

            // 물고기 먹기
            shark.eat++;
            map[target.x][target.y] = 0; // 먹힌 자리는 빈칸

            // 상어 크기 성장 조건 확인
            if (shark.eat == shark.size) {
                shark.size++;
                shark.eat = 0;
            }
        }

        // 최종 시간 출력
        System.out.println(time);
    }

    /**
     * BFS로 상어의 현재 위치에서 가장 가까운 먹을 수 있는 물고기를 찾음
     */
    static Fish bfs(Shark shark) {
        boolean[][] visited = new boolean[n][n];
        Queue<int[]> q = new LinkedList<>();
        List<Fish> fishes = new ArrayList<>(); // 먹을 수 있는 후보 물고기들

        // 시작점 큐에 넣기
        q.add(new int[]{shark.x, shark.y, 0}); // x, y, 거리
        visited[shark.x][shark.y] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            // 현재 칸에 상어보다 작은 물고기가 있으면 먹을 수 있음
            if (map[x][y] > 0 && map[x][y] < shark.size) {
                fishes.add(new Fish(x, y, dist));
            }

            // 4방향 이동
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                // 범위 체크
                if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                // 방문 여부, 이동 가능 여부 (자기보다 큰 물고기는 못 지나감)
                if (!visited[nx][ny] && map[nx][ny] <= shark.size) {
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, dist + 1});
                }
            }
        }

        // 먹을 수 있는 물고기가 없으면 null 반환
        if (fishes.isEmpty()) return null;
        // 정렬해서 우선순위가 가장 높은 물고기 반환
        Collections.sort(fishes);
        return fishes.get(0);
    }
}
