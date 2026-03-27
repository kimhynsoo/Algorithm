import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        System.out.println((int)Math.pow(2, N)-1);
        hanoi(N, 1, 2, 3);

        System.out.println(sb.toString());


    }
    

    static void hanoi(int N, int from,int via,int to){
        if(N==1){
            sb.append(from + " "+to+"\n");
            return;
        }

        hanoi(N-1, from, to, via);
        sb.append(from + " "+to+"\n");
        hanoi(N-1, via, from, to);
    }
}