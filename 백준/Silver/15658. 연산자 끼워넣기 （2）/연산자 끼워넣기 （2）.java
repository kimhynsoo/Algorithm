import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Main {
    // BOJ 15658: 연산자 끼워넣기 (2) - DFS로 모든 연산자 배치 탐색
    static int N;
    static int[] A;           // 수열
    static int[] op;          // 연산자 개수 [+, -, x, /]
    static int max = Integer.MIN_VALUE; // 최댓값 결과
    static int min = Integer.MAX_VALUE; // 최솟값 결과
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        String[] input = br.readLine().split(" "); // 피연산자 입력
        for(int i=0;i<N;i++){
            A[i] = Integer.parseInt(input[i]);
        }
        String[] input2 = br.readLine().split(" "); // 연산자 개수 입력 [+ - x /]
        op = new int[4];
        for(int i=0;i<4;i++){
            op[i] = Integer.parseInt(input2[i]);
        }
        dfs(1,A[0]); // 첫 번째 수를 시작값으로 두고, 두 번째 수부터 연산 배치 탐색 시작
        System.out.println(max);
        System.out.println(min);
    }
    static void dfs(int index, int result){ // index: 다음에 처리할 수의 인덱스, result: 현재까지 계산 결과
        if(index == N){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }
        for(int i=0;i<4;i++){
            if(op[i] > 0){ // 해당 연산자를 1개 사용해본다
                op[i]--;   // 사용 처리 (백트래킹 전진)
                dfs(index+1, calculate(result, A[index], i));
                op[i]++;   // 원복 (백트래킹 복귀)
            }
        }
    }
    static int calculate(int a, int b, int op){ // op: 0(+),1(-),2(*),3(/)
        if(op == 0) return a+b;
        if(op == 1) return a-b;
        if(op == 2) return a*b;
        if(op == 3) return a/b; // 자바 정수 나눗셈: 0 방향으로 버림(음수도 절대값 기준 내림 아님)
        return 0;
    }
}
