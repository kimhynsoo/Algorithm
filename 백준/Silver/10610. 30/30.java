import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();

        int sum=0;
        boolean hasZero=false;
        char[] nums = N.toCharArray();

        for(char num : nums){
            int n = num-'0';
            sum+=n;
            if(n==0) hasZero=true;
        }

        if(!hasZero || sum%3!=0){
            System.out.println(-1);
            return;
        }

        Arrays.sort(nums);
        StringBuilder sb = new StringBuilder(new String(nums));
        System.out.println(sb.reverse().toString());

    }
}
