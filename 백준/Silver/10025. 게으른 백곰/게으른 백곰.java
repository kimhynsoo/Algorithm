import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st = null;
	
	static final int MAX = 1_000_000;
	public static void main(String[] args) throws Exception{
		
		int[] arr = new int[MAX + 2];
		
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i =0; i< N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			arr[x] = v;
		}
		
		
		int sum = 0;
		for(int i =0; i<= 2 * K; i++) {
			if(i > MAX) continue;
			sum += arr[i];
		}
		
		int left = 0;
		int right = 2 * K;
		int max = sum;
		
		while(right <= MAX) {
			sum -= arr[left++];
			sum += arr[++right];
			max = Math.max(max, sum);
		}
		
		System.out.println(max);
	}

}
