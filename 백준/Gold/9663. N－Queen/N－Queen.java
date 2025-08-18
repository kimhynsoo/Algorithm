import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int board[][];
    static int col[];
    static int cnt=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        board = new int[N][N];
        col = new int[N];
        nQueen(0);
        System.out.println(cnt);

    }
    static void nQueen(int row){
        if(row ==N){
            cnt++;
            return;
        }

        for(int i=0; i<N; i++){
            col[row] = i;
            if(isOK(row)){
                nQueen(row+1);
            }
        }
    }

    static boolean isOK(int row){
        for(int i=0; i<row; i++){
            if(col[i]==col[row]) return false; //같은 행
            if(Math.abs(row-i)==Math.abs(col[row]-col[i])) return false; //대각선
        }
        return true;
    }
}