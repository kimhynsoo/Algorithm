import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); // 전체 학생 수
        StringTokenizer st = new StringTokenizer(br.readLine());

        int order[] = new int[N+1]; // 각 번호 학생의 현재 위치(인덱스)를 저장할 배열

        // 입력받은 순서대로 각 학생 번호의 위치를 저장
        for(int i=0; i<N; i++){
            int num = Integer.parseInt(st.nextToken());
            order[num] = i; // 학생 'num'이 현재 줄에서 i번째에 서 있음
        }

        int max = 1; // 가장 긴 연속된 증가 부분 수열 길이 (초기값 1)
        int cnt = 0; // 현재 연속된 증가 수열의 길이

        // 학생 번호 1부터 N까지 순서대로 확인
        for(int i=1; i<=N; i++){
            // 현재 번호의 위치가 이전 번호보다 뒤에 있다면 → 연속 증가
            if(order[i] > order[i-1]){
                if(++cnt > max){ // 연속 길이 증가 후 최대값 갱신
                    max = cnt;
                }
            } else {
                // 연속이 끊기면 다시 1로 초기화
                cnt = 1;
            }
        }

        // N이 1이면 이동할 필요 없음
        if(N == 1){
            System.out.println(0);
        } else {
            // 전체 인원 중 가장 긴 연속 구간(max)을 제외한 나머지를 옮겨야 함
            System.out.println(N - max);
        }
    }
}
