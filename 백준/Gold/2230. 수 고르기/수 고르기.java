import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(arr);
        long min_diff = Long.MAX_VALUE;

        int left = 0;
        int right = 1;
        while (right < N) {
            long diff = arr[right] - arr[left];
            if (diff < M) {
                right++;
            } else if (diff == M) {
                System.out.println(diff);
                return;
            } else {
                if (diff < min_diff) {
                    min_diff = diff;
                }
                left++;
            }

        }
        System.out.println(min_diff);

    }
}