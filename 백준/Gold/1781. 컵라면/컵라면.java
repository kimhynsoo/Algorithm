import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Ramen implements Comparable<Ramen>{
        int deadline,cup;
        Ramen(int deadline,int cup){
            this.deadline = deadline;
            this.cup = cup;
        }

        @Override
        public int compareTo(Ramen o) {
            if(this.deadline==o.deadline){
                return o.cup-this.cup;
            }
            return this.deadline-o.deadline;
        }
    }
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Ramen> pq = new PriorityQueue<>();
        for(int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());
            pq.add(new Ramen(deadline, cup));
        }

        
        PriorityQueue<Integer> result = new PriorityQueue<>();
        while (!pq.isEmpty()) {
            Ramen cur =pq.poll();
            result.offer(cur.cup);

            if(result.size()>cur.deadline){
                result.poll();
            }
            
        }
        long sum=0;
        while (!result.isEmpty()) {
            sum+=result.poll();
            
        }
        System.out.println(sum);


    }
}
