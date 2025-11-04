import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 2];

        st = new StringTokenizer(br.readLine());
        arr[0]=0;
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        arr[N+1]=L;
        Arrays.sort(arr);
        int left = 1;
        int right = L-1;
        while (left<=right) { //휴게소가 없는 구간에 mid 만큼의 길이를 최댓값으로 하는 주소를 몇개 세울 수 있냐
            int mid = (left+right)/2;
            int cnt=0;
            for(int i=0; i<N+1; i++){
                int dist = arr[i+1] - arr[i] -1; //최소 길이가 1이므로 1을 빼준다.
                cnt += dist/mid;
            }

            if(M>=cnt){//더 지을 수 있는데 최댓값을 줄일 수 있겠다.
                right = mid-1;
            }else{// 이 최댓값은 너무 작아서 내가 지을 수 있는 주유소 보다 더 필요해
                left = mid + 1;
            }
            
        }
        System.out.println(left); //최댓값 왜냐면 left>right 상태이므로

    }
}