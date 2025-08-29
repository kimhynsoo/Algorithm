import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char board[][]; // 지도 상태 저장 ('.', 'S', 'D', '*', 'X')

    static Queue<Point> gosumList = new ArrayDeque<>(); // 고슴도치 이동 BFS 큐
    static Queue<Point> waterList = new ArrayDeque<>(); // 물 확산 BFS 큐
    static int R,C; // 행, 열 크기
    static boolean[][] visited; // 고슴도치 방문 체크
    static Point desPoint; // 비버의 굴 위치(D)
    
    // 상하좌우 방향
    static int dx[] = {-1,1,0,0}; 
    static int dy[] = {0,0,-1,1};
    
    // 좌표와 시간 정보를 담을 클래스
    static class Point{
        int x,y,time;
        Point(int x,int y,int time){
            this.x=x;
            this.y=y;
            this.time=time;
        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R=Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board=new char[R][C];
        visited = new boolean[R][C];
        
        // 지도 입력 처리
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                char c = line.charAt(j);
                board[i][j] = c;
                
                if(c == 'D'){ // 도착지 (비버의 굴)
                    desPoint = new Point(i, j, 0);
                }
                else if(c == 'S'){ // 고슴도치 시작 위치
                    gosumList.offer(new Point(i, j, 0));
                    visited[i][j]=true;
                }
                else if(c == '*'){ // 물 시작 위치
                    waterList.offer(new Point(i,j,0));
                }
            }
        }
        
        // BFS 실행
        bfs();
    }

    static void bfs(){
        // 고슴도치가 이동할 수 없을 때까지 반복
        while(!gosumList.isEmpty()){
            
            // 1. 먼저 물 확산 처리
            int w_size= waterList.size();
            for(int i=0; i<w_size; i++){
                Point cur = waterList.poll();
                int x = cur.x;
                int y = cur.y;
                
                for(int j=0; j<4; j++){
                    int nx = x+dx[j];
                    int ny = y+dy[j];
                    
                    // 범위를 벗어나면 continue
                    if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
                    
                    // 물이 퍼질 수 있는 칸은 빈칸('.') 또는 고슴도치 위치('S')
                    if(board[nx][ny] =='.' || board[nx][ny] =='S'){
                        board[nx][ny] = '*'; // 물로 채움
                        waterList.offer(new Point(nx, ny, 0));
                    }
                }
            }

            // 2. 고슴도치 이동 처리
            int g_size= gosumList.size();
            for(int i=0; i<g_size; i++){
                Point cur = gosumList.poll();
                int x = cur.x;
                int y = cur.y;

                // 만약 도착지에 도착했다면 시간 출력 후 종료
                if(desPoint.x ==x && desPoint.y ==y){
                    System.out.println(cur.time);
                    return;
                }

                // 4방향 탐색
                for(int j=0; j<4; j++){
                    int nx = x+dx[j];
                    int ny = y+dy[j];
                    
                    // 범위 밖이거나 이미 방문한 칸이면 skip
                    if(nx<0 || ny<0 || nx>=R || ny>=C || visited[nx][ny]) continue;
                    
                    // 물('*')이나 돌('X')은 이동 불가
                    if(board[nx][ny] =='*' || board[nx][ny] =='X') continue;
                    
                    // 방문 처리 후 큐에 추가 (시간 +1)
                    visited[nx][ny] = true;
                    gosumList.offer(new Point(nx, ny, cur.time+1));
                }
            }
        }
        // 도착지까지 가지 못한 경우
        System.out.println("KAKTUS");
    }
}
