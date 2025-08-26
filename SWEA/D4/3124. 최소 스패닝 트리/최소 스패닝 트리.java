import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static class Edge implements Comparable<Edge>{
        int from,to,weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            
            return Integer.compare(this.weight, o.weight);
        }
        
    }

    static Edge[] edgeList;
    static int V,E;
    static int[] parents;
    static int cnt;
    static long total_weight;

    static void make(){
        for(int i=1; i<=V; i++){
            parents[i]=i;
        }
    }

    static int find(int a){
        if(a == parents[a]) return a;
        return parents[a]=find(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;

    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for(int tc=1; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            parents = new int[V+1];
            edgeList = new Edge[E];
            make();
            for(int i=0; i<E; i++){
                st = new StringTokenizer(br.readLine());
                int from =Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int weight = Integer.parseInt(st.nextToken());
                edgeList[i] = new Edge(from, to, weight);
            }

            Arrays.sort(edgeList);
            total_weight =0;
            cnt=0;
            for(Edge e : edgeList){
                if(union(e.from, e.to)){
                    
                    total_weight+=e.weight;
                    if(++cnt == V-1) break;
                }
                
            }
            System.out.printf("#%d %d\n",tc,total_weight);
        }
        
    }
}
