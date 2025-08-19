import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        int [][] board = new int[101][101];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        ArrayList <Integer> r_list =new ArrayList<>();
        ArrayList <Integer> c_list = new ArrayList<>();
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            r_list.add(r);
            int c = Integer.parseInt(st.nextToken());
            c_list.add(c);
            for(int j=r; j<r+10; j++){
                for(int k =c; k<c+10; k++){
                    board[j][k]=1;
                }
            }
        }
        Collections.sort(r_list);
        Collections.sort(c_list);
        int [] dx ={-1,1,0,0};
        int [] dy ={0,0,1,-1};
        int cnt=0;
        for(int i=r_list.get(0); i<r_list.get(N-1)+10; i++){
            for(int j=c_list.get(0); j<c_list.get(N-1)+10; j++){
                if(board[i][j]==1){
                    for(int k=0; k<4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx<0||ny<0){
                            cnt++;
                            continue;
                        } 
                        if(board[nx][ny]==0){
                            cnt++;
                        }

                    }
                }
            }
        }
        System.out.println(cnt);


    }
}
