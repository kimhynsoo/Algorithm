import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());



        int [][] info = new int[N][2];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup_ramen = Integer.parseInt(st.nextToken());
            info[i][0] = deadline;
            info[i][1] = cup_ramen;
        }

        Arrays.sort(info,(o1,o2)->{
            if(o1[0]==o2[0]){
                return -(o1[1]-o2[1]);
            }
            return o1[0]-o2[0];
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<N;i ++){
            pq.offer(info[i][1]);
            
            if(pq.size() > info[i][0]){
                pq.poll();
            }
        }
        
        long result=0;
        while (!pq.isEmpty()) {
            result+=pq.poll();
            
        }

        System.out.println(result);
    }
}
