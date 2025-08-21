import java.util.*;
import java.io.*;

public class Main {
    static int L, C;
    static char[] chars;
    static int vowelMask = 0;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        chars = new char[C];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < C; i++){
            chars[i] = st.nextToken().charAt(0);
        }

        // 모음 비트마스크 생성
        for(char c : new char[]{'a','e','i','o','u'}){
            vowelMask |= 1 << (c - 'a');
        }

        // 문자 배열을 미리 정렬하면 DFS에서 선택되는 조합도 정렬됨
        Arrays.sort(chars);

        // 조합 생성
        combination(0, 0, new char[L], 0, 0);

        
    }

    static void combination(int cnt, int start, char[] selected, int vow, int cons){
        if(cnt == L){
            
            if(vow >= 1 && cons >= 2){
                sb = new StringBuilder();
                for(char c : selected){
                    sb.append(c);
                }
                System.out.println(sb);
            }
            return;
        }

        for(int i = start; i < C; i++){
            selected[cnt] = chars[i];
            int bit = 1 << (chars[i] - 'a');
            if((bit & vowelMask) == 0){ // 자음
                combination(cnt + 1, i + 1, selected, vow, cons + 1);
            } else { // 모음
                combination(cnt + 1, i + 1, selected, vow + 1, cons);
            }
        }
    }
}
