import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int index=1;
        while (true) {
            String line = br.readLine();
            if(line.charAt(0)=='-') break;
            
            int res=0;
            Stack<Character> stack = new Stack<>();
            int len = line.length();
            for(int i=0; i<len; i++){
                char c =line.charAt(i);
                if(c=='{'){
                    stack.push(c);
                }else{
                    if(stack.isEmpty()){
                        stack.push('{');
                        res++;
                    }else{

                        stack.pop();
                    }
                }
            }
            res+=stack.size()/2;
            System.out.printf("%d. %d\n",index++,res);
        }
    }
}