import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int arr[][] ;
    static int area[][];
    static boolean [][]visited;

    static int N,area_num;

    public static void main(String[] args) throws IOException{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N][N];
        area = new int[N][N];
        
        StringTokenizer st;
        area_num=1;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                arr[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j]==1&&area[i][j]==0){
                    area_bfs(i, j);
                }
            }
        }
        

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j]==1){
                    bfs(i, j);
                }
            }
        }

        System.out.println(min);

    }
    static int []dr={-1,1,0,0};
    static int []dc={0,0,-1,1};
    static void area_bfs(int r, int c){
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});
        area[r][c]=area_num;
        while (!q.isEmpty()) {
            int []cur = q.poll();
            for(int i=0; i<4; i++){
                int nr = cur[0]+dr[i];
                int nc = cur[1]+dc[i];

                if(nr<0 || nc<0 || nr>=N|| nc>=N||arr[nr][nc]==0) continue;
                if(area[nr][nc]==0){
                    area[nr][nc]=area_num;
                    q.add(new int[]{nr,nc});
                }
            }
            
        }

        area_num++;
    }
    

    static int min = Integer.MAX_VALUE;
    static void bfs(int r,int c){
        visited = new boolean[N][N];
        int this_area = area[r][c];
        visited[r][c]=true;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c,0});
        
        while (!q.isEmpty()) {
            
            int []cur = q.poll();
            for(int i=0; i<4; i++){
                int nr = cur[0]+dr[i];
                int nc = cur[1]+dc[i];
                if(nr<0 || nc<0 || nr>=N|| nc>=N||visited[nr][nc]) continue;
                if(arr[nr][nc]==0){
                    visited[nr][nc]=true;
                    q.offer(new int[]{nr,nc,cur[2]+1});
                }
                if(arr[nr][nc]==1 && area[nr][nc]!=this_area){
                    min = Math.min(min,cur[2]);
                    return;
                }

            }
            
        }
        
    }
}
