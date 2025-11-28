import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.printf("%d", dp_2());

    }

    // O(N^2) - dp 기존에 얻어진 수보다 많은 횟수면 저장하고 다음숫자 비교함
    static int dp_1() {
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    // O(NlogN)
    // 1. 배열 마지막 요소보다 새로운 수가 크다면, 배열에 넣는다.
    // 2. 그렇지 않다면, 그 수가 들어갈 자리에 넣는다. (이분 탐색을 통해 들어갈 자리를 찾는다.)
    static int dp_2() {
        int[] LIS = new int[N];
        int idx = 0;
        LIS[0] = arr[0];
        for (int i = 1; i < N; i++) {
            if (LIS[idx] < arr[i]) {
                LIS[++idx] = arr[i];
            } else if (LIS[idx] > arr[i]) {
                int ii = lower_bound(LIS, idx, arr[i]);
                LIS[ii] = arr[i];
            }
        }

        return idx + 1;
    }

    static int lower_bound(int[] dp, int end, int n) {
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if (dp[mid] < n) {
                start = mid + 1;
            } else {
                end = mid;
            }

        }
        return start;
    }
}
