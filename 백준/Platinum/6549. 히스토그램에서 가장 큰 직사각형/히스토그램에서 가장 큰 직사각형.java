import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;              // 막대기의 개수
    static long []height;      // 각 막대기의 높이 저장 배열
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 줄 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        
        StringBuilder sb = new StringBuilder();
        
        // N이 0이 될 때까지 반복 (입력 종료 조건)
        while (N != 0) {
            height = new long[N]; // 히스토그램 높이 배열 초기화
            
            // N개의 막대 높이 입력 받기
            for (int i = 0; i < N; i++) {
                height[i] = Long.parseLong(st.nextToken());
            }
            
            // 가장 큰 직사각형 넓이 구하기
            sb.append(getArea()).append("\n");
            
            // 다음 입력 줄 읽기
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
        }
        
        // 결과 출력
        System.out.println(sb);
    }

    /**
     * 스택을 이용해 히스토그램에서 가장 큰 직사각형 넓이를 구하는 함수
     */
    static long getArea() {
        Stack<Integer> stack = new Stack<>(); // 인덱스를 저장하는 스택
        long max_area = 0;                    // 최댓값을 저장
        int i = 0;                            // 현재 막대 위치
        
        // 모든 막대를 순차적으로 탐색
        while (i < N) {
            // 스택이 비어 있거나, 현재 막대가 스택 top 막대보다 크거나 같으면 push
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i++);
            } else {
                // 스택의 top을 pop하여 넓이를 계산
                int top = stack.pop();
                
                // 높이는 height[top]
                // 너비는 (스택이 비었으면 i, 아니면 i - stack.peek() - 1)
                long area = height[top] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
                
                max_area = Math.max(max_area, area);
            }
        }
        
        // 스택에 남은 막대 처리
        while (!stack.isEmpty()) {
            int top = stack.pop();
            long area = height[top] * (stack.isEmpty() ? i : (i - stack.peek() - 1));
            
            max_area = Math.max(max_area, area);
        }
        
        return max_area;
    }
}
