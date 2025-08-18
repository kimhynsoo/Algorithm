import java.io.*;
import java.util.*;
class Node{
    int x,y;
    public Node(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
}
public class Main {
    static int N,L,R;
    static int [][] A;
    static int move=0;
    static int[] dx={0,1,-1,0};
    static int[] dy={-1,0,0,1};
    static ArrayList<Node> list;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st=new StringTokenizer(br.readLine().trim());
        N=Integer.parseInt(st.nextToken());
        L=Integer.parseInt(st.nextToken());
        R=Integer.parseInt(st.nextToken());
        A=new int [N][N];
        for(int i=0; i<N; i++)
        {
            st=new StringTokenizer(br.readLine().trim());
            for(int j=0; j<N; j++)
            {
                A[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(move());

    }

    public static int move()
    {
        int result=0;
        while(true)
        {
            boolean isMove=false;
            visited = new boolean[N][N];
            for(int i=0; i<N; i++)
            {
                for(int j=0; j<N; j++)
                {
                    if(!visited[i][j])
                    {
                        int sum=bfs(i,j);
                        if(list.size()>1)
                        {
                            changePopulation(sum);
                            isMove=true;
                        }

                    }

                }
            }
            if(!isMove) return result;
            result++;
        }

    }

    static int bfs(int x,int y)
    {
        Queue<Node> q = new LinkedList<>();
        list = new ArrayList<>();

        q.offer(new Node(x,y));
        list.add(new Node(x,y));
        visited[x][y]=true;

        int sum = A[x][y];

        while (!q.isEmpty())
        {
            Node cur = q.poll();

            for(int i=0; i<4; i++)
            {
                int nx=cur.x+dx[i];
                int ny=cur.y+dy[i];
                if(nx>=0&&ny>=0&&nx<N&&ny<N&&!visited[nx][ny])
                {
                    int diff = Math.abs(A[cur.x][cur.y]-A[nx][ny]);
                    if(L<=diff && diff<=R) {
                        q.offer(new Node(nx, ny));
                        list.add(new Node(nx,ny));
                        sum+=A[nx][ny];
                        visited[nx][ny]=true;
                    }
                }
            }
        }

        return sum;
    }

    static void changePopulation(int sum)
    {
        int avg=sum/ list.size();
        for(Node n : list)
        {
            A[n.x][n.y]=avg;

        }
    }


}