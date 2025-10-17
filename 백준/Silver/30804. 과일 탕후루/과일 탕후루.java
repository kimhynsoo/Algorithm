import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int [] nums = new int[N];
        for(int i=0; i<N; i++){
            nums[i] = sc.nextInt();
        }
        Map<Integer,Integer> map = new HashMap<>();

        int left=0;
        int max_len=0;
        for(int right=0; right<N; right++){
            map.put(nums[right],map.computeIfAbsent(nums[right],k->0)+1);
            while (map.size()>2) {
                map.compute(nums[left], (k,v)->v-1);
                if(map.get(nums[left])==0) map.remove(nums[left]);
                left++;
            }
            max_len=Integer.max(max_len, right-left+1);
        }
        System.out.println(max_len);
    }
}