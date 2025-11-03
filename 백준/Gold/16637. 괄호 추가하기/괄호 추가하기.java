import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int 연산자길이;
    static ArrayList<Integer> 숫자;
    static ArrayList<Character> 연산;
    public static void main(String[] args) throws IOException{
        BufferedReader 뭐 = new BufferedReader(new InputStreamReader(System.in));
        int 코딩개싫다 = Integer.parseInt(뭐.readLine());
        숫자=new ArrayList<>() ;
        연산=new ArrayList<>();

        String 뭐몰라=뭐.readLine();
        for(int i=0; i<코딩개싫다; i++){
            if(i%2==0){
                숫자.add(뭐몰라.charAt(i)-'0');
            }else{
                연산.add(뭐몰라.charAt(i));
            }
        }
        연산자길이 = 연산.size();

        디엪에스(숫자.get(0),0);

        System.out.println(결과);

    }
    static int 결과=Integer.MIN_VALUE;
    static void 디엪에스(int 현재결과, int 현재인덱스){
        if(현재인덱스==연산자길이){
            결과 = Math.max(현재결과, 결과);
            return;
        }

        디엪에스(계산(현재결과,숫자.get(현재인덱스+1),연산.get(현재인덱스)),현재인덱스+1);

        if(현재인덱스+2 <= 연산자길이){
            디엪에스(계산(현재결과,계산(숫자.get(현재인덱스+1),숫자.get(현재인덱스+2),
                                                                연산.get(현재인덱스+1)),연산.get(현재인덱스)),현재인덱스+2);
        }
    }

    static int 계산(int ㅋ,int ㅎ, char 연산자){
        if(연산자 == '*'){
            return ㅋ*ㅎ;
        }else if(연산자 =='+'){
            return ㅋ+ㅎ;
        }else{
            return ㅋ-ㅎ;
        }
    }
}
