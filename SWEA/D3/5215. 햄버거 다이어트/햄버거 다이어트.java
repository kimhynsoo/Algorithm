import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N,L;
    static int score[];
    static int kcal[];
    static int max_score =0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for(int tc = 1 ; tc<=T ; tc ++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            score = new int[N];
            kcal = new int[N];
            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                score[i]=Integer.parseInt(st.nextToken());
                kcal[i] = Integer.parseInt(st.nextToken());
            }
            max_score=0;
            subset(0, 0, 0);
            System.out.printf("#%d %d\n",tc,max_score);

        }

    }
    static void subset(int index,int sc, int kc){
        if(kc>L){
            return;
        }
        if(index == N){
            max_score=Math.max(sc, max_score);
            return;
        }
        subset(index+1, sc, kc);
        subset(index+1, sc+score[index], kc + kcal[index]);
    }


}
