import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static class Pair implements Comparable<Pair>{
        int r,c,count;
        public Pair(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
        @Override
        public int compareTo(Pair o) {
            // TODO Auto-generated method stub
            return this.count-o.count;
        }
    }
    static boolean visited[][];
    static int board[][];
    static int R,C;
    static int [] dr = {-1,1,0,0};
    static int [] dc = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        visited = new boolean[R][C];
        for(int i=0; i<R; i++){
            String line = br.readLine().trim();
            for(int j=0; j<C; j++){
                board[i][j] = line.charAt(j) - '0';
            }
        }
        System.out.println(bfs());
    }

    static int bfs(){
        PriorityQueue<Pair> q = new PriorityQueue();
        visited[0][0]=true;
        q.offer(new Pair(0, 0, 0));
        
        while (!q.isEmpty()) {
            Pair cur =q.poll();
            int r = cur.r;
            int c= cur.c;
            if(r==R-1 && c==C-1) return cur.count;
            for(int d=0; d<4; d++){
                int nr= r+dr[d];
                int nc= c+dc[d];

                if(nr<0 || nc<0 || nr>=R || nc>=C || visited[nr][nc]) continue;
                visited[nr][nc] = true;
                if(board[nr][nc]==1){
                    q.offer(new Pair(nr, nc, cur.count+1 ));
                }
                else{
                    q.offer(new Pair(nr, nc, cur.count ));
                }
            }
            
        }
        return -1;
    }
}