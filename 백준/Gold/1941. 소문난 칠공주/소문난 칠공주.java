import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.io.IOException;

public class Main {
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static boolean[][] visited;
    static char[][] maps;
    static int[] selected;
    static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maps = new char[5][5];
        for(int i=0;i<5;i++){
            String input = br.readLine();
            for(int j=0;j<5;j++){
                maps[i][j] = input.charAt(j);
            }
        }
        selected = new int[7];
        comb(0,0);
        System.out.println(answer);
    }
    
    static void comb(int index, int start){
        if(index == 7){
            int s_cnt = 0;
            
            visited = new boolean[5][5];
            for(int i=0;i<7;i++){
                int x = selected[i] / 5;
                int y = selected[i] % 5;
                visited[x][y] = true;
                if(maps[x][y] == 'S') s_cnt++;
            }
            if(s_cnt>=4){
                if(check()){
                    answer++;
                }
            }
            
            return;
        }
        for(int i=start;i<25;i++){
            selected[index] = i;
            comb(index+1,i+1);
        }
    }
    static boolean check(){
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{selected[0]/5,selected[0]%5});
        visited[selected[0]/5][selected[0]%5] = false;
        int cnt = 0;
        while(!q.isEmpty()){
            int[] now = q.poll();
            cnt++;
            for(int i=0;i<4;i++){
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(nx<0 || nx>=5 || ny<0 || ny>=5) continue;
                if(!visited[nx][ny]) continue;
                visited[nx][ny] = false;
                q.offer(new int[]{nx,ny});
            }
        }
        return cnt == 7;
    }
   
    
    
}
