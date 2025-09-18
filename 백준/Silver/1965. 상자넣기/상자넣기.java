import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //lis

    static int N;
    static int[] arr,lis;

    static int lower_bound(int end,int n){
        int start =0;
        while(start<end){
            int mid = (start+end)/2;

            if(lis[mid]<n){
                start=mid+1;
            }else{
                end=mid;
            }
        }
        return start;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        lis = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        lis[0]=arr[0];
        int idx=0;
        for(int i=1; i<N; i++){
            if(lis[idx]<arr[i]){
                lis[++idx]=arr[i];
            }else if(lis[idx]>arr[i]){
                int ii = lower_bound(idx, arr[i]);
                lis[ii] = arr[i];
            }
        }
        
        System.out.println(idx+1);
    }
}
