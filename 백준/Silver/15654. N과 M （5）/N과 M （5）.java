import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static boolean[] visited; // 방문 여부 체크

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr); // 사전순 출력을 위해 정렬
        visited = new boolean[n];
        dfs(arr, m, 0, new int[m]);
        System.out.println(sb.toString());
    }

    public static void dfs(int[] arr, int m, int depth, int[] result) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = arr[i];
                dfs(arr, m, depth + 1, result);
                visited[i] = false; // 백트래킹
            }
        }
    }
}
