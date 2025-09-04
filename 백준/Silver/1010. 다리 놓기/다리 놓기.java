import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // M사이트 중 N개의 사이트를 선택할 수 있는 모든 경우의 수.
    // 조합의 개수를 구하는 것 과 같다.
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int [][] map = new int[N+1][K+1];

            for(int i=0; i<=N; i++ ){
                for(int j=0; j<= Math.min(i, K); j++){
                    if(j==0 || i==j){
                        map[i][j]=1;
                    }
                    else{
                        map[i][j] = map[i-1][j-1] + map[i-1][j];
                    }
                }
            }

            System.out.println(map[N][K]);
        }
    }
}
