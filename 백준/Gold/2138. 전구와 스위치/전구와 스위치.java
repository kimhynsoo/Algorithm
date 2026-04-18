import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
 
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int N = Integer.parseInt(br.readLine());
 
        char[] bulbs = br.readLine().toCharArray();	
        char[] pressFirstBulbs = Arrays.copyOf(bulbs,bulbs.length); //첫번째 전구를 누른 경우의 수
        
        //1번째 스위치를 눌러 전구 모양 번경
        pressFirstBulbs[0] = bulbs[0] == '1' ? '0' : '1';;
        pressFirstBulbs[1] = bulbs[1] == '1' ? '0' : '1';;
 
        char[] target = br.readLine().toCharArray();
 
        int count = 0;	//첫번째 스위치를 안 누른 경우의 수
        int pressFirstCount = 1; //첫번째 스위치를 누른 경우의 수
 
		//i번째의 전구를 확인해서 다르면 같도록 변경		
        for(int i=1;i<bulbs.length;i++){
           //첫번째 전구를 안누른 경우의 전구 변경
            if(bulbs[i-1] != target[i-1]){
                bulbs[i-1] = bulbs[i-1] == '1' ? '0' : '1';
                bulbs[i] = bulbs[i] == '1' ? '0' : '1';
 
                if(i+1 < bulbs.length){
                    bulbs[i+1] = bulbs[i+1] == '1' ? '0' : '1';
                }
 
                count++;
            }
			//첫번째 전구를 누른 경우의 전구 변경
            if(pressFirstBulbs[i-1] != target[i-1]){
                pressFirstBulbs[i-1] = pressFirstBulbs[i-1] == '1' ? '0' : '1';
                pressFirstBulbs[i] = pressFirstBulbs[i] == '1' ? '0' : '1';
 
                if(i+1 < pressFirstBulbs.length){
                    pressFirstBulbs[i+1] = pressFirstBulbs[i+1] == '1' ? '0' : '1';
                }
 
                pressFirstCount++;
            }
        }
        
        //모든 경우의 수 중 맞는 것의 회수를 출력
        if(isSame(bulbs,target)){
            System.out.println(count);
        }else if(isSame(pressFirstBulbs,target)){
            System.out.println(pressFirstCount);
        }else{
            System.out.print(-1);
        }
    }
	
    //2개의 배열이 같은지 확인하는 메소드
    public static boolean isSame(char[]a, char[]b){
        for(int i=0;i<a.length;i++){
            if(b[i] != a[i]){
                return false;
            }
        }
 
        return true;
    }
}