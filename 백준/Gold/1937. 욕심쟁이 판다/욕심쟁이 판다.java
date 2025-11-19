import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int[][] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        dp = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max=0;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.println(max);

    }

    static int[] dr = { -1, 1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    static int dfs(int r, int c) {
        if (dp[r][c] != 0)
            return dp[r][c];

        dp[r][c]=1;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr < 0 || nc < 0 || nr >= N || nc >= N || graph[nr][nc] <= graph[r][c])
                continue;

            dp[r][c] = Math.max(dfs(nr, nc) + 1, dp[r][c]);
        }
        return dp[r][c];
    }
}
