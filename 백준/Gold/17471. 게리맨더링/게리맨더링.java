import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;                                 // 구역 개수
    static int[] people;                          // 각 구역의 인구 수
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); // 인접 리스트 (그래프)
    static boolean[] selected;                    // 부분집합 선택 배열 (true → A 선거구, false → B 선거구)
    static int answer = Integer.MAX_VALUE;        // 최소 인구 차이 저장

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 구역 개수 입력
        N = Integer.parseInt(br.readLine());
        people = new int[N+1]; // 1-based index

        // 각 구역의 인구 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        // 인접 리스트 초기화
        for (int i=0; i<=N; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // 각 구역의 연결 정보 입력
        // i번째 줄 → i 구역과 연결된 구역들의 번호
        for (int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine());
            int j = Integer.parseInt(st.nextToken()); // i 구역과 연결된 구역의 개수
            for (int k=0; k<j; k++) {
                adj.get(i).add(Integer.parseInt(st.nextToken()));
            }
        }

        selected = new boolean[N+1]; // 선거구 나누기용 boolean 배열
        subset(1);                   // 부분집합 탐색 시작 (1번 구역부터)

        // 최종 출력: 불가능하면 -1
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // 부분집합 탐색 (모든 구역을 두 선거구로 나누기)
    static void subset(int idx) {
        if (idx == N+1) { // 모든 구역을 선택 완료한 경우
            List<Integer> A = new ArrayList<>(); // A 선거구
            List<Integer> B = new ArrayList<>(); // B 선거구

            // selected 배열을 보고 A/B 그룹 분류
            for (int i=1; i<=N; i++) {
                if (selected[i]) A.add(i);
                else B.add(i);
            }

            // 둘 중 하나라도 공집합이면 무효
            if (A.isEmpty() || B.isEmpty()) return;

            // 두 그룹이 모두 연결되어 있는지 검사
            if (isConnected(A) && isConnected(B)) {
                int sumA = 0, sumB = 0;
                for (int x : A) sumA += people[x]; // A 구역 인구 합
                for (int x : B) sumB += people[x]; // B 구역 인구 합
                // 인구 차이 최소값 갱신
                answer = Math.min(answer, Math.abs(sumA - sumB));
            }
            return;
        }

        // 현재 idx 구역을 A 선거구에 포함
        selected[idx] = true;
        subset(idx+1);

        // 현재 idx 구역을 B 선거구에 포함
        selected[idx] = false;
        subset(idx+1);
    }

    // 특정 그룹이 연결되어 있는지 확인 (BFS)
    static boolean isConnected(List<Integer> group) {
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();

        // 그룹 내 첫 번째 구역에서 BFS 시작
        q.offer(group.get(0));
        visited[group.get(0)] = true;
        int cnt = 1; // 방문한 구역 수

        while (!q.isEmpty()) {
            int cur = q.poll();
            // 현재 구역과 연결된 구역들 탐색
            for (int i : adj.get(cur)) {
                // 방문하지 않았고, 같은 그룹에 속해 있으면 탐색
                if (!visited[i] && group.contains(i)) {
                    visited[i] = true;
                    cnt++;
                    q.offer(i);
                }
            }
        }

        // 방문한 구역 수가 그룹 크기와 같으면 연결되어 있음
        return cnt == group.size();
    }
}
