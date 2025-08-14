import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    //부분집합
    static int N;
    static int[][] flavor;
    static boolean[] isSelected;
    static int min_diff=Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        flavor = new int[N][2];

        isSelected = new boolean[N];

        for(int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            flavor[i][0]=Integer.parseInt(st.nextToken());
            flavor[i][1]=Integer.parseInt(st.nextToken());
        }
        subset(0);
        System.out.println(min_diff);

    }
    static void subset(int cnt){
        if(cnt==N){
            int sum_sin=1;
            int sum_ssn=0;
            for(int i=0; i<N; i++){
                if(isSelected[i]){
                    sum_sin*=flavor[i][0];
                    sum_ssn+=flavor[i][1];
                }
            }
            if(sum_sin==1 &&sum_ssn==0) return;
            int diff = Math.abs(sum_ssn-sum_sin);
            min_diff=Math.min(min_diff, diff);
            return;
        }

        isSelected[cnt]=true;
        subset(cnt+1);
        isSelected[cnt]=false;
        subset(cnt+1);
    }
}
