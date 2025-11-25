
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> positive_pq = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        PriorityQueue<Integer> negative_pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {

            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                positive_pq.offer(num);
            } else {
                negative_pq.offer(num);
            }
        }

        int res = 0;

        while (negative_pq.size() > 1) {
            int o1 = negative_pq.poll();
            int o2 = negative_pq.poll();
            if (o1 < 0 && o2 < 0) {
                res += o1 * o2;
            } else if (o1 < 0 && o2 == 0) {
                break;
            }

        }
        while (!negative_pq.isEmpty()) {
            res += negative_pq.poll();
        }

        while (positive_pq.size() > 1) {
            int o1 = positive_pq.poll();
            int o2 = positive_pq.poll();
            if (o1 == 1 || o2 == 1) {
                res += o1 + o2;
            } else {
                res += o1 * o2;
            }

        }
        while (!positive_pq.isEmpty()) {
            res+=positive_pq.poll();
        }

        System.out.println(res);

    }
}
