import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        int a=1;
        int b=1;
        for(int i=2; i<=N; i++){
            int temp = (a+b)%10007;
            a = b%10007;
            b = temp;
        }
        
        System.out.println(b);
    }
}
