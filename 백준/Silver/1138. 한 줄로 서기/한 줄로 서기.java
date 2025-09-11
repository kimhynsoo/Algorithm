import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static class Pair{
        int no, left;
        public Pair(int no, int left){
            this.no=no;
            this.left=left;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Pair [] order = new Pair[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            order[i] = new Pair(i+1, Integer.parseInt(st.nextToken()));
        }
        int [] ans = new int[N];
        for(int i=0;i<N;i++){
            int cnt = 0;
            for(int j=0;j<N;j++){
                if(ans[j]==0) cnt++;
                if(cnt==order[i].left+1){
                    ans[j] = order[i].no;
                    break;
                }
            }
        }
        for(int i=0;i<N;i++){
            System.out.print(ans[i]+" ");
        }
    }
}
