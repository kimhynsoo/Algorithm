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

        int conference[][] = new int[N][2];
        
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            conference[i][0]= Integer.parseInt(st.nextToken());
            conference[i][1]= Integer.parseInt(st.nextToken());
        }

        Arrays.sort(conference,(o1,o2)->{
            if(o1[1]==o2[1]){
                return o1[0]-o2[0];
            }
            return o1[1]-o2[1];
        });

        int end_time=conference[0][1];
        int cnt=1;
        for(int i=1; i<N; i++){
            if (conference[i][0]>=end_time) {
                cnt++;
                end_time=conference[i][1];
            }
        }
        System.out.println(cnt);
    }
}
