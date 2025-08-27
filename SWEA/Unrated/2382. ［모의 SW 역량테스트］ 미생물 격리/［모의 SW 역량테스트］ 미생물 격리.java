import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    // 미생물 그룹을 나타내는 클래스
    static class Group {
        int x, y, cnt, d; // 좌표 (x,y), 미생물 수(cnt), 이동 방향(d)

        public Group(int x, int y, int cnt, int d) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.d = d;
        }
    }

    static int N, M, K;               // N: 맵 크기, M: 진행 시간, K: 군집 수
    static ArrayList<Group> groups;   // 현재 존재하는 군집 리스트
    static int[] dx = {0, -1, 1, 0, 0}; // 방향: 1=상, 2=하, 3=좌, 4=우
    static int[] dy = {0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 맵 크기
            M = Integer.parseInt(st.nextToken()); // 격리 시간
            K = Integer.parseInt(st.nextToken()); // 미생물 군집 수

            groups = new ArrayList<>();

            // 초기 군집 입력
            for (int i = 1; i <= K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                groups.add(new Group(x, y, cnt, d));
            }

            // M시간 동안 이동 반복
            while (M-- > 0) {
                move();
            }

            // 남아 있는 군집의 미생물 수 합산
            int res = 0;
            for (Group g : groups) res += g.cnt;

            System.out.printf("#%d %d\n", tc, res);
        }
    }

    // 한 턴 동안 군집들을 이동시키고, 합쳐주는 함수
    static void move() {
        // 임시 보드: 이동한 군집들을 위치별로 저장
        ArrayList<Group>[][] temp = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = new ArrayList<>();
            }
        }

        // 1. 모든 그룹 이동
        for (Group g : groups) {
            int nx = g.x + dx[g.d]; // 이동 후 좌표
            int ny = g.y + dy[g.d];
            int nd = g.d;           // 방향
            int cnt = g.cnt;        // 미생물 수

            // 경계(약품 구역)에 닿으면
            if (nx == 0 || ny == 0 || nx == N - 1 || ny == N - 1) {
                cnt /= 2; // 미생물 수 절반 감소
                if (cnt == 0) continue; // 수가 0이 되면 군집 소멸

                // 방향 반전
                if (nd == 1) nd = 2;
                else if (nd == 2) nd = 1;
                else if (nd == 3) nd = 4;
                else if (nd == 4) nd = 3;
            }

            // 이동한 군집을 해당 위치에 추가
            temp[nx][ny].add(new Group(nx, ny, cnt, nd));
        }

        // 2. 좌표별 합치기
        groups = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (temp[i][j].isEmpty()) continue; // 군집 없음

                if (temp[i][j].size() == 1) {
                    // 군집이 1개라면 그대로 추가
                    groups.add(temp[i][j].get(0));
                } else {
                    // 군집이 2개 이상이라면 합침
                    int sum = 0, maxCnt = -1, dir = 0;
                    for (Group g : temp[i][j]) {
                        sum += g.cnt; // 미생물 수 합산
                        if (g.cnt > maxCnt) {
                            maxCnt = g.cnt; // 가장 큰 군집 찾기
                            dir = g.d;      // 그 군집의 방향 유지
                        }
                    }
                    groups.add(new Group(i, j, sum, dir));
                }
            }
        }
    }
}
