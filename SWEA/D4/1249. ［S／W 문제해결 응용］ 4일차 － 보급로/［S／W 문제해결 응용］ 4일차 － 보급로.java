import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {
    // PriorityQueue에서 사용될 Point 클래스: 좌표(r, c)와 시작점으로부터의 거리(dist)
    static class Point implements Comparable<Point>{
        int r, c, dist;

        public Point(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        // PriorityQueue에서 dist 기준 오름차순 정렬
        @Override
        public int compareTo(Point o) {
            return this.dist - o.dist;
        }
    }

    static int[][] board;      // 입력 맵: 각 칸의 이동 비용
    static int N;              // 맵 크기 N x N
    static int[][] minDist;    // 시작점부터 각 칸까지 최소 거리 저장
    static final int INF = Integer.MAX_VALUE; // 초기값으로 무한대 설정
    static int[] dx = {-1,1,0,0}; // 상하좌우 이동용
    static int[] dy = {0,0,-1,1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine()); // 맵 크기
            board = new int[N][N]; // 입력 맵 초기화
            minDist = new int[N][N]; // 최소 거리 배열 초기화

            for(int i=0; i<N; i++){
                String line = br.readLine(); // 한 줄 입력
                for(int j=0; j<N; j++){
                    board[i][j] = line.charAt(j) - '0'; // 문자 -> 정수 변환
                    minDist[i][j] = INF;               // 초기값 무한대로 설정
                }
            }
            minDist[0][0] = 0; // 시작점 최단 거리 0

            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(dijkstra(0, 0)); // 다익스트라 수행
            System.out.println(sb);
        }
    }

    static int dijkstra(int r, int c){
        PriorityQueue<Point> pq = new PriorityQueue<>(); // 거리 기준 우선순위 큐
        pq.offer(new Point(r, c, 0)); // 시작점 삽입

        while(!pq.isEmpty()){
            Point cur = pq.poll(); // 현재 최단 거리 칸 선택
            int x = cur.r;
            int y = cur.c;
            int dist = cur.dist;

            if(x == N-1 && y == N-1){ // 목표 지점 도착하면 최단 거리 반환
                return dist;
            }

            // 상하좌우 탐색
            for(int d=0; d<4; d++){
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx<0 || ny<0 || nx>=N || ny>=N) continue; // 범위 벗어나면 패스

                int ndist = dist + board[nx][ny]; // 현재 거리 + 이동 비용
                if(minDist[nx][ny] > ndist){      // 더 짧은 경로 발견 시
                    minDist[nx][ny] = ndist;      // 거리 갱신
                    pq.offer(new Point(nx, ny, ndist)); // 우선순위 큐에 삽입
                }
            }
        }

        return -1; // 목표 지점 도달 불가 시
    }
}
