import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static long []oil;
    static long []dist;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N =Integer.parseInt(br.readLine());
        dist=new long[N-1];
        oil=new long [N-1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++){
            dist[i]=Long.parseLong(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N-1; i++){
            oil[i]=Long.parseLong(st.nextToken());
        }
        long res =0;
        int idx=0;
        while (idx<N-1) {
            res += oil[idx]*dist[idx];
            int next =idx+1;
            while(next<N-1 && oil[next]>oil[idx]){
                res+= oil[idx]*dist[next];
                next++;
            }
            idx=next;
        }
        System.out.println(res);
    }
}