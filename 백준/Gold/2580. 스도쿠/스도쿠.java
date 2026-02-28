import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Main {
	
	static int[][] arr = new int[9][9];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		for(int i = 0 ; i < 9 ; i++) {
		StringTokenizer str = new StringTokenizer(br.readLine());
		for(int j = 0 ; str.hasMoreTokens();j++) {
			arr[i][j]= Integer.parseInt(str.nextToken());
		}
		}

		dfs(0,0);
}
	static void dfs(int row, int col) {
		
		if(col == 9) {
			dfs(row+1,0);
			return;
		}
		
		if(row == 9) {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(arr[i][j]).append(' ');
				}
				sb.append('\n');
			}
			System.out.println(sb);
			
			System.exit(0);
		}
		
		if(arr[row][col]==0) {
			for(int i = 1 ; i <= 9 ; i++) {
				if(check(row, col, i)) {
					arr[row][col] = i;
					dfs(row, col+1);
				}
			}
			arr[row][col]=0;
			return;
		}
		
		dfs(row, col+1);
	}
	
	static boolean check(int row, int col, int value) {
		
		//같은열에 무슨 숫자가 비었는지
		for(int i = 0 ; i < 9 ; i ++) {
			if(arr[row][i] == value) {
				return false;
			}
		}
		
		//같은행에 무슨 숫자가 비었는지
		for(int i = 0 ; i < 9 ; i ++) {
			if(arr[i][col] == value) {
				return false;
			}
		}
		
		//같은 네모에 뭐가 있는지
		int ind_row = (row/3)*3;
		int ind_col = (col/3)*3;
		
		for(int i = ind_row ; i < ind_row+3 ; i ++) {
			for(int j = ind_col ; j < ind_col +3 ; j++) {
				if(arr[i][j] == value)
					return false;
			}
		}
		
		return true;
	}
	

}