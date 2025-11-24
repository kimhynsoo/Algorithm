
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] graph;
    static int N;
    static int dr[] = { -1, 1, 0, 0 };
    static int dc[] = { 0, 0, -1, 1 };

    static class Virus implements Comparable<Virus> {
        int r, c, v_num;

        public Virus(int r, int c, int v_num) {
            this.r = r;
            this.c = c;
            this.v_num = v_num;
        }

        @Override
        public int compareTo(Virus o) {
            return this.v_num - o.v_num;
        }

    }

    static Queue<Virus> viruses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        graph = new int[N + 1][N + 1];
        viruses = new ArrayDeque<>();
        ArrayList<Virus> init = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                graph[i][j] = num;
                if (num != 0) {
                    init.add(new Virus(i, j, num));
                }
            }
        }
        Collections.sort(init);
        for (Virus v : init)
            viruses.offer(v);

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        while (S-- > 0) {

            bfs();

        }
        System.out.println(graph[row][col]);
    }

    static void bfs() {
        int size = viruses.size();
        while (size-- > 0) {
            Virus cur = viruses.poll();
            int r = cur.r;
            int c = cur.c;
            int v_num = cur.v_num;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr <= 0 || nc <= 0 || nr > N || nc > N || graph[nr][nc] != 0)
                    continue;

                graph[nr][nc] = v_num;
                viruses.offer(new Virus(nr, nc, v_num));
            }
        }

    }

}
