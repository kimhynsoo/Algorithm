
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

        ArrayList<Integer> pos = new ArrayList<>();
        ArrayList<Integer> neg = new ArrayList<>();
        int zero = 0;
        int one = 0;
        for (int i = 0; i < N; i++) {

            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                pos.add(num);

            } else if (num == 0) {
                zero++;
            } else if (num == 1) {
                one++;
            } else {
                neg.add(num);
            }
        }
        Collections.sort(pos, Collections.reverseOrder());
        Collections.sort(neg);
        int res = 0;

        int i = 0;
        int pos_size = pos.size();
        while (i + 1 < pos_size) {
            res += pos.get(i) * pos.get(i + 1);
            i += 2;
        }
        if (i < pos_size) {
            res += pos.get(i);
        }

        i = 0;
        int neg_size = neg.size();
        while (i + 1 < neg_size) {
            res += neg.get(i) * neg.get(i + 1);
            i += 2;
        }
        if (i < neg_size) {
            if (zero == 0) {
                res+=neg.get(i);
            }
        }
        res+=one;
        System.out.println(res);

    }
}
