import java.util.*;
import java.io.*;
public class Main {
    static int R,C;
    static char[][] board;
    // static boolean[] visited;
    static int max =0;

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
        // visited = new boolean[26];
        // visited[board[0][0]-'A']=true;
        dfs(0, 0,1, 1 << board[0][0]-'A');
        System.out.println(max);


    }
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static void dfs(int r, int c, int cnt, int bitmask) {
        max = Math.max(max, cnt);

        for (int i = 0; i < 4; i++) {
            int nx = r + dx[i];
            int ny = c + dy[i];

            if(nx<0 || ny<0 || nx>=R || ny>=C) continue;
            int nextChar = board[nx][ny] - 'A';

            if((bitmask & (1 << nextChar)) == 0) {
                dfs(nx, ny, cnt + 1, bitmask | (1 << nextChar));
            }
        }
    }



}
