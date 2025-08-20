import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // N: 수빈이의 시작 위치, K: 동생의 목표 위치
    static int N, K;
    // count[]: 각 위치까지 최단 시간으로 도달하는 방법의 수를 저장하는 배열
    static int[] count = new int[100001];
    // time[]: 각 위치까지 도달하는 데 걸리는 최단 시간을 저장하는 배열
    static int[] time = new int[100001];

    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 BufferedReader 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 시작 위치와 목표 위치 입력
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        // 예외 처리: 수빈이가 동생보다 앞서 있거나 같은 위치에 있을 경우
        if (N >= K) {
            // 이 경우, 뒤로 한 칸씩 이동하는 방법밖에 없음
            System.out.println(N - K); // 걸리는 시간
            System.out.println(1);       // 방법의 수
            return; // 프로그램 종료
        }
        
        // 너비 우선 탐색 (BFS) 실행
        bfs();

        // 결과 출력
        System.out.println(time[K]); // K까지 가는 데 걸리는 최단 시간
        System.out.println(count[K]);// 최단 시간으로 K까지 가는 방법의 수
    }

    static void bfs() {
        // BFS를 위한 큐(Queue) 생성
        Queue<Integer> q = new ArrayDeque<>();
        // 시작점 N을 큐에 추가
        q.offer(N);

        // time 배열을 -1로 초기화하여 방문 여부를 체크 (-1은 '미방문'을 의미)
        Arrays.fill(time, -1);

        // 시작점의 시간과 방법의 수 초기화
        time[N] = 0;  // 시작점이므로 시간은 0
        count[N] = 1; // 시작점에 도달하는 방법은 1가지

        // 큐가 비어있지 않은 동안 반복
        while (!q.isEmpty()) {
            // 큐에서 현재 위치를 꺼냄
            int current = q.poll();

            // 현재 위치에서 이동할 수 있는 세 가지 경우 (X-1, X+1, X*2)
            int[] nextMoves = {current - 1, current + 1, current * 2};

            for (int next : nextMoves) {
                // 다음 위치가 유효한 범위(0 ~ 100000) 내에 있는지 확인
                if (next >= 0 && next <= 100000) {
                    
                    // Case 1: 해당 위치를 처음 방문하는 경우 (time[next] == -1)
                    if (time[next] == -1) {
                        time[next] = time[current] + 1; // 최단 시간을 기록 (현재 시간 + 1)
                        count[next] = count[current];   // 방법의 수를 현재 위치의 방법 수로 설정
                        q.offer(next);                  // 다음 탐색을 위해 큐에 추가
                    } 
                    // Case 2: 이미 방문했지만, 같은 최단 시간으로 도달하는 또 다른 방법을 찾은 경우
                    else if (time[next] == time[current] + 1) {
                        // 기존 방법의 수에 현재 위치까지 오는 방법의 수를 더해줌
                        count[next] += count[current];
                    }
                }
            }
        }
    }
}