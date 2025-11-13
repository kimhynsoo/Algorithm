import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    static int find(int x){
        if(parent[x]==x)return x;
        return parent[x] = find(parent[x]);
    }
    static boolean union(int a,int b){
        a=find(a);
        b=find(b);
        if(a==b) return false;
        parent[b]=a;
        return true;
    }
    static int []parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        
        parent=new int[N+1];
        for(int i=1; i<=N; i++){
            parent[i]=i;        }
        for(int i=1; i<=N; i++){
            StringTokenizer st =new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num==1){
                    union(i, j);
                }
            }
        }

        StringTokenizer st =new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        for(int i=1; i<M; i++){
            int to =Integer.parseInt(st.nextToken());
            if(union(start, to)){
                System.out.println("NO");
                return;
            }
            start=to;
        }
        System.out.println("YES");
    }
    
}
