import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] nums;
    static int max=Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int [] op = new int[4];
    public static void main(String[] args)  throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        nums = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            nums[i]=Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            op[i]=Integer.parseInt(st.nextToken());
        }
        dfs(1, nums[0]);
        System.out.println(max);
        System.out.println(min);

    }

    static void dfs(int index,int result){
        if(index == N){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for(int i=0; i<4; i++){
            if(op[i]>0){
                op[i]--;
                dfs(index+1, calc(i,result,nums[index]));
                op[i]++;
            }
        }
    }
    static int calc(int op,int a,int b){
        if(op==0) return a+b;
        if(op==1) return a-b;
        if(op==2) return a*b;
        if(op==3) return a/b;
        return 0;
    }
}
