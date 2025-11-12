import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 구슬 개수
        int M = Integer.parseInt(st.nextToken()); // 비교 횟수
        int mid = (N + 1) / 2; // 가운데보다 많은 구슬 수 기준
        boolean[][] graph = new boolean[N + 1][N + 1]; // graph[a][b] = true → a가 b보다 무겁다

        // 입력으로 주어진 비교 관계 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int big = Integer.parseInt(st.nextToken()); // 더 무거운 구슬
            int small = Integer.parseInt(st.nextToken()); // 더 가벼운 구슬
            graph[big][small] = true; // 방향 그래프
        }

        // 플로이드-워셜 알고리즘으로 간접 비교 관계 확장
        
        for (int k = 1; k <= N; k++) { // 중간 노드 (경유지)
            for (int i = 1; i <= N; i++) { // 출발 노드
                for (int j = 1; j <= N; j++) { // 도착 노드
                    // i가 k보다 무겁고, k가 j보다 무거우면 i는 j보다 무겁다
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        int result = 0; // 가운데 구슬이 될 수 없는 구슬의 수

        // 각 구슬 i에 대해 무거운 구슬/가벼운 구슬 개수 세기
        for (int i = 1; i <= N; i++) {
            int heavy = 0; // i보다 가벼운 구슬 수
            int light = 0; // i보다 무거운 구슬 수

            for (int j = 1; j <= N; j++) {
                // i → j 경로가 존재한다면 i가 j보다 무겁다
                if (graph[i][j])
                    heavy++;
                // j → i 경로가 존재한다면 j가 i보다 무겁다
                if (graph[j][i])
                    light++;
            }

            // 절반 이상보다 무겁거나 가벼우면 가운데 구슬이 될 수 없음
            if (heavy >= mid || light >= mid) {
                result++;
            }
        }

        // 결과 출력
        System.out.println(result);
    }
}
