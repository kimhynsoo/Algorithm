import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 문자열을 문자 배열로 변환
        char[] str1 = br.readLine().toCharArray();
        char[] str2 = br.readLine().toCharArray();
        
        int len1 = str1.length;
        int len2 = str2.length;

        // dp[i][j] : str1의 앞 i개와 str2의 앞 j개까지 고려했을 때의 LCS 길이
        int [][] dp = new int[len1+1][len2+1];

        // LCS 동적 계획법
        for(int i = 1; i <= len1; i++){
            for(int j = 1; j <= len2; j++){
                // 문자가 같다면, 왼쪽 위 값 + 1
                if(str1[i-1] == str2[j-1]){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } 
                // 문자가 다르면, 위쪽 혹은 왼쪽 중 큰 값 선택
                else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // 최종 LCS 길이 출력
        System.out.println(dp[len1][len2]);
    }
}
