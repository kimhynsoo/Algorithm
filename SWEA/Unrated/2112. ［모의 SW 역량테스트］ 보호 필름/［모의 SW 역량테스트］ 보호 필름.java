import java.io.*;
import java.util.*;

public class Solution {
    static int D, W, K;
    static int[][] board;
    static int[][] temp_board;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[D][W];
            temp_board = new int[D][W];
            for (int i = 0; i < D; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = temp_board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            result = Integer.MAX_VALUE;
            if (check()) {
                System.out.printf("#%d %d\n", tc, 0);
            } else {
                dfs(0, 0);
                System.out.printf("#%d %d\n", tc, result);
            }
        }
    }

    // idx번째 행을 어떻게 처리할지 결정 (0: 그대로, 1: 모두 A, 2: 모두 B)
    static void dfs(int idx, int changeCnt) {
        if (changeCnt >= result) return;
        if (idx == D) {
            if (check()) result = Math.min(result, changeCnt);
            return;
        }

        // 그대로 두기
        dfs(idx + 1, changeCnt);

        // 모두 A로 변경
        Arrays.fill(board[idx], 0);
        dfs(idx + 1, changeCnt + 1);

        // 모두 B로 변경
        Arrays.fill(board[idx], 1);
        dfs(idx + 1, changeCnt + 1);

        // 원복 (다음 탐색 위해)
        board[idx] = Arrays.copyOf(temp_board[idx], W);
    }

    static boolean check() {
        for (int j = 0; j < W; j++) {
            int cnt = 1;
            boolean pass = false;
            for (int i = 1; i < D; i++) {
                if (board[i][j] == board[i - 1][j]) cnt++;
                else cnt = 1;
                if (cnt >= K) {
                    pass = true;
                    break;
                }
            }
            if (!pass) return false;
        }
        return true;
    }
}
