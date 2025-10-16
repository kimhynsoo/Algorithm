import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Taxi{
        int r,c,oil;
        Taxi(int r,int c,int oil){
            this.r = r;
            this.c = c;
            this.oil = oil;
        }
    }

    static class Customer{
        int num,r,c,d_r,d_c;
        boolean status=false;

        public Customer(int num,int r, int c, int d_r, int d_c) {
            this.num=num;
            this.r = r;
            this.c = c;
            this.d_r = d_r;
            this.d_c = d_c;
        }
        
    }
    static int N,M,b_oil;
    static int [][] map;
    static Customer[] infos;
    static Taxi taxi;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        b_oil = Integer.parseInt(st.nextToken());
        map = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        int t_r = Integer.parseInt(st.nextToken());
        int t_c = Integer.parseInt(st.nextToken());
        taxi = new Taxi(t_r, t_c, b_oil);

        infos = new Customer[M+1];
        for(int i=1; i<=M;i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d_r = Integer.parseInt(st.nextToken());
            int d_c = Integer.parseInt(st.nextToken());
            infos[i]=new Customer(i,r, c, d_r, d_c);
        }
        for (int i = 0; i < M; i++) {
            int idx = bfsToFindCustomer(); // 가장 가까운 손님 찾기
            if (idx == -1) { // 손님 못 찾거나 연료 부족
                System.out.println(-1);
                return;
            }

            boolean success = moveToDestination(idx); // 목적지 이동
            if (!success) { // 도중 실패
                System.out.println(-1);
                return;
            }
        }
        
        System.out.println(taxi.oil);
    }
    static boolean visited [][];
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};
    static int result=0;
    static int bfsToFindCustomer() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{taxi.r, taxi.c, 0});
        visited = new boolean[N+1][N+1];
        visited[taxi.r][taxi.c] = true;

        int minDist = Integer.MAX_VALUE;
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;
        int minIdx = -1;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            // 이미 최소 거리보다 길면 멈춤
            if (dist > minDist) break;

            for (int i = 1; i <= M; i++) {
                Customer cus = infos[i];
                if (!cus.status && cus.r == r && cus.c == c) {
                    if (dist < minDist ||
                        (dist == minDist && (r < minR || (r == minR && c < minC)))) {
                        minDist = dist;
                        minR = r;
                        minC = c;
                        minIdx = i;
                    }
                }
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
                if (visited[nr][nc] || map[nr][nc] == 1) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, dist + 1});
            }
        }

        if (minIdx == -1) return -1; // 손님 없음
        if (minDist > taxi.oil) return -1; // 연료 부족

        // 손님 태우기
        taxi.oil -= minDist;
        taxi.r = minR;
        taxi.c = minC;
        return minIdx;
    }

    static boolean moveToDestination(int idx) {
        Customer cus = infos[idx];
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{taxi.r, taxi.c, 0});
        visited = new boolean[N+1][N+1];
        visited[taxi.r][taxi.c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dist = cur[2];
            if (r == cus.d_r && c == cus.d_c) {
                if (dist > taxi.oil) return false; // 연료 부족
                taxi.oil += dist; // 이동거리 * 2 (감소분 + 보충분)
                taxi.r = r;
                taxi.c = c;
                cus.status = true;
                return true;
            }

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 1 || nc < 1 || nr > N || nc > N) continue;
                if (visited[nr][nc] || map[nr][nc] == 1) continue;
                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc, dist + 1});
            }
        }
        return false;
    }

}