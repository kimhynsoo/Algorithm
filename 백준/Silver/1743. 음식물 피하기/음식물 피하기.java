import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static int map[][];
    static boolean visited[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new int[R+1][C+1];
        visited = new boolean[R+1][C+1];
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c]=1;

        }
        
        for(int i=1; i<=R; i++){
            for(int j=1; j<=C; j++){
                if(map[i][j]==1 && !visited[i][j]){
                    bfs(i,j);
                }
            }
        }
        System.out.println(max);
        
    }
    static int max=0;
    static int []dr = new int[]{-1,1,0,0};
    static int []dc = new int[]{0,0,-1,1};

    static void bfs(int r, int c){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});
        visited[r][c] = true;
        int cnt=0;
        while(!q.isEmpty()){
            cnt++;
            int cur[] = q.poll();
            for(int d=0; d<4; d++){
                int nr = cur[0]+dr[d];
                int nc = cur[1]+dc[d];

                if(nr<=0 || nc<=0 || nr>R || nc>C || visited[nr][nc] || map[nr][nc]==0){
                    continue;
                }
                visited[nr][nc] = true;
                q.offer(new int[]{nr,nc});
            }

        }
        if(cnt>max){
            max = cnt;
        }
    }
}