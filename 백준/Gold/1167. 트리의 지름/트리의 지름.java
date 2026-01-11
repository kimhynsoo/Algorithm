import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static class Pair{
        int to;
        int weight;

        Pair(int to, int weight){
            this.to=to;
            this.weight=weight;
        }

    }

    static ArrayList<Pair>[] arr;
    static boolean visited[];
    static int max=0;
    static int firstMax=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        arr = new ArrayList[N+1];
        visited = new boolean[N+1];

        for(int i=1; i<=N; i++){
            arr[i]= new ArrayList<>();
        }

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if(to == -1) break;
                int weight = Integer.parseInt(st.nextToken());

                arr[start].add(new Pair(to, weight));
            }
        }
        

        dfs(1, 0);
        visited = new boolean[N+1];
        dfs(firstMax, 0);

        System.out.println(max);




    }
    
    static void dfs(int start,int cnt){
        if(cnt>max) {
            max = cnt;
            firstMax=start;
        }
        visited[start] = true;
        for(Pair p : arr[start]){
            if(!visited[p.to] ){
                
                dfs(p.to, cnt+p.weight);
                
            }
        }
        

    }
}