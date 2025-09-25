import java.util.*;

public class Main {
    // 전역 변수들
    static int n, m, g, r;          // n,m: 지도 크기 / g: 초록 배양액 개수 / r: 빨강 배양액 개수
    static int[][] map;             // 정원 지도 (0: 못 심음, 1: 심을 수 있음, 2: 배양액 후보지)
    static List<int[]> candidates = new ArrayList<>(); // 배양액을 뿌릴 수 있는 후보 위치 목록
    static int maxFlowers = 0;      // 최종적으로 만들 수 있는 꽃 개수의 최대값

    // 4방향 이동 벡터
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        g = sc.nextInt();
        r = sc.nextInt();

        // 지도 입력
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
                // 배양액 후보지(2)만 따로 저장
                if (map[i][j] == 2) {
                    candidates.add(new int[]{i, j});
                }
            }
        }

        // 후보 위치에서 g개, r개 선택하는 모든 경우의 수 탐색
        choose(0, 0, new ArrayList<>(), new ArrayList<>());
        System.out.println(maxFlowers);
    }

    /**
     * 후보 위치에서 g개, r개를 뽑는 조합 탐색
     * @param idx      현재 후보 위치 인덱스
     * @param greenCnt 현재까지 고른 초록 배양액 수
     * @param greens   초록 배양액 위치 목록
     * @param reds     빨강 배양액 위치 목록
     */
    static void choose(int idx, int greenCnt, List<int[]> greens, List<int[]> reds) {
        // 초록/빨강 개수를 초과하면 중단
        if (greens.size() > g || reds.size() > r) return;

        // 모든 후보를 다 확인했을 때
        if (idx == candidates.size()) {
            // 초록 g개, 빨강 r개를 정확히 뽑은 경우에만 시뮬레이션 실행
            if (greens.size() == g && reds.size() == r) {
                simulate(greens, reds); 
            }
            return;
        }

        int[] pos = candidates.get(idx);

        // 1) 현재 위치를 초록 배양액으로 선택
        greens.add(pos);
        choose(idx + 1, greenCnt + 1, greens, reds);
        greens.remove(greens.size() - 1); // 백트래킹

        // 2) 현재 위치를 빨강 배양액으로 선택
        reds.add(pos);
        choose(idx + 1, greenCnt, greens, reds);
        reds.remove(reds.size() - 1); // 백트래킹

        // 3) 현재 위치에 배양액을 두지 않음
        choose(idx + 1, greenCnt, greens, reds);
    }

    /**
     * BFS 시뮬레이션 - 선택된 초록, 빨강 배양액이 동시에 퍼져나가는 과정
     * @param greens 초록 배양액 시작 위치들
     * @param reds   빨강 배양액 시작 위치들
     */
    static void simulate(List<int[]> greens, List<int[]> reds) {
        int[][] time = new int[n][m];   // 배양액이 퍼진 시간 기록
        int[][] color = new int[n][m];  // 배양액 색 (0=없음, 1=초록, 2=빨강, 3=꽃)

        Queue<int[]> q = new LinkedList<>();

        // 초기 초록 배양액 위치 설정
        for (int[] gPos : greens) {
            q.add(new int[]{gPos[0], gPos[1], 1, 1}); // x, y, 시간=1, 색=1(초록)
            time[gPos[0]][gPos[1]] = 1;
            color[gPos[0]][gPos[1]] = 1;
        }

        // 초기 빨강 배양액 위치 설정
        for (int[] rPos : reds) {
            q.add(new int[]{rPos[0], rPos[1], 1, 2}); // 색=2(빨강)
            time[rPos[0]][rPos[1]] = 1;
            color[rPos[0]][rPos[1]] = 2;
        }

        int flowers = 0; // 이번 시뮬레이션에서 핀 꽃 개수

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], t = cur[2], c = cur[3];

            // 이미 꽃이 된 칸은 전파 불가
            if (color[x][y] == 3) continue;

            // 4방향 확산
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (map[nx][ny] == 0) continue; // 호수(못심는 땅)

                if (time[nx][ny] == 0) {
                    // 아직 배양액이 퍼지지 않은 칸 → 현재 배양액 전파
                    time[nx][ny] = t + 1;
                    color[nx][ny] = c;
                    q.add(new int[]{nx, ny, t + 1, c});
                } 
                // 같은 시간에 다른 색 배양액이 도착 → 꽃 생성
                else if (time[nx][ny] == t + 1 && color[nx][ny] != c && color[nx][ny] != 3) {
                    color[nx][ny] = 3; // 꽃
                    flowers++;
                }
            }
        }

        // 최대 꽃 개수 갱신
        maxFlowers = Math.max(maxFlowers, flowers);
    }
}
