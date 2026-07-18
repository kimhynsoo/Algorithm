import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer="";
        HashMap<String,Integer> participants = new HashMap<>();
        for(String p : participant){
            participants.put(p,participants.getOrDefault(p,0)+1);
        }
        for(String c : completion){
            participants.put(c,participants.get(c)-1);
        }
        for(String name : participants.keySet()){
            if(participants.get(name)>0)
                answer = name;
        }
        return answer;
        
    }
}