import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    // 각 노드의 부모, 깊이, 방문 여부, 트리 구조를 저장할 배열
    static int[] parent;
    static int[] depth;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 노드의 개수 입력

        // 배열 초기화
        tree = new ArrayList[N+1]; // 인접 리스트 형태의 트리
        parent = new int[N+1];     // 각 노드의 부모 저장
        depth = new int[N+1];      // 각 노드의 깊이 저장
        visited = new boolean[N+1];// DFS 방문 체크

        StringTokenizer st;
        for(int i=1; i<=N; i++){
            tree[i] = new ArrayList<>();
        }

        // 간선 정보 입력 (양방향)
        for(int i=0; i<N-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
        }

        // 루트(1번 노드)부터 깊이와 부모 관계를 설정
        dfs(1, 0);

        // LCA 쿼리 처리
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(lca(a, b)).append("\n");
        }

        System.out.println(sb);
    }


    static void dfs(int node, int d){
        visited[node] = true;   // 방문 체크
        depth[node] = d;        // 깊이 기록

        for(int next : tree[node]){ // 인접 노드 순회
            if(!visited[next]){
                parent[next] = node; // 부모 정보 저장
                dfs(next, d + 1);    // 자식 노드 탐색 (깊이 +1)
            }
        }
    }


    static int lca(int a, int b){
        // 깊이가 더 깊은 노드를 a로 통일
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        // 깊이가 다를 경우, 깊이를 맞출 때까지 a를 부모로 끌어올림
        while (depth[a] != depth[b]) {
            a = parent[a];
        }

        // 깊이가 같아졌으면 동시에 부모를 따라 올라감
        // 둘이 같아지는 순간이 최소 공통 조상
        while(a != b){
            a = parent[a];
            b = parent[b];
        }

        return a;
    }
}
