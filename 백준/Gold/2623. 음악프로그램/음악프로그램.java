import java.io.*;
import java.util.*;

public class Main {
    static int N, M; // N: 가수 수, M: 보조 PD 수
    static int[] inDegree; // 각 노드(가수)의 진입 차수
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); // 인접 리스트
    static ArrayList<Integer> result = new ArrayList<>(); // 위상 정렬 결과 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력: 가수 수, 보조 PD 수
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화 (1번 ~ N번 가수 사용)
        for(int i=0; i<=N; i++){
            adj.add(new ArrayList<Integer>());
        }

        inDegree = new int[N+1]; // 진입 차수 배열

        // 각 보조 PD의 순서 정보 입력
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken()); // 해당 PD가 담당하는 가수 수

            // 첫 두 가수 정보 입력
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj.get(from).add(to); // from → to 순서
            inDegree[to]++;        // to의 진입 차수 증가

            // 나머지 가수들 연결
            for(int j=0; j<k-2 ; j++ ){
                from = to;
                to = Integer.parseInt(st.nextToken());
                adj.get(from).add(to);
                inDegree[to]++;
            }
        }

        // 위상 정렬 수행
        sort();
    }

    static void sort(){
        Queue<Integer> q = new ArrayDeque<>();

        // 진입 차수가 0인 노드(가수) 큐에 삽입
        for(int i=1; i<=N; i++){
            if(inDegree[i]==0){
                q.offer(i);
            }
        }

        // BFS 기반 위상 정렬
        while (!q.isEmpty()) {
            int cur = q.poll();
            result.add(cur); // 순서에 추가

            // 현재 노드와 연결된 노드들의 진입 차수 감소
            for(int next : adj.get(cur)){
                if(--inDegree[next] == 0){ // 진입 차수가 0이 되면 큐에 삽입
                    q.offer(next);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        // 모든 노드를 방문했으면 순서 출력
        if(result.size() == N){
            for(int n : result){
                sb.append(n).append("\n");
            }
        } else { 
            // 사이클 발생(모든 가수를 나열할 수 없는 경우)
            sb.append(0);
        }
        System.out.println(sb);
    }
}
