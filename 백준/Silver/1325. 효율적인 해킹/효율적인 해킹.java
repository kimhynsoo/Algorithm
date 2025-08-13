import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Integer>> graph;
    static int[] result;
    static int N, M;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); //N개의 컴퓨터
        M = Integer.parseInt(st.nextToken()); //M개의 신뢰 관계
        graph=new ArrayList<>();
        
        result = new int[N+1];
        
        //해당 key컴퓨터를 신뢰하는 컴퓨터 리스트.

        for(int i =0; i<=N; i++){
            graph.add(new ArrayList<Integer>());
        }
        for(int i=0; i<M; i++){
            st=new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            graph.get(B).add(A);
        }

        int max=0;
        for(int i=1; i<=N; i++){
            visited= new boolean[N+1];
            DFS(i);
            for(int j=1; j<=N; j++){
                if(visited[j])
                {
                    result[i]++;
                }
            }
        }

        for(int i=1; i<=N; i++){
            if(result[i]>=max){
                max=result[i];
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++){
            if(result[i]==max)
                sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
        


    }
    static void BFS(int start){
        boolean[] visited = new boolean[N+1];
        Queue<Integer> q = new LinkedList<>();
        visited[start]=true;
        q.add(start);

        while (!q.isEmpty()) {
            int cur = q.poll();

            for(int next : graph.get(cur)){
                if(!visited[next]){
                    visited[next]=true;
                    result[start]++;
                    q.add(next);
                }
            }
            
        }
    }

    static void DFS(int start){
        visited[start]=true;

        for(int k : graph.get(start)){
            if(!visited[k]){
                
                DFS(k);
            }
        }
    }
}
