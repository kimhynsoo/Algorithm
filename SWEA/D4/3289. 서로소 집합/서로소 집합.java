import java.io.*;
import java.util.*;
public class Solution {
    static int N,M;
    static int []parents;
    static int []rank;

    static void make(){
        for(int i=1; i<=N; i++){
            parents[i]=i;
        }
    }
    static int find(int a){
        if(parents[a]==a) return a;
        return parents[a] = find(parents[a]);
    }
    static boolean union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);

        if(rootA == rootB) return false;
        else{
            if(rank[rootA]>rank[rootB]){
                parents[rootB]=rootA;
            }
            else if(rank[rootA]<rank[rootB]){
                parents[rootA]=rootB;
            }
            else{
                parents[rootB]=rootA;
                rank[rootA]++;
            }
            return true;
        }
    }

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int tc = 1; tc <= T; tc++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            parents = new int[N+1];
            rank = new int[N+1];
            make();
            System.out.printf("#%d ",tc);
            for(int i=0; i<M; i++){
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                if(op==0){ //union
                    union(a, b);
                }
                else if(op==1){ //find
                    if(find(a)==find(b)){
                        System.out.print(1);
                    }
                    else{
                        System.out.print(0);
                    }
                }
            }
            System.out.println();

        }
    }
}
