import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main { // 클래스명 PascalCase

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 1. N < 2 예외 처리
        if (N < 2) {
            System.out.println(0);
            return;
        }

        // 2. 에라토스테네스의 체로 소수 찾기
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;
        ArrayList<Integer> primeList = new ArrayList<>(); // 변수명 camelCase

        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                primeList.add(i);
                // 소수의 배수 제거 (i*i부터 시작하여 효율성 확보)
                for (long j = (long)i * i; j <= N; j += i) {
                    isPrime[(int)j] = false;
                }
            }
        }

        // 3. 투 포인터로 연속합 계산
        int left = 0;
        int right = 0;
        int count = 0;
        long sum = 0; // 큰 N에 대비하여 long 사용 (선택 사항)
        
        while (left < primeList.size()) {
            if (sum == N) {
                count++;
                // N과 같으면 왼쪽 포인터 이동 (구간 축소)
                sum -= primeList.get(left++);
            } else if (sum < N) {
                // N보다 작으면 오른쪽 포인터 이동 (구간 확장)
                if (right < primeList.size()) {
                    sum += primeList.get(right++);
                } else {
                    // 오른쪽 끝에 도달했으나 합이 N보다 작으면 더 이상 확장 불가
                    break;
                }
            } else { // sum > N
                // N보다 크면 왼쪽 포인터 이동 (구간 축소)
                sum -= primeList.get(left++);
            }
        }

        System.out.println(count);
    }
}