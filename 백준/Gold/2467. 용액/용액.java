
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        int res = Integer.MAX_VALUE;
        int min_al = 0;
        int min_san = 0;
        while (left < right) {
            int mid = (left + right) / 2;

            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < res) {
                res = Math.abs(sum);
                min_al = arr[left];
                min_san = arr[right];
                if (sum == 0) {
                    break;
                }
                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            } else {
                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        System.out.print(min_al + " " + min_san);
    }
}
