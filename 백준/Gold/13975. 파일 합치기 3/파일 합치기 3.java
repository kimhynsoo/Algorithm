import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int tc = Integer.parseInt(st.nextToken());
        for(int t=0; t<tc; t++){
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            
            for(int i=0; i<N; i++){
                pq.add(Long.parseLong(st.nextToken()));
            }
            long sum=0;
            while (pq.size()!=1) {
                long num= pq.poll()+pq.poll();
                sum+=num;
                pq.add(num);
            }
            System.out.println(sum);
        }
    }
}