import java.io.*;
import java.util.*;

public class Main {
    static int R, C, H;
    static int[][][] box;

    static class Node {
        int r, c, h, day;
        Node(int r, int c, int h, int day) {
            this.r = r; this.c = c; this.h = h; this.day = day;
        }
    }

    // 6방향: 상하좌우 + 위/아래
    static final int[] dr = {-1, 1, 0, 0, 0, 0};
    static final int[] dc = {0, 0, -1, 1, 0, 0};
    static final int[] dh = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken()); // M(가로)
        R = Integer.parseInt(st.nextToken()); // N(세로)
        H = Integer.parseInt(st.nextToken()); // H(높이)

        box = new int[R][C][H];
        ArrayDeque<Node> q = new ArrayDeque<>();

        int unripe = 0; // 안 익은 토마토 개수

        for (int h = 0; h < H; h++) {
            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++) {
                    int v = Integer.parseInt(st.nextToken());
                    box[r][c][h] = v;
                    if (v == 1) {
                        q.offer(new Node(r, c, h, 0)); // 시작점(이미 익은 토마토)
                    } else if (v == 0) {
                        unripe++;
                    }
                }
            }
        }

        // 시작부터 전부 익어 있으면 0
        if (unripe == 0) {
            System.out.println(0);
            return;
        }

        int answer = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();
            // 현재까지의 일수 기록(가장 늦게 익은 토마토의 day)
            answer = Math.max(answer, cur.day);

            for (int d = 0; d < 6; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                int nh = cur.h + dh[d];
                if (nr < 0 || nc < 0 || nh < 0 || nr >= R || nc >= C || nh >= H) continue;
                if (box[nr][nc][nh] == 0) {
                    box[nr][nc][nh] = 1; // 익힘 표시(방문 표시 겸용)
                    unripe--;
                    q.offer(new Node(nr, nc, nh, cur.day + 1));
                }
            }
        }

        // 다 익혔는지 최종 확인
        System.out.println(unripe == 0 ? answer : -1);
    }
}
