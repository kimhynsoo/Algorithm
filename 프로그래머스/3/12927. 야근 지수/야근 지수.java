import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq =new PriorityQueue<Integer>(Collections.reverseOrder());
        
        int len = works.length;
        for(int i=0; i<len; i++){
            pq.offer(works[i]);
        }
        
        for(int i=0; i<n; i++){
            if(!pq.isEmpty()){
                int num = pq.poll();
                if(num-1!=0) pq.offer(num-1);    
            }
            
        }
        while(!pq.isEmpty()){
            answer+=Math.pow(pq.poll(),2);
        }
        
        return answer;

    }
}