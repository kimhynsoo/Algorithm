import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987_654_321;
    static int N;
    static int [][]dist;
    static int [][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dist = new int[N][N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                dist[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int minDist = solve();

        System.out.println(minDist);


        
    }
    static int solve(){
        // dp 테이블 초기화. dp[현재도시][방문상태]
        // 방문상태는 2^N 가지의 경우의 수가 있음.
        dp = new int[N][1<<N];
        for(int i=0; i<N; i++){
            //-1은 아직 해당 상태를 계산하지 않았음을 의미
            Arrays.fill(dp[i], -1);
        }

        return findPath(0,1);
    }

    static int findPath(int current, int mask){
        // 1.기저 사례 : 모든 도시를 방문한 경우
        if(mask == (1<<N)-1){
            // 현재 도시에서 출발 도시(0번)로 돌아갈 수 있다면 거리를 반환
            return dist[current][0] == 0 ? INF : dist[current][0];
        }

        // 2. 메모제이션 : 이미 계산한 하위 문제인 경우
        if (dp[current][mask] != -1){
            return dp[current][mask];
        }

        // 3. 재귀 단계 : 현재 상태의 최소 비용 계산
        // 현재 상태(current, mask)의 최소 비용을 INF로 초기화
        dp[current][mask] = INF;

        // 다음으로 방문할 도시(next)를 탐색
        for(int next =0; next<N; next++){
            // 만약 next 도시를 아직 방문하지 않았고 next 도시로 갈 수 있다면
            if((mask & (1<<next))==0 && dist[current][next]!=0){
                // (현재 -> 다음 도시 비용) + (다음 도시부터 나머지 경로의 최소 비용)
                int newCost = dist[current][next] + findPath(next, mask | (1<<next));

                //기존에 계산된 최소 비용과 새로 계산된 비용을 비교하여 더 작은 값으로 갱신
                dp[current][mask] = Math.min(dp[current][mask], newCost);
            }
        }

        return dp[current][mask];


    }
}
