
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Pair implements Comparable<Pair>{
        int start,end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            int cmp = Integer.compare(this.start, o.start);
            if(cmp==0){
                cmp = Integer.compare(this.end, o.end);
            }
            return cmp;
            
        }
        

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Pair[] list = new Pair[N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            list[i]=new Pair(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(list);
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(Pair p : list){
            if(!pq.isEmpty()&& p.start>=pq.peek()){
                pq.poll();
            }
            pq.add(p.end);
        }
        System.out.println(pq.size());
    }
}