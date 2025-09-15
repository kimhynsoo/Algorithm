import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static class Edge implements Comparable<Edge>{
		int to;
		long weight;
		public Edge(int to, long weight) {

			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return Long.compare(this.weight, o.weight);
		}
	}
	static ArrayList<Edge>[] adj;
	static int N,M;
	static int start,end;
	static long[] minDist;
	static boolean [] visited;
	static final long INF = Long.MAX_VALUE;
 	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		adj = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			adj[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long weight = Long.parseLong(st.nextToken());
			adj[from].add(new Edge(to, weight));
		}
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		minDist = new long[N+1];
		visited = new boolean[N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(minDist, INF);
		}
		minDist[start]=0;
		dijkstra();
		
		System.out.println(minDist[end]);
		
	}
 	
 	static void dijkstra() {
 		PriorityQueue<Edge> pq = new PriorityQueue<>();
 		pq.offer(new Edge(start, 0));
 		
 		while(!pq.isEmpty()) {
 			Edge cur = pq.poll();
 			
 			int to = cur.to;
 			long weight = cur.weight;
 			if(visited[to]) continue;
 			visited[to] = true;
 			
 			if(to==end) return; //종료 조건
 			
 			 			
 			for(Edge next :adj[to]) {
 				long nweight = next.weight + weight;
 				if(!visited[next.to] && minDist[next.to]>nweight) {
 					minDist[next.to] = nweight;
 					pq.offer(new Edge(next.to, nweight));
 				}
 			}
 		}
 	}
}
