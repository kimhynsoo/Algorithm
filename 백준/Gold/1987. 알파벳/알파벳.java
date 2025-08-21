import java.util.*;
import java.io.*;
public class Main {
    static int R,C;
    static char[][] board;
    static List<Character> visited = new ArrayList<>();
    static int max =1;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        board = new char[R][C];

        for(int i=0; i<R; i++){
            String line = br.readLine();
            for(int j=0; j<C; j++){
                board[i][j] = line.charAt(j);
            }
        }
        visited.add(board[0][0]);
        dfs(0, 0);
        System.out.println(max);


    }
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static void dfs(int r, int c){
        
        for(int i=0; i<4; i++){
            int nx = r + dx[i];
            int ny = c + dy[i];
            if(nx<0 || ny<0 || nx>=R || ny>=C || visited.contains(board[nx][ny])) continue;
            visited.add(board[nx][ny]);
            max = Math.max(visited.size(), max);
            dfs(nx, ny);
            visited.remove(visited.size()-1);
        }
        
    }


}
