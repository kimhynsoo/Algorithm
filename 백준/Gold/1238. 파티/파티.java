import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class  Main {
    // 간선 정보를 담을 클래스
    static class Edge implements Comparable<Edge>{
        int to, weight; // 도착 정점, 가중치(거리)

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight; // 가중치 오름차순 정렬 (우선순위 큐에서 사용)
        }
    }

    static int N, M, X;                 // N: 학생 수(노드 수), M: 도로 수(간선 수), X: 파티가 열리는 마을
    static ArrayList<Edge>[] adj;       // 정방향 그래프 (from → to)
    static ArrayList<Edge>[] reverse_adj; // 역방향 그래프 (to → from)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 그래프 초기화
        adj = new ArrayList[N+1];
        reverse_adj = new ArrayList[N+1];
        for(int i=1; i<=N; i++){
            adj[i] = new ArrayList<>();
            reverse_adj[i] = new ArrayList<>();
        }

        // 간선 입력 (단방향 도로)
        for(int i=0; i<M; i++){
            st  = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            adj[from].add(new Edge(to, weight));        // 정방향: from → to
            reverse_adj[to].add(new Edge(from, weight)); // 역방향: to → from
        }

        // X → i (정방향 그래프)
        int[] go = dijkstra(X, adj);
        // i → X (역방향 그래프, 즉 거꾸로 출발)
        int[] back = dijkstra(X, reverse_adj);

        // 각 학생의 왕복 최단 거리 계산
        int max = 0;
        for(int i=1; i<=N; i++){
            if(i==X) continue; // 자기 자신(X마을)은 제외

            int dist = go[i] + back[i]; // 왕복 거리
            max = Math.max(max, dist);  // 최댓값 갱신
        }

        // 결과 출력
        System.out.println(max);
    }

    // 다익스트라 알고리즘 (시작점에서 모든 정점까지의 최단 거리 구함)
    static int[] dijkstra(int start, ArrayList<Edge>[] list){
        boolean[] visited = new boolean[N+1];
        int[] minDist = new int[N+1];
        Arrays.fill(minDist, 1_000_000_000); // 충분히 큰 값으로 초기화
        minDist[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0)); // 시작점 추가

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            int to = cur.to;
            int weight = cur.weight;

            if(visited[to]) continue; // 이미 처리한 노드는 스킵
            visited[to] = true;

            // 현재 정점에서 이동할 수 있는 모든 간선 확인
            for(Edge next : list[to]){
                int nweight = weight + next.weight; // 새로운 거리
                if(!visited[next.to] && minDist[next.to] > nweight){
                    minDist[next.to] = nweight;    // 더 짧은 거리 갱신
                    pq.offer(new Edge(next.to, nweight));
                }
            }
        }

        return minDist;
    }
}
