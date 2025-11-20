import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int []parent;
    static int find(int a){
        if(parent[a]==a) return a;
        return parent[a]=find(parent[a]);
    }
    static boolean union(int a,int b){
        a = find(a);
        b = find(b);
        if(a==b){
            return false;
        }
        parent[a]=b;
        return true;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        parent=new int[G+1];
        for(int i=0; i<=G; i++){
            parent[i]=i;
        }
        int res=0;
        for(int i=0; i<P; i++){
            int g = Integer.parseInt(br.readLine());

            int possibleGate=find(g);

            if(possibleGate==0) break;

            res++;
            union(possibleGate, possibleGate-1);
        }
        System.out.println(res);
        
    }
}