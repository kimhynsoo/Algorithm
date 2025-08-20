import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,p1,p2,R;
    static boolean[] visited;
    static int [] dist;
    static int cnt=0;
    static ArrayList<ArrayList<Integer>> relation = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        dist = new int[N+1];
        for(int i=0; i<=N; i++){
            relation.add(new ArrayList<Integer>());
        }

        st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());
        st= new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());

        for(int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            relation.get(to).add(from);
            relation.get(from).add(to);
        }
        System.out.println(bfs(p1, p2));
        




    }

    static int bfs(int start,int to){
        visited[start]=true;
        dist[start]=0;
        Queue <Integer> q = new ArrayDeque<>();
        q.offer(start);
        
        while(!q.isEmpty()){
            int cur = q.poll();
            
            for(int next : relation.get(cur)){
                
                if(visited[next]) continue;
                visited[next] = true;
                q.offer(next);
                dist[next] = dist[cur]+1;
                if(next == to) return dist[next];

            }
        }
        return -1;

    }
}
