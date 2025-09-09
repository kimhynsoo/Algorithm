import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr, LIS;

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        LIS = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        LIS[0]=arr[0];
        int idx=0;
        for(int i=1; i<N; i++){
            if(LIS[idx]<arr[i]){
                LIS[++idx]=arr[i];
            }else if(LIS[idx]> arr[i]){
                int pos = lower_bound(LIS,idx,arr[i]);
                LIS[pos] = arr[i];
            }
        }
        int res = N - (idx+1);
        
        System.out.println(res);
    }
    static int lower_bound(int []dp, int end, int val){
        int start = 0;
        while (start<end) {
            int mid = (start+end)/2;
            if(dp[mid]<val){
                start = mid+1;
            }else{
                end=mid;
            }
            
        }
        return start;
    }
}