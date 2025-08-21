import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int V, E;                         // 정점(학생 수), 간선(비교 횟수)
    static int[] inDegree;                   // 각 정점의 진입 차수 저장
    static ArrayList<ArrayList<Integer>> adj = new ArrayList<>(); // 인접 리스트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정점 개수 V, 간선 개수 E 입력
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        inDegree = new int[V+1]; // 1번 학생부터 V번 학생까지 사용

        // 인접 리스트 초기화
        for (int i = 0; i <= V; i++) {
            adj.add(new ArrayList<Integer>());
        }

        // 간선 입력 (from -> to)
        // "from 학생은 to 학생보다 먼저 서야 한다"는 의미
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adj.get(from).add(to);  // from → to 간선 추가
            inDegree[to]++;         // to의 진입 차수 증가
        }

        // 위상 정렬 실행
        sort();
    }

    static void sort() {
        Queue<Integer> q = new ArrayDeque<>();  // 진입 차수 0인 정점을 담을 큐
        StringBuilder sb = new StringBuilder(); // 결과 저장용

        // 처음에 진입 차수가 0인 정점들을 큐에 넣음
        for (int i = 1; i <= V; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }

        // 큐가 빌 때까지 반복
        while (!q.isEmpty()) {
            int cur = q.poll();       // 현재 꺼낸 학생
            sb.append(cur).append(" "); // 결과에 추가

            // 현재 정점에서 나가는 간선 제거
            for (int next : adj.get(cur)) {
                if (--inDegree[next] == 0) { // 간선을 제거한 후 진입 차수가 0이 되면
                    q.offer(next);           // 큐에 추가
                }
            }
        }

        // 결과 출력
        System.out.println(sb);
    }
}
