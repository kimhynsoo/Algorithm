import java.util.*;
import java.io.*;
public class Main {
    static int N,M;
    static boolean[] visited;
    static ArrayList<ArrayList<Integer>> realtion = new ArrayList<>();

    static void make(){
        for(int i=0; i<N; i++){
            
            realtion.add(new ArrayList<Integer>());
        }

    }

    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        
        make();
        visited = new boolean[N];
        for(int i=0; i<M;i++){
            st = new StringTokenizer(br.readLine());
            int a =Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            realtion.get(a).add(b);
            realtion.get(b).add(a);

        }
        for(int i=0; i<N; i++){
            if(dfs(i, 0)) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
        
        
    }

    static boolean dfs(int start,int cnt){
        if(cnt==4){
            return true;
        }
        visited[start] =true;
        for(int next : realtion.get(start)){
            if(!visited[next]){
                if(dfs(next,cnt+1)) return true;
            }
        }
        visited[start]=false;
        return false;
    }
}
