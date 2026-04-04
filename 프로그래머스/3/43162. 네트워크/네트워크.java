import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited= new boolean[n];
        for(int i=0; i<n; i++){
            if(!visited[i]){
                dfs(i,computers,visited,n);
                answer++;
            }
        }
        return answer;
    }
    void dfs(int now, int[][] computers, boolean[] visited,int n){
        visited[now]=true;
        for(int next=0; next<n; next++){
            if(!visited[next]&&computers[now][next]==1){
                dfs(next,computers,visited,n);
            }
        }
    }
}