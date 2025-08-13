import java.util.Arrays;
import java.util.Scanner;

public class Main {
 
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N  = sc.nextInt();
        int target = sc.nextInt();

        int [][] map = new int [N][N];

        int x=N/2;
        int y=N/2;

        map[x][y]=1;
        int num=1;

        int cnt=1;
        int target_x=0,target_y=0;
        
        while(true) {
			for(int i=0; i<cnt; i++) {
				map[x--][y] = num++;
			}
			if(num-1 == N*N) break;
			for(int i=0; i<cnt; i++) {
				map[x][y++] = num++;
			}
			
			cnt++;
			for(int i=0; i<cnt; i++) {
				map[x++][y] = num++;
			}
			
			for(int i=0; i<cnt; i++) {
				map[x][y--] = num++;
			}
			cnt++;
		}
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(target==map[i][j]){
                    target_x=i+1;
                    target_y=j+1;
                }
                sb.append(map[i][j] + " ");
            }
            sb.append("\n");
        }
        sb.append(target_x+" "+target_y);
        System.out.println(sb);

        
        
    }
}
