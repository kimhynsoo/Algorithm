import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int [] dist = new int[1000001];
        if(N>=K){
            System.out.println(N-K);
        }else{
            Queue <Integer> q =new ArrayDeque<>();
            q.offer(N);
            
            while (!q.isEmpty()) {
                int cur = q.poll();
                if (cur==K) {
                    System.out.println(dist[K]);
                    return;
                }
                int [] moves = {cur-1,cur+1,cur*2};
                for(int move : moves){
                    if(move>=0 && move<=100000 ){
                        if(dist[move]==0){
                            dist[move] = dist[cur]+1;
                            q.offer(move);
                        }
                    }
                }
                
                
                
            }
        }
    }
}
