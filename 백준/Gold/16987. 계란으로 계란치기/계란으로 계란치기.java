import java.util.Scanner;

public class Main {

    static int max = 0; // 전역에서 최대값 관리

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] s = new int[n]; // 내구도
        int[] w = new int[n]; // 무게
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        dfs(s, w, n, 0);
        System.out.println(max);
    }

    public static void dfs(int[] s, int[] w, int n, int index) {
        if (index == n) {
            // 깨진 계란 개수 세기
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (s[i] <= 0) cnt++;
            }
            max = Math.max(max, cnt);
            return;
        }

        if (s[index] <= 0) { // 현재 계란이 이미 깨져 있으면 다음으로
            dfs(s, w, n, index + 1);
            return;
        }

        boolean hit = false;
        for (int i = 0; i < n; i++) {
            if (i == index || s[i] <= 0) continue; // 자기 자신 또는 깨진 계란은 skip

            // 계란 부딪히기
            s[i] -= w[index];
            s[index] -= w[i];
            hit = true;

            dfs(s, w, n, index + 1);

            // 원상복구 (백트래킹)
            s[i] += w[index];
            s[index] += w[i];
        }

        if (!hit) { // 칠 수 있는 계란이 없을 경우
            dfs(s, w, n, index + 1);
        }
    }
}
