import java.io.*;
import java.util.*;

public class Main {
    static int K, W, H;
    static int[][] board;
    static boolean[][][] visited;
    
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    static int[] hx = {-2,-1,1,2,2,1,-1,-2};
    static int[] hy = {1,2,2,1,-1,-2,-2,-1};
    
    static class Node {
        int x, y, k, dist;
        Node(int x, int y, int k, int dist) {
            this.x=x; this.y=y; this.k=k; this.dist=dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        
        board = new int[H][W];
        visited = new boolean[H][W][K+1];
        
        for(int i=0;i<H;i++){
            st=new StringTokenizer(br.readLine());
            for(int j=0;j<W;j++){
                board[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        
        int ans = bfs();
        System.out.println(ans);
    }
    
    static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(0,0,K,0));
        visited[0][0][K]=true;
        
        while(!q.isEmpty()){
            Node cur=q.poll();
            if(cur.x==H-1 && cur.y==W-1) return cur.dist;
            
            // 원숭이 이동 (상하좌우)
            for(int i=0;i<4;i++){
                int nx=cur.x+dx[i];
                int ny=cur.y+dy[i];
                if(nx<0||ny<0||nx>=H||ny>=W) continue;
                if(board[nx][ny]==1) continue;
                if(!visited[nx][ny][cur.k]){
                    visited[nx][ny][cur.k]=true;
                    q.offer(new Node(nx,ny,cur.k,cur.dist+1));
                }
            }
            
            // 말 이동 (k 남아있을 때만)
            if(cur.k>0){
                for(int i=0;i<8;i++){
                    int nx=cur.x+hx[i];
                    int ny=cur.y+hy[i];
                    if(nx<0||ny<0||nx>=H||ny>=W) continue;
                    if(board[nx][ny]==1) continue;
                    if(!visited[nx][ny][cur.k-1]){
                        visited[nx][ny][cur.k-1]=true;
                        q.offer(new Node(nx,ny,cur.k-1,cur.dist+1));
                    }
                }
            }
        }
        return -1;
    }
}
