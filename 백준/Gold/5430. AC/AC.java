import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args)throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            Deque<Integer> deque = new LinkedList<>();
            String line = br.readLine().trim();
            int len = line.length();
            int N = Integer.parseInt(br.readLine());
            String nums = br.readLine().trim();
            nums = nums.substring(1, nums.length()-1);
            String [] input = nums.split(",");
            
            for(int i=0; i<N; i++){
                deque.add(Integer.parseInt(input[i]));
            }
            boolean reverse = false;
            boolean error =false;
            for(int i=0; i<len; i++){
                
                if(line.charAt(i)=='R'){
                    //뒤집기
                    reverse = !reverse;
                }else{
                    //제거
                    if(deque.isEmpty()){
                        System.out.println("error");
                        error=true;
                        break;
                    }
                    if(reverse){
                        deque.removeLast();
                    }
                    else{
                        deque.removeFirst();
                    }
                }
            }
            if(!error){
                if(reverse ){
                    ArrayList<Integer> temp = new ArrayList<>(deque);
                    Collections.reverse(temp);
                    deque = new LinkedList<>(temp);
                }
                StringJoiner sj = new StringJoiner(",","[","]");
                for(int x : deque){
                    sj.add(String.valueOf(x));
                }
                System.out.println(sj);

            }
            
            
        }
    }
}
