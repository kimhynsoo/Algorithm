import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int R,C;
    static boolean [][] visited;
    static int [][] map;
    static int [][] regionId;
    static Map<Integer, Integer> regionSize;
    static int dr[] = {0,-1,0,1};
    static int dc[] = {-1,0,1,0};
    static int mask[] = {1,2,4,8};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        regionId = new int[R][C];
        regionSize = new HashMap<>();

        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt=0;
        visited = new boolean[R][C];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(!visited[i][j]){
                    cnt++;
                    bfs(i, j,cnt);
                }
            }
        }

        visited = new boolean[R][C];
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(!visited[i][j]){
                    visited[i][j]=true;
                    max_find(i, j);
                }
            }
        }
        System.out.println(cnt);
        System.out.println(max);
        System.out.println(max_size);
    }
    static int max=0;
    static int max_size=0;

    static void bfs(int r, int c,int r_id){
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{r,c});
        visited[r][c]=true;
        regionId[r][c]=r_id;
        int cnt=0;
        while (!queue.isEmpty()) {
            cnt++;
            int [] cur = queue.poll();
            int c_r = cur[0];
            int c_c = cur[1];
            regionId[c_r][c_c]=r_id;
            
            for(int d=0; d<4; d++){
                if((mask[d] & map[c_r][c_c]) ==0){
                    int nr = c_r + dr[d];
                    int nc = c_c + dc[d];
                    if(!visited[nr][nc]){
                        visited[nr][nc]=true;
                        queue.offer(new int[]{nr,nc});
                    }
                }
            }
            
        }
        if(cnt > max){
            max = cnt;
        }
        regionSize.put(r_id, cnt);
    }


    static void max_find(int r, int c){
        for(int d=0; d<4; d++){
                if((mask[d] & map[r][c]) !=0){ //벽인 경우
                    int nr = r + dr[d];
                    int nc = c + dc[d];
                    if(nr<0 || nc<0 ||nr>=R || nc>=C || regionId[nr][nc]==regionId[r][c]) continue;
                    int size = regionSize.get(regionId[r][c]) + regionSize.get(regionId[nr][nc]);
                    if(size > max_size){
                        max_size=size;
                    }
                }
            }
    }
}