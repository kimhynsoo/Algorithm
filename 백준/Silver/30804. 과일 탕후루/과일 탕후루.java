import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int [] nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        int kindCnt=0;
        int[] fruit = new int[10];
        int left=0;
        int max_len=0;
        for(int right=0; right<N; right++){
            if(fruit[nums[right]]==0) kindCnt++;
            fruit[nums[right]]++;
            while (kindCnt>2) {
                fruit[nums[left]]--;
                if(fruit[nums[left]]==0) kindCnt--;
                left++;
            }
            max_len=Integer.max(max_len, right-left+1);
        }
        System.out.println(max_len);
    }
}