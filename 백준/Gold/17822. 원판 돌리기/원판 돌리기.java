import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int N,M,T;
    static int [][] circle;
    static boolean[][]visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circle = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++)
            {
                circle[i][j]=Integer.parseInt(st.nextToken());
            }
        }

        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            for(int row = x-1; row<N; row+=x){
                rotate(row, d, k);
            }
            boolean erase = false;
            visited = new boolean[N][M];
            for(int i=0; i<N; i++){
                for(int j=0; j<M; j++){
                    if(!visited[i][j]&&circle[i][j]!=0){
                        if(bfs(i,j)) erase=true;
                    }
                }
            }
            if(!erase){
                double cnt=0;
                double sum =0;
                double avg=0;
                for(int i=0; i<N; i++){
                    for(int j=0; j<M; j++){
                        if(circle[i][j]!=0){
                            cnt++;
                            sum+=circle[i][j];
                        }
                    }  
                } 
                if(cnt ==0){
                    continue;
                }else{
                    avg = sum/cnt;
                    for(int i=0; i<N; i++){
                        for(int j=0; j<M; j++){
                            if(circle[i][j]==0) continue;
                            if((double)circle[i][j]>avg){
                                circle[i][j]-=1;
                            }else if((double)circle[i][j]<avg){
                                circle[i][j]+=1;
                            }
                        }
                    }
                }
            }
        }
        int res=0;
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                res+=circle[i][j];
            }
        }
        System.out.println(res);
            
    }


    static int dr[] = {-1,1,0,0};
    static int dc[] = {0,0,-1,1};
    static boolean bfs(int r,int c){
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{r,c});
        visited[r][c]=true;
        int num = circle[r][c];
        ArrayList<int[]> erase_list = new ArrayList<>();
        erase_list.add(new int[]{r,c});
        while (!q.isEmpty()) {
            int []cur = q.poll();

            int cr = cur[0];
            int cc = cur[1];

            for(int d=0; d<4; d++){
                int nr = cr + dr[d];
                int nc = cc + dc[d];

                // 행은 범위 벗어나면 무시
                if(nr < 0 || nr >= N) continue;

                // 열은 원형 처리
                if(nc < 0) nc = M - 1;
                else if(nc >= M) nc = 0;


                if(visited[nr][nc]) continue;
                if(circle[nr][nc]==num){
                    visited[nr][nc]=true;
                    erase_list.add(new int[]{nr,nc});
                    q.offer(new int[]{nr,nc});
                }
            }
            
        }
        if(erase_list.size()>1){
            for(int[] e : erase_list){
                circle[e[0]][e[1]]=0;
            }
        }else{
            return false;
        }
        return true;

    }

    static void rotate(int row,int d, int k){
        if(d==0){//오른쪽
            for(int i=0; i<k; i++){
                int temp = circle[row][M-1];
                for(int j=M-1; j>0; j--){
                    circle[row][j] = circle[row][j-1];
                }
                circle[row][0]=temp;
            }

        }else{
            for(int i=0; i<k; i++){
                int temp = circle[row][0];
                for(int j=0; j<M-1; j++){
                    circle[row][j] = circle[row][j+1];
                }
                circle[row][M-1]=temp;
            }
        }
    }
}
