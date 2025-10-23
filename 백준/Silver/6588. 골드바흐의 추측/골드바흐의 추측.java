import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static boolean[] isPrime;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        isPrime = new boolean[1000001];
        for(int i=2; i<=1000000; i++){
            isPrime[i]=true;
        }
        int sqrt = (int)Math.sqrt(1000000);
        for(int i=2; i<= sqrt; i++){
            if(isPrime[i]){
                for(int j= i*i; j<=1000000; j+=i){
                    
                    isPrime[j]=false;
                }

            }
        
            
        }
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0 ) break;
            boolean flag = false;
            for(int i=2; i<=N/2; i++){
                
                if(isPrime[i]&&isPrime[N-i]){
                    flag=true;
                    System.out.println(N + " = " + i + " + " + (N - i));
                    break;
                }
            }
            if(!flag){
                System.out.println("Goldbach's conjecture is wrong.");
            }

        }
        
    }
}
