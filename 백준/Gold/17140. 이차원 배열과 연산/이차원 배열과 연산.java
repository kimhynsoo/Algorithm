import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    // 현재 배열의 행(R), 열(C) 크기
    static int R = 3;
    static int C = 3;
    static int[][] arr; // 실제 연산에 사용될 2차원 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 목표 위치 (r,c)와 목표 값 k 입력
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 배열 최대 크기를 100×100으로 고정 (문제 제한)
        arr = new int[100][100];

        // 초기 3×3 배열 입력
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int t = 0; // 경과 시간(연산 횟수)

        while (true) {
            // 목표 칸의 값이 k이면 종료
            if (arr[r - 1][c - 1] == k) {
                System.out.println(t);
                break;
            }

            // 100초를 초과하면 실패
            if (t > 100) {
                System.out.println(-1);
                break;
            }

            // 행 개수 ≥ 열 개수 → R 연산, 아니면 C 연산
            if (R >= C) {
                func_R();
            } else {
                func_C();
            }

            t++; // 1초 경과
        }
    }

    // -------------------------------
    // R 연산: 각 행 단위로 정렬 연산 수행
    // -------------------------------
    static void func_R() {
        int max_c = 0; // 연산 후 가장 긴 열의 길이
        int[][] newArr = new int[100][100]; // 결과를 저장할 임시 배열

        // 각 행마다 (숫자, 등장횟수) 쌍 계산
        for (int i = 0; i < R; i++) {
            Map<Integer, Integer> dict = new HashMap<>();

            for (int j = 0; j < C; j++) {
                int n = arr[i][j];
                if (n == 0) continue; // 0은 무시
                dict.merge(n, 1, Integer::sum); // 숫자 등장 횟수 계산
            }

            // (숫자, 빈도) 쌍 리스트화
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(dict.entrySet());

            // 정렬 기준: 빈도 오름차순 → 숫자 오름차순
            list.sort((a, b) -> {
                if (a.getValue().equals(b.getValue())) {
                    return a.getKey() - b.getKey();
                } else {
                    return a.getValue() - b.getValue();
                }
            });

            // 정렬 결과를 [숫자, 개수, 숫자, 개수, ...] 형태로 저장
            int idx = 0;
            for (Map.Entry<Integer, Integer> m : list) {
                if (idx >= 100) break; // 최대 길이 제한
                newArr[i][idx++] = m.getKey();
                newArr[i][idx++] = m.getValue();
            }

            // 현재 행의 길이 중 최댓값 갱신
            max_c = Math.max(max_c, idx);
        }

        C = max_c; // 열 개수 갱신
        arr = newArr; // 결과 배열 반영
    }

    // -------------------------------
    // C 연산: 각 열 단위로 정렬 연산 수행
    // -------------------------------
    static void func_C() {
        int max_r = 0; // 연산 후 가장 긴 행의 길이
        int[][] newArr = new int[100][100]; // 결과를 저장할 임시 배열

        // 각 열마다 (숫자, 등장횟수) 쌍 계산
        for (int j = 0; j < C; j++) {
            Map<Integer, Integer> dict = new HashMap<>();

            for (int i = 0; i < R; i++) {
                int n = arr[i][j];
                if (n == 0) continue; // 0은 무시
                dict.merge(n, 1, Integer::sum); // 숫자 등장 횟수 계산
            }

            // (숫자, 빈도) 쌍 리스트화
            List<Map.Entry<Integer, Integer>> list = new ArrayList<>(dict.entrySet());

            // 정렬 기준: 빈도 오름차순 → 숫자 오름차순
            list.sort((a, b) -> {
                if (a.getValue().equals(b.getValue())) {
                    return a.getKey() - b.getKey();
                } else {
                    return a.getValue() - b.getValue();
                }
            });

            // 정렬 결과를 세로 방향으로 저장
            int idx = 0;
            for (Map.Entry<Integer, Integer> m : list) {
                if (idx >= 100) break; // 최대 길이 제한
                newArr[idx++][j] = m.getKey();
                newArr[idx++][j] = m.getValue();
            }

            // 현재 열의 길이 중 최댓값 갱신
            max_r = Math.max(max_r, idx);
        }

        R = max_r; // 행 개수 갱신
        arr = newArr; // 결과 배열 반영
    }
}
