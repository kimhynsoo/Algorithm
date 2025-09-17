import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class boj_16946_벽뿌4 {
    // 지도 크기
    static int rows, cols;

    // grid: 입력 맵 (0 = 빈칸, 1 = 벽)
    static int [][] grid;

    // regionId: 각 빈칸이 속한 영역 번호를 저장
    static int [][] regionId;

    // 상하좌우 이동 좌표
    static int [] dRow = {-1, 1, 0, 0};
    static int [] dCol = {0, 0, -1, 1};

    // BFS로 부여할 영역 번호 시작 값
    static int regionCounter = 1;

    // 각 영역 번호별 크기 저장
    static Map<Integer, Integer> regionSizeMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지도 크기 입력
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());

        grid = new int[rows][cols];
        regionId = new int[rows][cols];

        // 지도 입력
        for (int i = 0; i < rows; i++) {
            String line = br.readLine();
            for (int j = 0; j < cols; j++) {
                grid[i][j] = line.charAt(j) - '0'; // '0' -> 0, '1' -> 1
            }
        }

        StringBuilder sb = new StringBuilder();

        // 1️⃣ BFS로 모든 빈칸 영역 번호 붙이기
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 아직 방문하지 않은 빈칸이면 BFS 실행
                if (grid[i][j] == 0 && regionId[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        // 2️⃣ 벽 칸 계산: 인접 영역 크기 합산
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    Set<Integer> adjacentRegions = new HashSet<>();
                    int cellValue = 1; // 벽을 부수고 들어갈 수 있는 칸 수 시작 값

                    // 상하좌우 4방향 탐색
                    for (int d = 0; d < 4; d++) {
                        int nextRow = i + dRow[d];
                        int nextCol = j + dCol[d];

                        // 범위 벗어나면 무시
                        if (nextRow < 0 || nextCol < 0 || nextRow >= rows || nextCol >= cols) continue;

                        // 인접한 칸이 빈칸이면, 그 칸의 영역 번호 추가
                        // ⚠ grid 기준으로 하면 안됨! 이미 이전 벽 계산으로 0~9로 바뀌었을 수 있음
                        if (regionId[nextRow][nextCol]!= 0) {
                            adjacentRegions.add(regionId[nextRow][nextCol]);
                        }
                    }

                    // 중복 없는 영역 번호 집합으로 총 칸 수 계산
                    for (int region : adjacentRegions) {
                        cellValue += regionSizeMap.get(region);
                    }

                    // 최종 값은 10으로 나눈 나머지
                    grid[i][j] = cellValue % 10;
                }

                // 결과 문자열에 추가
                sb.append(grid[i][j]);
            }
            sb.append("\n");
        }

        // 결과 출력
        System.out.println(sb.toString());
    }

    // BFS: 빈칸 영역을 탐색하고 영역 번호 부여
    static void bfs(int startRow, int startCol) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{startRow, startCol});

        // 시작 칸 영역 번호 부여
        regionId[startRow][startCol] = regionCounter;
        int size = 1; // 영역 크기 초기화

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];

            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {
                int nextRow = curRow + dRow[d];
                int nextCol = curCol + dCol[d];

                // 범위 벗어나거나, 이미 영역 번호 부여된 칸, 벽이면 스킵
                if (nextRow < 0 || nextCol < 0 || nextRow >= rows || nextCol >= cols
                        || regionId[nextRow][nextCol] != 0 || grid[nextRow][nextCol] == 1) {
                    continue;
                }

                // 영역 번호 부여 후 큐에 추가
                regionId[nextRow][nextCol] = regionCounter;
                queue.offer(new int[]{nextRow, nextCol});
                size++;
            }
        }

        // 영역 번호별 크기 저장
        regionSizeMap.put(regionCounter, size);
        regionCounter++; // 다음 영역 번호 준비
    }
}
