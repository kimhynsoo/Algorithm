import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static char [][]board;
	static int [][] visited;
	static int R,C,K;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	static int res = -1;
	static int x_1,y_1,x_2,y_2;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new int[R][C];
		for(int i=0; i<R; i++) {
			String line = br.readLine();
			for(int j=0; j<C; j++) {
				board[i][j] = line.charAt(j);
				visited[i][j] = Integer.MAX_VALUE;
			}
		}
		
		st = new StringTokenizer(br.readLine());
		x_1 = Integer.parseInt(st.nextToken())-1;
		y_1 = Integer.parseInt(st.nextToken())-1;
		x_2 = Integer.parseInt(st.nextToken())-1;
		y_2 = Integer.parseInt(st.nextToken())-1;
		
		
		bfs(x_1,y_1,0);
		
		if(visited[x_2][y_2] ==Integer.MAX_VALUE) {
			System.out.println(-1);
			
		}
		else {
			System.out.println(visited[x_2][y_2]);
		}
	}
	
	static void bfs(int r, int c,int cnt) {
		
		
		Queue<int[]> q= new ArrayDeque<>(); 
		visited[r][c]=0;
		q.offer(new int[] {r,c});
		
		while(!q.isEmpty()) {
			int [] cur =q.poll();
			int x = cur[0];
			int y = cur[1];
			int ncnt=visited[x][y]+1;
			
			for(int i=0; i<4; i++) {
				int nx = x;
				int ny = y;
				for(int k=0; k<K; k++) {
					nx += dx[i];
					ny += dy[i];
					if(nx<0 || ny<0 || nx>=R || ny >=C || board[nx][ny]=='#') break;
					if(visited[nx][ny]<ncnt) break;
					if(visited[nx][ny] == Integer.MAX_VALUE) {
						
						q.offer(new int[] {nx,ny});
						visited[nx][ny]=ncnt;
					}
				}
			}
		}
		
	}
}


