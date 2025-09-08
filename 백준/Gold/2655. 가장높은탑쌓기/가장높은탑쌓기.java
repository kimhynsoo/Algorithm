import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N; // 벽돌 개수
    
    // 벽돌 클래스 정의
    static brick[] bricks;
    static class brick implements Comparable<brick>{
        int extent, height, weight; // 밑면 넓이, 높이, 무게
        int no; // 입력에서의 번호 (출력할 때 필요)
        
        public brick(int no,int extent, int height, int weight) {
            this.no = no;
            this.extent = extent;
            this.height = height;
            this.weight = weight;
        }
        
        // 정렬 기준: 밑면 넓이가 큰 벽돌이 앞으로 오도록 (내림차순)
        @Override
        public int compareTo(brick o) {
            return o.extent - this.extent;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 벽돌 개수 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        bricks = new brick[N+1];
        bricks[0] = new brick(0, 0, 0, 0); // 0번 인덱스: 더미(dummy) 벽돌
    
        // 벽돌 정보 입력
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int extent = Integer.parseInt(st.nextToken()); // 밑면 넓이
            int height = Integer.parseInt(st.nextToken()); // 높이
            int weight = Integer.parseInt(st.nextToken()); // 무게
            bricks[i] = new brick(i, extent, height, weight);
        }

        // 밑면 넓이를 기준으로 정렬 (넓은 것 → 좁은 것)
        Arrays.sort(bricks, 1, N+1);

        int[] dp = new int[N+1];      // dp[i] = i번째 벽돌이 맨 위일 때 쌓을 수 있는 최대 높이
        int[] parent = new int[N+1];  // parent[i] = i번째 벽돌 아래에 있는 벽돌의 index (경로 추적용)

        int maxHeight = 0;  // 최종 최대 높이
        int lastIdx = 0;    // 최대 높이를 만들 수 있는 마지막 벽돌의 index
        
        // DP 수행
        for(int i=1; i<=N; i++){
            dp[i] = bricks[i].height; // 자기 자신만 쌓았을 때의 높이
            parent[i] = 0; // 초기에는 부모 없음

            // 이전 벽돌들 중 조건을 만족하는 경우만 고려
            for(int j=0; j<i; j++){
                if(bricks[i].weight < bricks[j].weight) {  // 위에 올리려면 더 가벼워야 함
                    // j 벽돌 위에 i 벽돌을 올릴 수 있다면 dp 갱신
                    if(dp[i] < dp[j] + bricks[i].height){
                        dp[i] = dp[j] + bricks[i].height;
                        parent[i] = j; // i의 아래에는 j 벽돌이 온다
                    }
                }
            }

            // 지금까지의 최대 높이 갱신
            if(dp[i] > maxHeight){
                maxHeight = dp[i];
                lastIdx = i;
            }
        }
        
        // 경로 역추적
        ArrayList<Integer> res = new ArrayList<>();
        while (lastIdx != 0) {
            res.add(bricks[lastIdx].no); // 실제 입력 순서 번호 저장
            lastIdx = parent[lastIdx];   // 부모로 이동
        }

        // 결과 출력
        System.out.println(res.size()); // 사용된 벽돌 개수
        for(int i : res){
            System.out.println(i); // 벽돌 번호 (아래에서 위 순서대로 출력)
        }
    }
}
