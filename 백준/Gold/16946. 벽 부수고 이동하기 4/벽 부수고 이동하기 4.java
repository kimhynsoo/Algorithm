
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static int [][] board,arr_idx;

    static int [] dr = {-1,1,0,0};
    static int [] dc = {0,0,-1,1};
    static int idx=1;
    static Map<Integer, Integer> idx_cnt = new HashMap<>();
    public static void main(String[] args) throws  IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][C];
        arr_idx = new int[R][C];
        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                board[i][j] = line.charAt(j) - '0';
            }
        }
        
        
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(board[i][j]==0&&arr_idx[i][j]==0){
                    bfs(i,j);
                }
                
            }
            
        }
        for(int i=0; i<R; i++){
            for(int j=0; j<C; j++){
                if(board[i][j]==1){
                    Set<Integer> idxSet = new HashSet<>();
                    int cnt=1;
                    for(int d=0; d<4; d++){
                        int nr = i+ dr[d];
                        int nc = j+ dc[d];
                        if(nr<0||nc<0||nr>=R||nc>=C) continue;
                        if(arr_idx[nr][nc]!=0){
                            idxSet.add(arr_idx[nr][nc]);
                        }
                    }
                    for(int num : idxSet){
                        cnt=cnt+idx_cnt.get(num);
                    }
                    board[i][j]=cnt%10;
                }
                sb.append(board[i][j]);
            }sb.append("\n");
            
        }
        
        System.out.println(sb.toString());
    }

    static void bfs(int r, int c){
        
        Queue<int []> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});
        arr_idx[r][c]=idx;
        int cnt=1;
        while (!q.isEmpty()) {
            int [] cur = q.poll();
            int rr = cur[0];
            int cc = cur[1];
            for(int d=0; d<4; d++){
                int nr = rr +dr[d];
                int nc = cc +dc[d];

                if(nr<0|| nc<0|| nr>=R|| nc>=C|| arr_idx[nr][nc]!=0|| board[nr][nc]==1) continue;
                
                arr_idx[nr][nc]=idx;
                
                q.offer(new int[]{nr,nc});
                cnt++;

            }
        }
        idx_cnt.put(idx, cnt);
        idx++;
        

    }
}
