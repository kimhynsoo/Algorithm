import java.util.*;
import java.io.*;

public class Main {

	static int n;
	static int[][] arr;
	static int broke, normal;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());
		arr = new int[n][n];
		for(int i = 0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0, 0, n);
		System.out.println(normal);
		System.out.println(broke);
		
	}
	static void dfs(int x, int y, int depth) {
		int target = arr[x][y];
		
		for(int i = x, xEnd = i+depth; i<xEnd; i++) {
			for(int j = y, yEnd = j+depth; j<yEnd; j++) {
				if(arr[i][j] != target) {
					
					dfs(x, y, depth/2);
					dfs(x+depth/2, y, depth/2);
					dfs(x, y+depth/2, depth/2);
					dfs(x+depth/2, y+depth/2, depth/2);
					return;
				}
			}
		}
		if(target == 1) broke++;
		else normal++;
		
	}

}
