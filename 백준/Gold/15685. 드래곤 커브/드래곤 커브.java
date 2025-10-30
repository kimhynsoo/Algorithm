import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static final int RIGHT =0;
    static final int UP =1;
    static final int LEFT =2;
    static final int DOWN =3;
    static boolean [][]map = new boolean [101][101];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); 

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            dragon(x, y, d, g);
        }
        System.out.println(find_square());
        
        
    }
    static void dragon(int x,int y,int d, int g){
        ArrayList<Integer> dist = new ArrayList<>();
        dist.add(d); //0세대
        
        for(int i=0; i<g; i++){ //세대만큼
            int size = dist.size();
            for(int s=size-1; s>=0; s--){
                //선분 하나씩을 봤을 때 전 세대의 선분은 반시계 방향으로 간다.
                dist.add((dist.get(s)+1)%4); 
            }
        }

        map[x][y]=true;
        for(int batch : dist){
            switch (batch) {
                case RIGHT:
                    map[++x][y]=true;
                    break;
                case UP:
                    map[x][--y]=true;
                    break;
                case LEFT:
                    map[--x][y]=true;
                    break;
                case DOWN:
                    map[x][++y]=true;
                    break;
                
            }
        }
    }

    static int find_square(){
        int cnt =0;
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(map[i][j]&&map[i+1][j]&&map[i][j+1]&&map[i+1][j+1]) cnt++;
            }
        }


        return cnt;
    }
}