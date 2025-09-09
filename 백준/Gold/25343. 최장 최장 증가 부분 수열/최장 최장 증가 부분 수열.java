import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        
        // dp[i][j]는 (i,j)까지 도달하는 경로들의 LIS 정보를 압축한 '최적의 LIS 배열'을 저장.
        ArrayList<Integer>[][] dp = new ArrayList[N][N];

        // 입력값으로 board 배열 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // DP 시작점 (0,0) 초기화
        dp[0][0] = new ArrayList<>();
        dp[0][0].add(board[0][0]);

        // DP 테이블을 (0,0)부터 (N-1, N-1)까지 순차적으로 채워나간다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 시작점은 이미 초기화
                if (i == 0 && j == 0) continue;

                // 현재 칸 (i,j)는 위쪽 칸 (i-1,j) 또는 왼쪽 칸 (i,j-1)에서 올 수 있다.
                ArrayList<Integer> top = (i > 0) ? dp[i - 1][j] : null;
                ArrayList<Integer> left = (j > 0) ? dp[i][j - 1] : null;

                // 1. 위쪽과 왼쪽의 LIS 배열을 merge
                // 이 병합된 리스트는 (i,j)에 도달하기 직전의 최적 상태를 나타낸다.
                ArrayList<Integer> mergedList = merge(top, left);

                // 2. 병합된 LIS 배열에 현재 칸의 값(board[i][j])을 반영하여 업데이트한다.
                LIS(mergedList, board[i][j]);

                // 3. 업데이트된 LIS 배열을 현재 칸의 DP 값으로 저장한다.
                dp[i][j] = mergedList;
            }
        }

        // 최종 목적지 (N-1, N-1)에 저장된 LIS 배열의 크기가 구하고자 하는 최장 LIS의 길이가 된다.
        System.out.println(dp[N - 1][N - 1].size());
    }


    /* 
    두 개의 LIS 배열(l1, l2)을 병합하여 하나의 '가상 최적 LIS 배열'을 생성한다.
    병합 규칙: 각 인덱스 위치에서 두 배열의 원소 중 더 작은 값을 선택한다.
    */
    static ArrayList<Integer> merge(ArrayList<Integer> l1, ArrayList<Integer> l2) {
        // 한쪽이 null이면(경로가 없으면) 다른 쪽 리스트를 복사해서 반환한다.
        if (l1 == null) return new ArrayList<>(l2);
        if (l2 == null) return new ArrayList<>(l1);

        ArrayList<Integer> merged = new ArrayList<>();
        int p1 = 0, p2 = 0;
        
        // 두 리스트의 공통 길이만큼 각 인덱스의 최솟값을 추가한다.
        while (p1 < l1.size() && p2 < l2.size()) {
            merged.add(Math.min(l1.get(p1), l2.get(p2)));
            p1++;
            p2++;
        }

        // 만약 한쪽 리스트가 더 길다면, 남은 원소들을 그대로 추가한다.
        while (p1 < l1.size()) {
            merged.add(l1.get(p1));
            p1++;
        }
        while (p2 < l2.size()) {
            merged.add(l2.get(p2));
            p2++;
        }

        return merged;
    }


    static void LIS(ArrayList<Integer> lis, int val) {
        // lower_bound를 통해 val이 들어갈 위치를 찾는다.
        int pos = lower_bound(lis, val);
        
        // val이 lis의 모든 원소보다 크면, 맨 뒤에 추가하여 길이를 1 늘린다.
        if (pos == lis.size()) {
            lis.add(val);
        } else {
            // 그렇지 않으면, 해당 위치의 값을 val로 교체한다. 
            lis.set(pos, val);
        }
    }


    static int lower_bound(ArrayList<Integer> list, int key) {
        int start = 0;
        int end = list.size();
        while (start < end) {
            int mid = (start + end) / 2;
            if (list.get(mid) < key) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}