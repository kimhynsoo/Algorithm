import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        // (deadline, cup_ramen) 배열을 저장하는 우선순위 큐
        // deadline 기준 오름차순 정렬
        // deadline이 같다면 cup_ramen 기준 내림차순
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1]; // 컵라면 많은 순으로 정렬
            }
            return o1[0] - o2[0]; // 데드라인 빠른 순
        });

        // 입력값 모두 pq에 저장
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int deadline = Integer.parseInt(st.nextToken());
            int cup_ramen = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{deadline, cup_ramen});
        }
        
        // 컵라면을 저장하는 최소 힙
        // → 컵라면 수가 가장 작은 것이 위에 옴
        PriorityQueue<Integer> result = new PriorityQueue<>();

        // 정렬된 순서대로 문제를 확인
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); // 가장 데드라인 빠른 문제 꺼냄

            // 일단 해당 문제를 푼다고 가정하고 컵라면 추가
            result.offer(cur[1]);

            // 현재까지 푼 문제 개수가 데드라인을 초과하면
            // → 가장 컵라면 적은 문제 제거
            if(result.size() > cur[0]){
                result.poll();
            }
        }

        // 선택된 문제들의 컵라면 수 합산
        long sum = 0;
        while (!result.isEmpty()) {
            sum += result.poll();
        }

        // 최대 얻을 수 있는 컵라면 출력
        System.out.println(sum);
    }
}
