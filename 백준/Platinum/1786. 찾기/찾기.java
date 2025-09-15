import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력: 텍스트 T, 패턴 P
        String T = br.readLine(); // 전체 문자열 (Text)
        String P = br.readLine(); // 찾을 문자열 (Pattern)
        int N = T.length(); // 텍스트 길이
        int M = P.length(); // 패턴 길이

        // LPS(Longest Prefix Suffix) 배열
        // lps[i] : P[0..i]까지 고려했을 때
        // 접두사(Prefix) == 접미사(Suffix)가 되는 최대 길이
        int [] lps = new int[M];

        int cnt = 0; // 패턴 등장 횟수
        ArrayList<Integer> location = new ArrayList<>(); // 패턴이 등장하는 시작 위치(1-based index) 저장

        // 패턴에 대한 LPS 배열 계산
        computeLPS(P, lps);

        // KMP 탐색
        int i = 0; // 텍스트 인덱스
        int j = 0; // 패턴 인덱스
        while (i < N) {
            // 1) 현재 문자가 일치하면 i, j 모두 증가
            if(T.charAt(i) == P.charAt(j)){
                i++;
                j++;
            }

            // 2) 패턴 전체 일치 (j == M)
            if(j == M ){
                cnt++; // 카운트 증가
                location.add(i-j+1); // 시작 위치 저장 (문제에서 1-based index 요구하므로 +1)
                j = lps[j-1]; // 다음 탐색 위치: 직전 LPS 값
            }
            // 3) 불일치 발생
            else if(i < N && T.charAt(i) != P.charAt(j)){
                // j > 0 이면 패턴 인덱스를 LPS로 이동
                if(j != 0){
                    j = lps[j-1];
                }
                // j == 0 이면 텍스트 인덱스만 이동
                else{
                    i++;
                }
            }
        }

        // 결과 출력
        System.out.println(cnt); // 패턴 등장 횟수
        StringBuilder sb = new StringBuilder();
        for(int loc : location) sb.append(loc).append(" "); // 등장 위치들
        System.out.println(sb.toString());// 마지막 공백 제거 후 출력
    }

    /**
     * 패턴 문자열 P에 대한 LPS(Longest Prefix Suffix) 배열 계산
     * 예: P = "ABABAC" → lps = [0, 0, 1, 2, 3, 0]
     */
    static void computeLPS(String P, int[] lps){
        int len = 0; // 현재까지 일치한 접두사/접미사 길이
        lps[0] = 0;  // 첫 문자의 lps는 항상 0
        int i = 1;   // 두 번째 문자부터 검사 시작

        while(i < P.length()){
            if(P.charAt(i) == P.charAt(len)){
                // 문자가 일치하면 len 증가 후 lps 갱신
                len++;
                lps[i] = len;
                i++;
            } else {
                if(len != 0){
                    // 이전 lps 값으로 돌아가 재검사
                    len = lps[len-1];
                } else {
                    // 일치 길이가 없으면 lps[i] = 0
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}
