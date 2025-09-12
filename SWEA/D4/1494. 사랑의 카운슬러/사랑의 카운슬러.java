import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    
    static int N;
    static int[][] pairs;
    static int visited[];
    static long min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            pairs = new int [N][2];
            visited = new int[N];
            min = Long.MAX_VALUE;
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                pairs[i][0]=r;
                pairs[i][1]=c;
            }
            combination(0, 0);

            System.out.printf("#%d %d\n",tc,min);

        }
    }
    
    static void combination(int cnt, int start){
        if(cnt==N/2){
            long dx=0,dy=0;
            for(int i=0; i<N; i++){
                if(visited[i]==1){
                    dx+=pairs[i][0];
                    dy+=pairs[i][1];
                }else{
                    dx-=pairs[i][0];
                    dy-=pairs[i][1];
                }
            }
            long dist = dx*dx + dy*dy;
            min = Math.min(min, dist);
            return;
        }

        for(int i = start; i<N; i++){
            visited[i]=1;
            combination(cnt+1, i+1);
            visited[i]=0;
        }
    }
}
