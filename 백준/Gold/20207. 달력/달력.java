import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int days_of_year=365;
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] day = new int[days_of_year+1];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            for(int j = s; j<=e; j++){
                day[j]++;
            }
        }
        int sum=0,width=0,height=0;
        for(int i=1; i<=days_of_year; i++){
            if(day[i]==0){
                sum+=width*height;
                width=height=0;
                continue;
            }
            width++;
            height=Math.max(height, day[i]);
        }
        sum+=width*height;

        System.out.println(sum);
    }
}
