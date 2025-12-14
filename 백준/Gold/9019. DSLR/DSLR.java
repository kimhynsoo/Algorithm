import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            String[] input = br.readLine().split(" ");
            int A = Integer.parseInt(input[0]);
            int B = Integer.parseInt(input[1]);

            boolean[] visited = new boolean[10000];
            int[] from = new int[10000];      // 어디서 왔는가
            char[] how = new char[10000];      // 어떤 명령으로 왔는가

            Queue<Integer> q = new ArrayDeque<>();
            q.add(A);
            visited[A] = true;

            while (!q.isEmpty()) {
                int cur = q.poll();

                if (cur == B) break;

                int D = (cur * 2) % 10000;
                int S = (cur == 0) ? 9999 : cur - 1;
                int L = (cur % 1000) * 10 + (cur / 1000);
                int R = (cur % 10) * 1000 + (cur / 10);

                if (!visited[D]) {
                    visited[D] = true;
                    from[D] = cur;
                    how[D] = 'D';
                    q.add(D);
                }
                if (!visited[S]) {
                    visited[S] = true;
                    from[S] = cur;
                    how[S] = 'S';
                    q.add(S);
                }
                if (!visited[L]) {
                    visited[L] = true;
                    from[L] = cur;
                    how[L] = 'L';
                    q.add(L);
                }
                if (!visited[R]) {
                    visited[R] = true;
                    from[R] = cur;
                    how[R] = 'R';
                    q.add(R);
                }
            }

            // 역추적
            StringBuilder result = new StringBuilder();
            int cur = B;

            while (cur != A) {
                result.append(how[cur]);
                cur = from[cur];
            }

            System.out.println(result.reverse().toString());
        }
    }
}
