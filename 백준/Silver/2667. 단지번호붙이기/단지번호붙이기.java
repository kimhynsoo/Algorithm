import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    // 지도의 크기
    static int N;
    // 2차원 지도 배열
    static int[][] board;
    // 방문 체크 배열
    static boolean[][] visited;
    // 단지 개수
    static int cnt = 0;
    // 현재 단지 내 집 수
    static int scnt = 0;
    // 단지 내 집 수를 오름차순 정렬하기 위한 우선순위 큐
    static PriorityQueue<Integer> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException, NumberFormatException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지도 크기 입력
        N = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        // 지도 입력
        for(int i = 0; i < N; i++){
            String[] line = br.readLine().split(""); // 한 줄을 문자 단위로 쪼갬
            for(int j = 0; j < N; j++){
                // '0'과 '1'을 int로 변환
                board[i][j] = line[j].charAt(0) - '0';
            }
        }

        visited = new boolean[N][N];

        // 모든 좌표 탐색
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                // 집이 있고 방문하지 않은 경우
                if(board[i][j] == 1 && !visited[i][j]){
                    dfs(i, j);          // DFS 탐색 시작
                    pq.offer(scnt);     // 단지 내 집 수 저장
                    scnt = 0;           // 다음 단지를 위해 초기화
                    cnt++;              // 단지 개수 증가
                }
            }
        }

        // 결과 출력
        System.out.println(cnt);
        while(!pq.isEmpty()){
            System.out.println(pq.poll()); // 단지 내 집 수 오름차순 출력
        }
    }

    // 상, 하, 좌, 우 이동
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    // DFS 탐색
    static void dfs(int r, int c){
        visited[r][c] = true; // 방문 처리
        scnt++;               // 단지 내 집 수 증가

        // 상하좌우 탐색
        for(int i = 0; i < 4; i++){
            int nx = r + dx[i];
            int ny = c + dy[i];

            // 경계 체크 및 방문 여부 확인
            if(nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) continue;

            // 집이 있는 경우 DFS 재귀 호출
            if(board[nx][ny] == 1){
                dfs(nx, ny);
            }
        }
    }
}
