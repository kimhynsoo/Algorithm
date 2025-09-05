import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int V,E;               // 정점 수 V, 간선 수 E
    static long[][] dist;          // 최단 거리 배열
    static final int INF = 1_000_000_000; // 도달 불가 표현

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine()); // 정점 수 입력
        E = Integer.parseInt(br.readLine()); // 간선 수 입력

        // dist 초기화
        dist = new long[V+1][V+1];           
        for(int i=1; i<=V; i++){
            Arrays.fill(dist[i], INF);       // 모든 거리를 INF로 초기화
            dist[i][i]=0;                    // 자기 자신까지 거리는 0
        }

        // 간선 정보 입력
        for(int i=0; i<E; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()); // 출발점
            int v = Integer.parseInt(st.nextToken()); // 도착점
            long w = Integer.parseInt(st.nextToken()); // 가중치
            dist[u][v] = Math.min(dist[u][v], w);                           // dist 배열에 저장
            // 만약 중복 간선이 있으면 dist[u][v] = Math.min(dist[u][v], w); 사용 가능
        }

        // 플로이드–와샬 핵심 3중 for문
        for(int k=1; k<=V; k++){           // 경유지 k
            for(int i=1; i<=V; i++){       // 출발점 i
                for(int j=1; j<=V; j++){   // 도착점 j
                    // i → k → j 경로가 존재하면
                    if(dist[i][k]!=INF && dist[k][j]!=INF){
                        // i → j 최단 거리 갱신
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // 출력
        
        for(int i=1; i<=V; i++){
            StringBuilder sb = new StringBuilder();
            for(int j=1; j<=V; j++){
                sb.append((dist[i][j]==INF)?0:dist[i][j]).append(" "); // 도달 불가 시 0 출력
            }
            System.out.println(sb);
            
        }
        
    }
}
