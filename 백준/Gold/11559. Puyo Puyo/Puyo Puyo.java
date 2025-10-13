import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
    static boolean[][] visited;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[12][6];

        // Input map setup
        for (int i = 0; i < 12; i++) {
            String line = br.readLine();
            for (int j = 0; j < 6; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        // Game loop
        int count = 0;
        while (true) {
            visited = new boolean[12][6];
            boolean popped = false; // Check if any Puyo is popped in this round

            // Check for clusters to pop
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.' && !visited[i][j]) {
                        if (bfs(i, j)) {
                            popped = true; // At least one Puyo was popped
                        }
                    }
                }
            }

            // If no Puyo was popped, the game ends
            if (!popped) break;

            // Apply gravity after popping
            gravity();

            count++;
        }

        System.out.println(count); // Output the number of rounds
    }

    static int dr[] = {-1, 1, 0, 0};
    static int dc[] = {0, 0, -1, 1};

    // Perform BFS to find clusters of Puyos
    static boolean bfs(int x, int y) {
        int cnt = 0;
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{x, y});
        q.offer(new int[]{x, y});
        visited[x][y] = true;
        char k = map[x][y];
        
        while (!q.isEmpty()) {
            cnt++;
            int[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nc < 0 || nr >= 12 || nc >= 6 || visited[nr][nc] || map[nr][nc] != k) continue;
                q.offer(new int[]{nr, nc});
                list.add(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }
        
        if (cnt >= 4) {
            // Pop the Puyos by turning them to '.'
            for (int[] pair : list) {
                map[pair[0]][pair[1]] = '.';
            }
            return true;
        }
        return false;
    }

    // Apply gravity to the map: Puyos fall down
    static void gravity() {
        // For each column, move Puyos down if there are gaps
        for (int c = 0; c < 6; c++) {
            // Collect all the Puyos in the current column
            List<Character> puyos = new ArrayList<>();
            for (int r = 0; r < 12; r++) {
                if (map[r][c] != '.') {
                    puyos.add(map[r][c]);
                }
            }

            // Start filling from the bottom
            int idx = 11;
            for (int i = puyos.size() - 1; i >= 0; i--) {
                map[idx--][c] = puyos.get(i);
            }

            // Fill the remaining spaces with '.'
            while (idx >= 0) {
                map[idx--][c] = '.';
            }
        }
    }
}
