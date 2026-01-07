import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int [][]arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        
        arr = new int[9][9];

        ArrayList<int[]> empty = new ArrayList<>();
        for(int i=0; i<9; i++){
            String line = br.readLine();
            for(int j=0; j<9; j++){
                int n = line.charAt(j)-'0';
                arr[i][j] = n;
                if ( n == 0){
                    empty.add(new int[]{i,j});
                }
            }
        }
        dfs(0, empty);

        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }



        

    }
    static boolean end = false;
    static void dfs(int depth, ArrayList<int[]> empty){
        if(end) return;
        if (depth == empty.size()) {
            end = true;
            return;
        }

        int [] cur = empty.get(depth);
        int r = (cur[0]/3)*3;
        int c = (cur[1]/3)*3;
        for(int num=1; num<=9; num++){
            boolean possible = true;
            for(int i=0; i<9; i++){
                if(arr[i][cur[1]]==num){
                    possible = false;
                    break;
                }
            }
            if(possible==false) continue;
            for(int j=0; j<9; j++){
                if(arr[cur[0]][j]==num){
                    possible=false;
                    break;
                }
            }
            if(possible==false) continue;
            outer : for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if(arr[r+i][c+j]==num){
                        possible=false;
                        break outer;
                    }
                }
            }
            if(possible==false) continue;
            arr[cur[0]][cur[1]]=num;
            
            dfs(depth+1, empty);
            if(end) return;
            arr[cur[0]][cur[1]]=0;
        }
    }
}
