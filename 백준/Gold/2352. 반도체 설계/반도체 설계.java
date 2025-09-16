
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr, LIS;

    static int lower_bound(int[]a,int end, int n){
        int start=0;
        while (start<end) {
            int mid = (start+end)/2;

            if(a[mid]<n){
                start = mid+1;
            }
            else{
                end = mid;
            }
            
        }
        return start;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i]= Integer.parseInt(st.nextToken());
        }
        LIS = new int[N];
        LIS[0]=arr[0];
        int index =0;
        for(int i=1; i<N; i++){
            if(arr[i]>LIS[index]){
                LIS[++index]=arr[i];
            }else if(arr[i]<LIS[index]){
                int ii = lower_bound(LIS, index, arr[i]);
                LIS[ii] = arr[i];
            }
        }
        System.out.println(index+1);
    }
}
