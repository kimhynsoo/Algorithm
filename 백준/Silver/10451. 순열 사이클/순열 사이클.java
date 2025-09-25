import java.util.Scanner;

public class Main {
    static int [] nums;
    static boolean[] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        for(int t=0; t<N; t++){
            int K = sc.nextInt();

            nums=new int[K+1];
            visited=new boolean[K+1];
            for(int i=1; i<=K; i++){
                nums[i]=sc.nextInt();
            }

            int cnt=0;
            for(int i=1; i<=K; i++){
                if(!visited[i]){
                    dfs(i);
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }
    static void dfs(int x){
        visited[x]=true;
        int next = nums[x];
        if(!visited[next]){
            dfs(next);
        }
    }
}
