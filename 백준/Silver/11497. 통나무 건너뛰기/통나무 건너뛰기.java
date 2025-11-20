import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        for(int t=0; t<N; t++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int []arr = new int[M];
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<M; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);
            int []result = new int[M];
            int left=0;
            int right=M-1;
            for(int i=0; i<M; i++){
                if(i%2==0){
                    result[left]=arr[i];
                    left++;
                }else{
                    result[right]=arr[i];
                    right--;
                }
            }
            int max=Math.abs(result[0]-result[M-1]);
            for(int i=0; i<M-1; i++){
                max = Math.max(max, Math.abs(result[i]-result[i+1]));
            }
            System.out.println(max);
            
        }
    }
}
