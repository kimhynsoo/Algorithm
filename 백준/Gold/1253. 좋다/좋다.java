import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int []arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(N<=2){
            System.out.println(0);
            return;
        }
        Arrays.sort(arr);
        int cnt=0;
        for(int i=0; i<N; i++){
            int start=0, end=N-1, sum;
            while (start<end) {
                if(i == start){
                    start++;
                }else if(i == end){
                    end--;
                }
                if(start==end){
                    break;
                }
                sum = arr[start]+arr[end];

                if(arr[i] < sum){
                    end--;
                }else if(arr[i] > sum){
                    start++;
                }else{
                    cnt++;
                    break;
                }
                
            }
        }
        System.out.println(cnt);
        
        
    }

    

}
