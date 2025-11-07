

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean e = false;
        int sum = 0;
        int min = 101;
        for (int i = 0; i < 7; i++) {
            int n = sc.nextInt();
            if (n % 2 != 0) {
                e = true;
                sum += n;
                if (min > n) {
                    min = n;
                }
            }

        }
        if (e) {
            System.out.println(sum);
            System.out.println(min);
        } else {
            System.out.println(-1);
        }
    }
}