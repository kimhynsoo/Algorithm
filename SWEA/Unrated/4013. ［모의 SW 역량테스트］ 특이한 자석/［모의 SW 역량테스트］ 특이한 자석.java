import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int K;                  // 회전 횟수
    static int[][] map;            // 자석 정보 [자석번호][8방향]
    static boolean[] visited;      // 방문 체크 (재귀 중복 방지)
    static int[] rotate_list;      // 각 자석이 회전할 방향 (1: 시계, -1: 반시계, 0: 회전 안 함)

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 수
        StringTokenizer st;

        for(int tc =1; tc<=T; tc++){
            K = Integer.parseInt(br.readLine());  // 회전 횟수
            map = new int[5][8];                 // 자석은 1~4번 사용

            // 자석 상태 입력
            for(int i=1; i<5; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<8; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // K번 회전 수행
            while (K-- > 0) {
                rotate_list = new int[5];    // 각 자석의 회전 여부/방향
                visited = new boolean[5];    // 중복 탐색 방지

                // 회전 명령 입력
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken()); // 회전시킬 자석 번호
                int d = Integer.parseInt(st.nextToken()); // 회전 방향

                // 연쇄 회전 여부 체크
                check(n, d);

                // 실제 회전 실행
                for(int i=1; i<5; i++){
                    if(rotate_list[i] != 0){
                        rotate(i, rotate_list[i]);
                    }
                }
            }

            // 점수 계산 (12시 방향 톱니값 * 2^(i-1))
            int res = 0;
            for(int i=1; i<5; i++){
                res += map[i][0] * (1 << (i-1));
            }

            // 출력
            System.out.printf("#%d %d\n", tc, res);
        }
    }

    /**
     * check : 특정 자석을 회전시킬 때
     * 양 옆 자석이 회전해야 하는지도 확인하여 rotate_list에 기록한다.
     */
    static void check(int n, int d){
        visited[n] = true;     // 현재 자석 방문 표시
        rotate_list[n] = d;    // 회전 방향 기록

        // 오른쪽 자석 확인 (n < 4일 때만 존재)
        if(n < 4){
            if(!visited[n+1]){ // 아직 확인 안 했으면
                // 현재 자석의 오른쪽(2번 톱니)와 옆 자석의 왼쪽(6번 톱니) 비교
                if(map[n][2] != map[n+1][6]){
                    check(n+1, -d); // 극이 다르면 반대 방향 회전
                }
            }
        }

        // 왼쪽 자석 확인 (n > 1일 때만 존재)
        if(n > 1){
            if(!visited[n-1]){
                // 현재 자석의 왼쪽(6번 톱니)와 옆 자석의 오른쪽(2번 톱니) 비교
                if(map[n][6] != map[n-1][2]){
                    check(n-1, -d); // 극이 다르면 반대 방향 회전
                }
            }
        }
    }

    static void rotate(int j, int d){
        if(d == 1){ // 시계 방향
            int temp = map[j][7]; // 마지막 원소 저장
            System.arraycopy(map[j], 0, map[j], 1, 7); // 오른쪽으로 shift
            map[j][0] = temp; // 맨 앞에 마지막 원소 삽입
        }else{      // 반시계 방향
            int temp = map[j][0]; // 첫 번째 원소 저장
            System.arraycopy(map[j], 1, map[j], 0, 7); // 왼쪽으로 shift
            map[j][7] = temp; // 맨 뒤에 첫 원소 삽입
        }
    }
}
