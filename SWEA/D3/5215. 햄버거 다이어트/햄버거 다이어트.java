import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, L; // N : 재료의 수, L : 제한 칼로리 
    
    // 재료 정보를 저장할 클래스
    static Info[] infos;
    static class Info  {
        int score, kcal; // 점수, 칼로리

        public Info(int score, int kcal) {
            this.score = score;
            this.kcal = kcal;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 입력
        StringTokenizer st;

        for(int tc=1; tc<=T; tc++){
            // N : 재료 개수, L : 제한 칼로리
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            infos = new Info[N];

            // 각 재료의 점수와 칼로리 입력
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int kcal = Integer.parseInt(st.nextToken());
                infos[i] = new Info(score, kcal);
            }
            
            // dp[j] : 칼로리 합이 j일 때 얻을 수 있는 최대 점수
            int dp[] = new int[L+1];

            // 0/1 배낭 문제 방식으로 DP 채우기
            for(int i=0; i<N; i++){
                int sc = infos[i].score; // 현재 재료 점수
                int kc = infos[i].kcal;  // 현재 재료 칼로리

                // 뒤에서부터 채우는 이유: 같은 재료를 중복해서 선택하지 않기 위해
                for(int j=L; j>=kc; j--){
                    dp[j] = Math.max(dp[j], dp[j-kc] + sc);
                }
            }

            // 테스트케이스 결과 출력
            // 제한 칼로리 L 이하에서 얻을 수 있는 최대 점수는 dp[L]
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(tc).append(" ").append(dp[L]);
            System.out.println(sb);
        }
    }
}
