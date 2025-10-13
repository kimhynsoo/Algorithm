import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args)throws IOException {
        
        // 입력을 빠르게 받기 위한 BufferedReader 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받을 수의 개수 N
        int N = Integer.parseInt(br.readLine());
        
        // 최대 힙 (내림차순 정렬): 작은 수들을 저장
        PriorityQueue<Integer> max_heap = new PriorityQueue<>((o1,o2)->(o2-o1));
        
        // 최소 힙 (오름차순 정렬): 큰 수들을 저장
        PriorityQueue<Integer> min_heap = new PriorityQueue<>((o1,o2)->(o1-o2));
        
        // 출력 결과를 저장할 StringBuilder
        StringBuilder sb = new StringBuilder();

        // N개의 수를 차례로 입력받으며 중간값 출력
        for(int i=1; i<=N; i++){
            int num = Integer.parseInt(br.readLine()); // 숫자 입력받기
            
            // 두 힙의 크기가 같다면 max_heap에 넣기 (왼쪽에 우선 넣음)
            if(min_heap.size() == max_heap.size()) 
                max_heap.offer(num);
            else // 다르면 min_heap에 넣기 (오른쪽)
                min_heap.offer(num);

            // 힙에 원소가 있고, max_heap의 최댓값이 min_heap의 최솟값보다 크면 swap
            if(!min_heap.isEmpty() && !max_heap.isEmpty()){
                if(max_heap.peek() > min_heap.peek()){
                    // 두 값을 바꿔서 힙의 조건 유지
                    int tmp = max_heap.poll();
                    max_heap.offer(min_heap.poll());
                    min_heap.offer(tmp);
                }
            }

            // 항상 max_heap의 루트 값이 현재까지의 중간값이므로 출력
            sb.append(max_heap.peek()).append("\n");
        }

        // 최종 결과 출력
        System.out.println(sb);
    }
}
