import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //입력값 처리하는 BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //결과값 출력하는 BufferedWriter
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        //문자열 길이
        int len = str.length();
        //i번째까지 암호를 해석했을 때 나오는 경우의 수
        int[] DP = new int[len+1];
        int div = 1000000;
        //시작 값이 0이 아니면 항상 1인 값 초기화
        DP[1] = DP[0] = 1;
        //0으로 시작하면 암호를 해석할 수 없으므로 0으로 설정
        if(str.charAt(0) == '0'){
            DP[len] = 0;
        }else{
            //점화식을 통한 1 ~ n번째까지 암호 코드 해석 경우의 수 계산
            for(int i=2; i<=len;i++){
                //앞의 암호 값
                int preC = Character.getNumericValue(str.charAt(i-2));
                //현재 암호 값
                int curC =  Character.getNumericValue(str.charAt(i-1));
                //현재 암호가 0인 값일 때
                if(curC == 0){
                    //암호가 10, 20을 만들 때
                    if(preC == 1 || preC == 2){
                        DP[i] = DP[i-2];
                    }
                }else{	//현재 암호가 1 ~ 9일 때
                    // 11, 12, .., 91, 92일 
                    // 앞에 암호 포함 경우, 현재 암호만 경우 2가지 경우일 때
                    if(preC == 1 || (preC == 2 && curC <= 6)){
                        DP[i] = (DP[i-1] + DP[i-2]) % div;
                    }else{	//현재 암호로만 알파벳 해석 가능
                        DP[i] = DP[i-1];
                    }
                }
            }
        }
        //모든 암호코드를 해석했을 때 경우의 수 BufferedWriter 저장
        bw.write(String.valueOf(DP[len]));
        bw.flush();		//결과 출력
        bw.close();
        br.close();

    }
}