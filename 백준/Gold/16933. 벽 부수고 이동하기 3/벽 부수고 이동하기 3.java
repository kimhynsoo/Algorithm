import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N,M,K;
    static int [][] graph;              // 지도 정보 (0: 빈 칸, 1: 벽)
    static boolean [][][] visited;      // 방문 여부 [행][열][남은 벽 부술 수 있는 횟수]

    // BFS에서 사용할 상태 클래스
    static class Pair {
        int r,c,k;      // 좌표 (r,c), 남은 벽 부술 수 있는 횟수 k
        int cnt;        // 현재까지 이동한 시간(거리)
        int flag;       // 낮/밤 구분 (1: 낮, -1: 밤)

        public Pair(int r, int c, int k, int flag, int cnt) {
            this.r = r;
            this.c = c;
            this.k = k;
            this.cnt = cnt;
            this.flag = flag;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 처리
        N = Integer.parseInt(st.nextToken()); // 행
        M = Integer.parseInt(st.nextToken()); // 열
        K = Integer.parseInt(st.nextToken()); // 벽을 부술 수 있는 최대 횟수

        graph = new int[N][M];
        visited = new boolean[N][M][K+1];

        // 지도 입력
        for(int i=0; i<N; i++){
            String line = br.readLine();
            for(int j=0; j<M; j++){
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        // BFS 실행 및 결과 출력
        System.out.println(bfs());
    }

    // 상하좌우 이동
    static int [] dr = {-1,1,0,0};
    static int [] dc = {0,0,-1,1};

    static int bfs(){
        Queue<Pair> q = new ArrayDeque<>();

        // 시작점 (0,0)에서 시작, 벽 K번 부술 수 있음, 낮(flag=1), 시간 cnt=1
        q.offer(new Pair(0, 0, K, 1, 1));
        visited[0][0][K] = true;

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int k = cur.k;
            int cnt = cur.cnt;
            int flag = cur.flag;

            // 도착지 (N-1, M-1)에 도착하면 시간 반환
            if(r == N-1 && c == M-1) return cnt;

            // 네 방향으로 이동 시도
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];

                // 범위 밖이거나 이미 방문한 상태면 패스
                if(nr < 0 || nc < 0 || nr >= N || nc >= M || visited[nr][nc][k]) continue;

                // 벽을 만난 경우
                if(graph[nr][nc] == 1){
                    if(k > 0 && !visited[nr][nc][k-1]){ // 부술 기회가 남아 있다면
                        if(flag == -1){ 
                            // 밤이면 벽을 바로 부술 수 없으므로
                            // 현재 위치에서 기다려서 낮이 된 후 다시 시도
                            q.offer(new Pair(r, c, k, -flag, cnt+1));
                        } else {
                            // 낮이면 벽 부수고 이동
                            visited[nr][nc][k-1] = true;
                            q.offer(new Pair(nr, nc, k-1, -flag, cnt+1));
                        }
                    }
                }
                // 빈 칸인 경우
                else {
                    visited[nr][nc][k] = true;
                    q.offer(new Pair(nr, nc, k, -flag, cnt+1));
                }
            }
        }
        // 도착할 수 없는 경우
        return -1;
    }
}
