import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int [] parents;
	static int N,M;
	static void make() {
		// TODO Auto-generated method stub
		for(int i=0; i<N; i++) {
			parents[i]=i;
		}
	}
	static int find(int a) {
		if(a==parents[a]) return a;
		return parents[a]= find(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		if(aRoot<bRoot) parents[bRoot]=aRoot;
		else  parents[aRoot]=bRoot;
		
		return true;
	}
	public static void main(String[] args) throws IOException{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents=new int [N];
		make();
		boolean flag=false;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(!union(a, b)) {
				System.out.println(i+1);
				flag = true;
				break;
			}
		}
		if(!flag) System.out.println(0);
		

	}

}
