import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static class island{
        double x,y;
        island(){};

        double length(island o){
            return Math.sqrt(Math.pow(this.x-o.x, 2)+Math.pow(this.y-o.y, 2));
        }
    }

    static class Edge implements Comparable<Edge> {
        int from,to;
        double weight;

        Edge(int from,int to, double weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            // TODO Auto-generated method stub
            return Double.compare(this.weight, o.weight);
        }
        
    
        
    }

    static int V;
    static double E;
    static int[] parents;
    static island[] islandList;
    // static ArrayList<Edge> edgeList;
    static PriorityQueue<Edge> edgeList;
    static int[] visited = new int[2];

    static void make(){
        for(int i=0; i<V; i++){
            parents[i]=i;
        }
    }

    static int find(int a){
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int tc = 1 ; tc<=T; tc++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            parents = new int[V];
            make();
            islandList = new island[V];
            for(int i=0; i<V; i++){
                islandList[i] = new island();
            }
            st = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for(int i=0; i<V; i++){
                islandList[i].x = Integer.parseInt(st.nextToken());
                islandList[i].y = Integer.parseInt(st2.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            E = Double.parseDouble(st.nextToken());
            
            // edgeList = new ArrayList<>();
            edgeList = new PriorityQueue<>();
            comb(0, 0);
            // Collections.sort(edgeList);
            int cnt=0;
            double total_weight=0;
            int n = V-1;
            // for(Edge e : edgeList){
            //     if(union(e.from, e.to)){
            //         total_weight += e.weight;
            //         if(++cnt == n) break;
            //     }
            // }
            while (!edgeList.isEmpty()) {
                
                Edge now =edgeList.poll();
                if(union(now.from,now.to)) {
                    total_weight += now.weight;
                    if(++cnt==n) break;
                }
                
            }
            
            System.out.printf("#%d %d\n",tc,Math.round(total_weight));

        }
    }

    static void comb(int cnt, int start){
        if(cnt == 2){
            int from = visited[0];
            int to = visited[1];
            double weight = Math.pow(islandList[from].length(islandList[to]) ,2)* E;
            edgeList.offer(new Edge(from, to, weight));

            return;
        }

        for(int i=start; i<V; i++){
            visited[cnt]=i;
            comb(cnt+1, i+1);
        }
    }
}
