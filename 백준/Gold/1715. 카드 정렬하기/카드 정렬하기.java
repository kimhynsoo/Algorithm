import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int result = 0;
        // while (!pq.isEmpty()) {
        // if (pq.size() > 1) {
        // int a = pq.poll();
        // int b = pq.poll();
        // result += a + b;
        // pq.offer(a + b);
        // } else {
        // break;
        // }
        // }

        for (int i = 0; i < N - 1; i++) {
            int a = pq.poll();
            int b = pq.poll();
            result += a + b;
            pq.offer(a + b);
        }
        System.out.println(result);
    }
}
