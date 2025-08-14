import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int [][] map = new int[N+1][N+1];
        boolean [][] visited= new boolean[N+1][N+1];
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            map[row][col]=1;
        }
        st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        Pair [] info = new Pair[L];
        for(int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            char angle = st.nextToken().charAt(0);
            info[i]=new Pair(sec, angle);
        }
        int d=0; //0 r 1 d 2 l 3 u
        int [] dx ={0,1,0,-1};
        int [] dy ={1,0,-1,0};
        int time = 0;
        int idx = 0; // info 배열 인덱스

        Queue<int[]> snake = new LinkedList<>();
        snake.add(new int[]{1, 1});
        visited[1][1] = true;

        int headX = 1, headY = 1;

        while (true) {
            time++;
            headX += dx[d];
            headY += dy[d];

            
            if (headX <= 0 || headX > N || headY <= 0 || headY > N || visited[headX][headY]) {
                System.out.println(time);
                return;
            }

            // 머리 이동
            snake.add(new int[]{headX, headY});
            visited[headX][headY] = true;

            // 사과가 없으면 꼬리 제거
            if (map[headX][headY] != 1) {
                int[] tail = snake.poll();
                visited[tail[0]][tail[1]] = false;
            } else {
                map[headX][headY] = 0; // 사과 먹었으면 제거
            }

            // 방향 전환 시점 확인
            if (idx < L && time == info[idx].sec) {
                if (info[idx].angle == 'D') { // 오른쪽 회전
                    d = (d + 1) % 4;
                } else if (info[idx].angle == 'L') { // 왼쪽 회전
                    d = (d + 3) % 4; 
                }
                idx++;
            }
        }

    }
    static class Pair{
        int sec;
        char angle;
        Pair(int sec,char angle){
            this.sec = sec;
            this.angle = angle;
        }
    }
}
