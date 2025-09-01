import java.util.*;
import java.io.*;

public class Main {

	static int n, m;
	static int[] parents;
	static int result;
	static boolean check;
	static int[][] arr;
	
	static void make() {
		for(int i = 0; i<n; i++) {
			parents[i] = i;
		}
	}
	
	static int find(int a) {
		if(parents[a] == a) return a;
		return parents[a] = find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa == pb) return false;
		if(pa > pb) parents[pb] = pa;
		else parents[pa] = pb;
		return true;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		parents = new int[n];
		arr = new int[m][2];
		make();
		check = true;
		
		for(int i = 0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[i][0] = a;
			arr[i][1] = b;
		}
		for(int i = 0; i<m; i++) {
			if(union(arr[i][0], arr[i][1])) {
				result++;
			}else {
				check = false;
				break;
			}
		}
		if(!check) System.out.println(result+1);
		else System.out.println(0);
		
	}

}
