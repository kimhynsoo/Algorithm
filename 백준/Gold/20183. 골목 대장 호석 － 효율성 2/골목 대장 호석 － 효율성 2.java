
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static long C; // 사용할 수 있는 최대 비용
    static int N, M, A, B;
    static ArrayList<Edge>[] adj;

    // 간선 정보 (도착 노드, 비용) - PQ 정렬 기준을 위해 Comparable 구현
    static class Edge implements Comparable<Edge> {
        int to;
        long cost;

        public Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    static long money[]; // A → 각 노드까지 소요되는 비용 저장 (Dijkstra 사용)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 노드 수
        M = Integer.parseInt(st.nextToken()); // 간선 수
        A = Integer.parseInt(st.nextToken()); // 출발 지점
        B = Integer.parseInt(st.nextToken()); // 도착 지점
        C = Long.parseLong(st.nextToken()); // 총 예산 (최대 비용)

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++)
            adj[i] = new ArrayList<>();

        long max = 0; // 간선 중 가장 큰 비용 저장 → 이분 탐색 상한값
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Long.parseLong(st.nextToken());
            max = Math.max(max, cost);
            adj[from].add(new Edge(to, cost));
            adj[to].add(new Edge(from, cost)); // 양방향
        }

        money = new long[N + 1];
        long left = 1, right = max;
        long ans = -1;

        // "최대 단일 간선 비용"을 이분 탐색
        // → 해당 limit 이하만 사용하여 목적지까지 C 예산 안에 도달 가능한지 확인
        while (left <= right) {
            long mid = (left + right) / 2;
            Arrays.fill(money, Long.MAX_VALUE);
            money[A] = 0;
            dijkstra(A, B, mid);

            if (money[B] <= C) { // 도달 가능하면 limit 더 줄여본다
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1; // 불가능하면 limit 늘려야 함
            }
        }
        System.out.println(ans);
    }

    // Dijkstra: 단일 간선비용이 limit 이하인 경로만 고려하면서, 총비용은 C 이하인지 탐색
    static void dijkstra(int start, int end, long limit) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int to = cur.to;
            long cost = cur.cost;

            if (money[to] < cost)
                continue; // 이미 더 작은 비용으로 방문됨
            if (to == end)
                return; // 목적지 도달 시 종료

            for (Edge next : adj[to]) {
                if (next.cost > limit)
                    continue; // 단일 간선 비용 제한 초과
                long nextCost = money[to] + next.cost;
                if (nextCost > C)
                    continue; // 총 비용 제한 초과
                if (money[next.to] > nextCost) { // 더 적은 비용이라면 업데이트
                    money[next.to] = nextCost;
                    pq.offer(new Edge(next.to, nextCost));
                }
            }
        }
    }
}
