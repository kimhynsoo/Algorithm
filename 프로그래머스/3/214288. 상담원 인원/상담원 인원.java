import java.util.*;
class Solution {
    int answer = Integer.MAX_VALUE;
    public int solution(int k, int n, int[][] reqs) {
        
        //유형 별 상담 요청 분리
        List<int[]>[] requests = new ArrayList[k+1];
        
        for(int i=1; i<=k; i++){
            requests[i] = new ArrayList<>();
        }
        
        for(int[] req :reqs){
            int start = req[0];
            int duration = req[1];
            int type = req[2];
            
            requests[type].add(new int[]{start,duration});
        }
        
        //wait[type][count] 
        //type 유형별 상담원을 count만큼 배치했을 때 기다리는 총 시간
        int maxCount = n-k+1;
        int [][] wait = new int[k+1][maxCount+1];
        
        for(int type = 1; type<=k; type++ ){
            for(int count =1; count<=maxCount; count++){
                wait[type][count]=
                    calcuateWaitingTime(requests[type],count);
            }
        }
        
        //모든 배치 비교
        
        
        dfs(1,k,n-k,0,wait);
        
        
        return answer;
    }
    
    int calcuateWaitingTime(List<int[]> requests, int count){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i=0; i<count; i++){
            pq.offer(0);
        }
        
        int totalWaitingTime=0;
        
        for(int []request : requests){
            int start = request[0];
            int duration = request[1];
            
            int avaiableTime = pq.poll();
            
            if(avaiableTime<=start){
                pq.offer(start+duration);
            }else{
                totalWaitingTime+=avaiableTime-start;
                pq.offer(avaiableTime+duration);
            }
        }
        return totalWaitingTime;
    }
    
    void dfs ( int type, int k,int remain, int totalWait,int [][]wait) {
        if(type>k){
            if(remain==0){
                answer=Math.min(answer,totalWait);
            }
            return;
        }
        
        for(int add=0; add<=remain; add++){
            int counselorCount = 1+add;
            dfs(type+1, k,remain -add, totalWait+wait[type][counselorCount],wait);
        }
    }
}