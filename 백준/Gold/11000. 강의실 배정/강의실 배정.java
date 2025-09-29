
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    static class Pair implements Comparable<Pair>{
        int start,end;

        public Pair(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Pair o) {
            if(this.start!=o.start){
                return this.start-o.start;
            }
            else{
                return this.end-o.end;
            }
            
        }
        

    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Pair[] list = new Pair[N];
        for(int i=0; i<N; i++){
            list[i]=new Pair(sc.nextInt(), sc.nextInt());
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