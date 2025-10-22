import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.StringTokenizer;

public class Main {
    static int space;                // 행 개수 (행성 수)
    static int planet;               // 열 개수 (특성 수)
    static int[][] processed_map;    // 정규화된 행렬
    static int[] combination = new int[2]; // 2개 행 조합 저장용 배열
    static int res = 0;              // 같은 행 쌍 개수 카운트

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행(space)과 열(planet) 입력
        space = Integer.parseInt(st.nextToken());
        planet = Integer.parseInt(st.nextToken());

        int[][] map = new int[space][planet]; // 원본 행렬

        // 행렬 입력
        for (int i = 0; i < space; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < planet; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 각 행을 "정규화"해서 processed_map에 저장
        // 정규화란: 같은 값은 같은 인덱스로 변환하여 값 자체가 아닌 순위로 비교 가능하게 함
        processed_map = new int[space][planet];
        for (int t = 0; t < space; t++) {
            Set<Integer> uniqueValues = new TreeSet<>();      // 해당 행의 값들을 오름차순 정렬
            Map<Integer, Integer> indexMap = new HashMap<>(); // 값 → 정규화된 인덱스 매핑

            // 유일한 값 수집
            for (int i = 0; i < planet; i++) {
                uniqueValues.add(map[t][i]);
            }

            // 각 값에 순위(index) 부여
            int idx = 0;
            for (int val : uniqueValues) {
                indexMap.put(val, idx++);
            }

            // processed_map에 정규화된 값 저장
            for (int i = 0; i < planet; i++) {
                processed_map[t][i] = indexMap.get(map[t][i]);
            }
        }

        // 2개 행의 조합을 모두 비교
        comb(0, 0);

        // 결과 출력
        System.out.println(res);
    }

    static void comb(int start, int depth) {
        // 조합 2개가 선택되었을 때
        if (depth == 2) {
            // 두 행이 같은지 비교 (내용 기준)
            if (Arrays.equals(processed_map[combination[0]], processed_map[combination[1]])) {
                res++; // 같으면 카운트 증가
            }
            return;
        }

        // start부터 마지막 행까지 반복하며 조합 생성
        for (int i = start; i < space; i++) {
            combination[depth] = i;          // 현재 깊이에 행 인덱스 저장
            comb(i + 1, depth + 1);         // 다음 깊이로 재귀
        }
    }
}
