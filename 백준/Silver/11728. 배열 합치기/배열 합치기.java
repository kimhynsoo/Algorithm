import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int[] arr1 = new int[a];
        int[] arr2 = new int[b];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < a; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < b; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;

        // 투 포인터로 병합
        while (i < a && j < b) {
            if (arr1[i] <= arr2[j]) {
                sb.append(arr1[i++]).append(" ");
            } else {
                sb.append(arr2[j++]).append(" ");
            }
        }

        // 남은 원소 처리
        while (i < a) sb.append(arr1[i++]).append(" ");
        while (j < b) sb.append(arr2[j++]).append(" ");

        System.out.println(sb);
    }
}
