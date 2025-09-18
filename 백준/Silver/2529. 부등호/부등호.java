import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<String> res = new ArrayList<>();
    static int k;
    static int[] permu;
    static char[] arr;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k = Integer.parseInt(br.readLine());
        arr = new char[k];
        permu = new int[k+1];
        visited = new boolean[10];
        String[] line = br.readLine().split(" ");
        for(int i=0; i<k; i++){
            arr[i]=line[i].charAt(0);
        }
        find(0);
        Collections.sort(res);
        System.out.println(res.get(res.size()-1));
        System.out.println(res.get(0));
        
    }
    static void find(int cnt){
        if(cnt==k+1){
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<k+1; i++){
                sb.append(permu[i]);
            }
            res.add(sb.toString());
            return;
        }
        
        for(int i=0; i<10 ;i++){
            if(visited[i]) continue;
            if(cnt==0){
                permu[cnt]=i;
                visited[i]=true;
                find(cnt+1);
                visited[i]=false;
            }else{
                
                if(arr[cnt-1] == '<' && permu[cnt-1]<i){
                    permu[cnt]=i;
                    visited[i]=true;
                    find(cnt+1);
                    visited[i]=false;
                }else if(arr[cnt-1] == '>'&& permu[cnt-1]>i){
                    permu[cnt]=i;
                    visited[i]=true;
                    find(cnt+1);
                    visited[i]=false;
                }

            }
        }
    }
}
