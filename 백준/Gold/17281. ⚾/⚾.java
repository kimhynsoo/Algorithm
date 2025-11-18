import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 선수 선택 여부 체크용 (1~9번 타자 → true면 이미 순서에 포함됨)
    static boolean[] visited = new boolean[10];
    // 타순을 저장 (1~9번 타순, order[4]은 고정 타자)
    static int[] order = new int[10];
    static int ans = 0; // 최종 최대 점수
    static int N; // 이닝 수
    static int[][] graph; // graph[이닝][선수번호] = 결과 (0:아웃,1~4 안타)

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][10];

        // graph 입력받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 9; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 4번 타순(= index 4)에 1번 선수 고정
        order[4] = 1;
        permu(1); // 1번 타순부터 조합 시작
        System.out.println(ans);
    }

    // 현재 타순(order)에 대해 실제 경기 시뮬레이션
    static int play() {
        int score = 0;
        int hitter = 1; // 현재 타석에 들어올 타순 번호 (1~9 반복)

        // 이닝 반복
        for (int i = 1; i <= N; i++) {
            int base[] = new int[5]; // base[1]=1루, ..., base[4]=홈
            int out = 0;

            // 3아웃 될 때까지 반복
            while (out < 3) {
                int hit = graph[i][order[hitter]]; // 이번 타자가 친 결과
                hitter = (hitter) % 9 + 1; // 다음 타자 순번

                if (hit == 0) { // 아웃
                    out++;
                    continue;
                }

                // 기존 주자들 이동
                for (int j = 0; j < hit; j++) {
                    base[4] += base[3];
                    base[3] = base[2];
                    base[2] = base[1];
                    base[1] = base[0];
                }

                // 타자도 출루
                base[hit]++;
            }

            // 홈으로 들어온 주자들 점수로 반영
            score += base[4];
        }
        return score;
    }

    // 4번 타순을 제외하고 나머지 타순을 완전탐색 (순열 생성)
    static void permu(int depth) {
        if (depth == 10) { // 1~9 모두 채움
            ans = Math.max(ans, play());
            return;
        }

        // 4번 타순은 이미 1번 선수로 고정돼 있으므로 건너뜀
        if (depth == 4) {
            permu(depth + 1);
            return;
        }

        // 2~9번 선수들 중 아직 선택되지 않은 선수 선택
        for (int i = 2; i <= 9; i++) {
            if (visited[i])
                continue;
            visited[i] = true;
            order[depth] = i;
            permu(depth + 1);
            visited[i] = false;
        }
    }
}
