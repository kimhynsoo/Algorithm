import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 간선 정보를 저장하는 클래스 (다익스트라용)
    static class Edge implements Comparable<Edge> {
        int to; // 도착 노드
        long weight; // 가중치 (거리)

        Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        // 우선순위 큐 정렬 기준: 거리(가중치)가 작은 순서대로
        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    static ArrayList<Edge>[] graph; // 그래프 인접 리스트
    static long[] minDist; // 각 도시까지의 최소 거리 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // N: 도시 개수, M: 도로 개수, K: 면접장 개수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 그래프 초기화 (도시 번호는 1부터 N까지 사용)
        graph = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // M개의 도로 입력
        // u → v 방향이 아니라 v → u 방향으로 저장해야 함
        // 면접장에서 출발해 도시로 가는 역방향 경로를 구하기 위해 반대로 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            graph[v].add(new Edge(u, c)); // 반대로 저장
        }

        // 면접장 입력
        st = new StringTokenizer(br.readLine());

        // 다익스트라 초기 세팅
        minDist = new long[N + 1];
        Arrays.fill(minDist, Long.MAX_VALUE); // 초기 거리: 무한대로 설정
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        // 여러 면접장을 모두 시작점으로 설정 (다중 시작점 다익스트라)
        for (int i = 0; i < K; i++) {
            int k = Integer.parseInt(st.nextToken());
            minDist[k] = 0; // 면접장은 거리 0
            pq.offer(new Edge(k, 0)); // 우선순위 큐에 삽입
        }

        // 다익스트라 알고리즘
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int to = cur.to;
            long weight = cur.weight;

            // 이미 더 짧은 거리로 방문한 적이 있으면 무시
            if (minDist[to] < weight)
                continue;

            // 현재 도시와 연결된 다른 도시들 탐색
            for (Edge next : graph[to]) {
                long nweight = weight + next.weight;
                // 더 짧은 경로를 발견하면 갱신
                if (minDist[next.to] > nweight) {
                    minDist[next.to] = nweight;
                    pq.offer(new Edge(next.to, nweight));
                }
            }
        }

        // 가장 멀리 떨어진 도시(= 최소 거리 중 최댓값) 찾기
        int max_dist_city = 1; // 도시 번호
        long max_dist = -1; // 거리

        for (int i = 1; i <= N; i++) {
            // 도달 불가능한 도시는 제외
            if (minDist[i] != Long.MAX_VALUE && minDist[i] > max_dist) {
                max_dist_city = i;
                max_dist = minDist[i];
            }
        }

        System.out.println(max_dist_city);
        System.out.println(max_dist);
    }
}
