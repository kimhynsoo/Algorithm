import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=0; i<bridge_length; i++) queue.offer(0);
        int on_bridge=0;
        int idx=0;
        
        while(idx<truck_weights.length){
            time++;
            on_bridge-=queue.poll();
            
            if(on_bridge+truck_weights[idx] <= weight){
                queue.offer(truck_weights[idx]);
                on_bridge+=truck_weights[idx];
                idx++;
            }else{
                queue.offer(0);
            }
        }
        time+=bridge_length;
        
        
        return time;
    }
}