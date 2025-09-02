import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V,E,start;   //V : 정점 개수, E : 간선 개수, start : 시작 정점
    static int[] minDist;   // 출발 정점 기준 최단 거리 저장 배열
    static boolean[] visited; // 방문 체크 배열
    static ArrayList<Edge>[] graph ; //인접리스트 (그래프)
    static final int INF = Integer.MAX_VALUE; //연결되지 않은 정점 사이의 거리
    static class Edge implements Comparable<Edge>{
        // 간선을 표현하는 클래스
        // 'to' : 도착 정점 번호, 'weight' : 가중치(거리, 비용)
        int to,weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
        // PriorityQueue에서 최소 가중치 기준으로 정렬되도록 설정
        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());          
        E = Integer.parseInt(st.nextToken());   
        start = Integer.parseInt(br.readLine());
        // 그래프 초기화 (정점 번호를 1번부터 사용하므로 V+1 크기)
        graph =  new ArrayList[V+1];
        for(int i=1; i<=V; i++){
            graph[i] = new ArrayList<>();
        }
        // 간선 정보 입력 (단방향 그래프)
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, weight));
        }
        minDist = new int[V+1];
        visited = new boolean[V+1];
        
        
        Arrays.fill(minDist, INF);
        minDist[start]=0;
        
        dijkstra(start);

    }

    static void dijkstra(int start){
        // 우선순위 큐를 이용하여 최소 비용 간선을 항상 먼저 선택
        PriorityQueue<Edge> pq = new PriorityQueue<>(); 
        // 시작 정점의 최단 거리는 0으로 설정하고 큐에 삽입
        pq.offer(new Edge(start, 0));            
        int cnt =0;
        // 큐가 빌 때까지 반복 (방문하지 않은 정점 중 최단 거리 선택)
        while (!pq.isEmpty()) {
            // 현재 최소 비용의 간선을 꺼냄
            Edge cur = pq.poll(); 
            int to = cur.to;  // 현재 간선이 향하는 정점
            int weight = cur.weight;

            if(minDist[to]< weight) continue;
            
            // 이미 방문한 정점이면 무시
            if(visited[to]) continue;   
            visited[to] = true;  // 방문 처리
            cnt++;
            if(cnt==V) break; 

            // 현재 정점과 연결된 모든 인접 간선 확인
            for(Edge next : graph[to]){
                int nextTo = next.to;
                int nextWeight =  weight + next.weight;
                // 아직 방문하지 않았고, 현재 정점을 거쳐가는 거리가 기존 최단거리보다 짧으면 갱신
                if(!visited[next.to] && minDist[next.to] > nextWeight){
                    minDist[next.to] = nextWeight; // 최단 거리 갱신
                    pq.offer(new Edge(nextTo, nextWeight)); // 갱신된 정점을 큐에 추가
                }
            }
        }

        // 최종 최단 거리 출력
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= V; i++){
            if(minDist[i] == INF){
                sb.append("INF").append("\n"); // 도달 불가 정점
            } else {
                sb.append(minDist[i]).append("\n"); // 최단 거리 출력
            }
        }
        System.out.println(sb);
    }

}
