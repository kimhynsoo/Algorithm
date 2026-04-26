import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Road implements Comparable<Road>{
        int to;
        int weight;
        Road(int to,int weight){
            this.to=to;
            this.weight=weight;
        }
        @Override
        public int compareTo(Road o) {
            
            return o.weight-this.weight;
        }
    }
    static int start,end,N;
    static ArrayList<Road>[] roads;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        roads=new ArrayList[N+1];
        
        for(int i=0; i<=N; i++){
            roads[i] = new ArrayList<>();
        }
        int left = 1;
        int right = 0;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            right = Math.max(right, weight);
            roads[from].add(new Road(to, weight));
            roads[to].add(new Road(from, weight));
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
        
        int answer=0;
        while (left<=right) {
            int mid = (left+right)/2;
            if(canMove(mid)){
                answer=mid;
                left=mid+1;
            }else{
                right=mid-1;
            }
            
        }
        System.out.println(answer);
    }
    static boolean canMove(int limit){
        boolean[] visited= new boolean[N+1];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll();
            if(cur==end){
                return true;
            }
            for(Road road : roads[cur]){
                int to = road.to;
                int weight = road.weight;
                if(!visited[to] && weight>=limit){
                    visited[to]=true;
                    q.offer(to);
                }
            }
        }
        return false;
    }
}
