import java.io.*;
import java.util.*;

public class Main {
	
	static int m, n, h;
	static int[][][] arr;
	static int[] dx = {-1, 1, 0, 0, 0, 0};
	static int[] dy = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, 1, -1};
	static Queue<int[]> q = new ArrayDeque<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		
		arr = new int[h][n][m];
		
		for(int i = 0; i<h; i++) {
			for(int j = 0; j<n; j++) {
				st = new StringTokenizer(br.readLine());
				for(int k = 0; k<m; k++) {
					arr[i][j][k] = Integer.parseInt(st.nextToken());
					if(arr[i][j][k] == 1) q.offer(new int[] {j,k,i});
				}
			}
		}
		
		bfs();
		
		int result = Integer.MIN_VALUE;
		for(int i = 0; i<h; i++) {
			for(int j = 0; j<n; j++) {
				for(int k = 0; k<m; k++) {
					if(arr[i][j][k] == 0) {
						System.out.println(-1);
						System.exit(0);
					}
					if(arr[i][j][k] > result) {
						result = arr[i][j][k];
					}
				}
			}
		}
		System.out.println(result-1);
		

	}
	
	static void bfs() {
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int now_x = now[0];
			int now_y = now[1];
			int now_h = now[2];
			
			for(int i = 0; i<6; i++) {
				int nx = now_x + dx[i];
				int ny = now_y + dy[i];
				int nh = now_h + dh[i];
				
				if(nx < 0 || ny < 0 || nh < 0 || nx >= n || ny >= m || nh >= h) continue;
				if(arr[nh][nx][ny] != 0) continue;
				
				arr[nh][nx][ny] = arr[now_h][now_x][now_y]+1;
				q.offer(new int[] {nx, ny, nh});
			}
		}
	}

}
