import java.io.*;
import java.util.*;

/**
 * 백준(BOJ) 15683번 문제 "감시" 솔루션
 * DFS와 백트래킹을 이용하여 모든 CCTV의 방향 조합을 탐색하고, 사각지대의 최솟값을 찾는다.
 */
public class Main {
    static int row, col; // 사무실의 세로, 가로 크기
    static int[][] board; // 사무실의 초기 상태를 저장하는 배열
    // 방향 벡터: 0:동, 1:남, 2:서, 3:북 (시계방향)
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static ArrayList<CCTV> cctvList = new ArrayList<>(); // CCTV의 정보를 저장하는 리스트
    static int minBlindSpots = Integer.MAX_VALUE; // 사각지대의 최솟값을 저장할 변수 (최대값으로 초기화)

    // CCTV의 위치(r, c)와 종류(type)를 저장하는 내부 클래스
    static class CCTV {
        int r, c, type;
        CCTV(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        
        board = new int[row][col];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                int n = Integer.parseInt(st.nextToken());
                board[i][j] = n;
                // CCTV를 발견하면 cctvList에 추가
                if (n >= 1 && n <= 5) {
                    cctvList.add(new CCTV(i, j, n));
                }
            }
        }
        
        // 0번째 CCTV부터 탐색을 시작하고, 초기 사무실 상태를 전달
        dfs(0, board);
        System.out.println(minBlindSpots);
    }


    static void dfs(int cctvIndex, int[][] map) {
        // [재귀 종료 조건] 모든 CCTV의 방향을 결정했다면
        if (cctvIndex == cctvList.size()) {
            // 현재 지도의 사각지대(0의 개수)를 계산
            int currentBlindSpots = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (map[i][j] == 0) {
                        currentBlindSpots++;
                    }
                }
            }
            // 기존 최솟값과 비교하여 갱신
            minBlindSpots = Math.min(minBlindSpots, currentBlindSpots);
            return;
        }

        // 현재 cctvIndex에 해당하는 CCTV 정보를 가져옴
        CCTV cctv = cctvList.get(cctvIndex);
        int r = cctv.r;
        int c = cctv.c;
        int type = cctv.type;

        // CCTV 종류에 따라 모든 회전 방향을 시도
        switch (type) {
            case 1: // 1번 CCTV: 4가지 방향 (단방향)
                for (int i = 0; i < 4; i++) {
                    // 다음 재귀로 넘겨주기 전에 현재 지도를 복사하여 사용
                    int[][] tempMap = copyMap(map);
                    watch(r, c, i, tempMap); // 한 방향 감시
                    dfs(cctvIndex + 1, tempMap); // 다음 CCTV로 넘어감
                }
                break;
            case 2: // 2번 CCTV: 2가지 방향 (180도 양방향)
                for (int i = 0; i < 2; i++) {
                    int[][] tempMap = copyMap(map);
                    watch(r, c, i, tempMap);     // i 방향
                    watch(r, c, i + 2, tempMap); // i의 반대 방향
                    dfs(cctvIndex + 1, tempMap);
                }
                break;
            case 3: // 3번 CCTV: 4가지 방향 (90도 양방향)
                for (int i = 0; i < 4; i++) {
                    int[][] tempMap = copyMap(map);
                    watch(r, c, i, tempMap);             // i 방향
                    watch(r, c, (i + 1) % 4, tempMap); // i의 90도 시계방향
                    dfs(cctvIndex + 1, tempMap);
                }
                break;
            case 4: // 4번 CCTV: 4가지 방향 (세 방향)
                for (int i = 0; i < 4; i++) {
                    int[][] tempMap = copyMap(map);
                    watch(r, c, i, tempMap);             // i 방향
                    watch(r, c, (i + 1) % 4, tempMap); // i의 90도 시계방향
                    watch(r, c, (i + 2) % 4, tempMap); // i의 180도 방향
                    dfs(cctvIndex + 1, tempMap);
                }
                break;
            case 5: // 5번 CCTV: 1가지 방향 (네 방향)
                int[][] tempMap = copyMap(map);
                watch(r, c, 0, tempMap); // 동
                watch(r, c, 1, tempMap); // 남
                watch(r, c, 2, tempMap); // 서
                watch(r, c, 3, tempMap); // 북
                dfs(cctvIndex + 1, tempMap);
                break;
        }
    }


    static void watch(int r, int c, int dir, int[][] map) {
        int nr = r;
        int nc = c;
        while (true) {
            nr += dx[dir];
            nc += dy[dir];

            // 지도 범위를 벗어나거나 벽(6)을 만나면 감시 중단
            if (nr < 0 || nc < 0 || nr >= row || nc >= col || map[nr][nc] == 6) break;

            // 빈 공간(0)은 감시 처리(-1). 다른 CCTV는 통과 가능
            if (map[nr][nc] == 0) {
                map[nr][nc] = -1; // -1을 감시된 영역으로 표시
            }
        }
    }

    
    static int[][] copyMap(int[][] original) {
        int[][] copied = new int[row][col];
        for (int i = 0; i < row; i++) {
            System.arraycopy(original[i], 0, copied[i], 0, col);
        }
        return copied;
    }
}