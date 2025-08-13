import java.util.Scanner;

public class Main {
    
    // 풀이: 비트마스크로 배운 알파벳을 관리하며 DFS로 K-5개 선택해 최대 읽기 수 계산
    static int N,K;
    static int[] words;
    static int learnMask;
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        words = new int[N];
        for(int i=0;i<N;i++){
            String word = sc.next();
            words[i] = wordToMask(word);
        }
        // 등록(|): 필수 문자 a,n,t,i,c를 배운 상태로 시작
        learnMask = 0;
        learnMask |= 1 << ('a' - 'a');
        learnMask |= 1 << ('n' - 'a');
        learnMask |= 1 << ('t' - 'a');
        learnMask |= 1 << ('i' - 'a');
        learnMask |= 1 << ('c' - 'a');
        if(K<5){
            System.out.println(0);
            return;
        }
        if(K>=26){
            System.out.println(N);
            return;
        }
        dfs(0,0);
        System.out.println(answer);
    }
    static int wordToMask(String word){
        int mask = 0;
        for(char c : word.toCharArray()){
            // 등록(|): 단어에 포함된 알파벳 비트를 켠다
            mask |= 1 << (c - 'a');
        }
        return mask;
    }
    static void dfs(int start, int selected){
        if(selected == K-5){
            answer = Math.max(answer, countReadable());
            return;
        }
        for(int i=start;i<26;i++){
            // 확인(&): 이미 배운 글자면 스킵
            if((learnMask & (1 << i)) != 0) continue;
            // 등록(|): 글자 i를 배운다
            learnMask |= (1 << i);
            dfs(i+1, selected+1);
            // 삭제(&~): 글자 i 선택을 원복한다
            learnMask &= ~(1 << i);
        }
    }
    static int countReadable(){
        int count = 0;
        for(int word : words){
            // 확인(&): word에 포함된 비트 중 배운 적 없는 비트(~learnMask)가 하나라도 있으면 0이 아니다
            if((word & ~learnMask) == 0) count++;
        }
        return count;
    }

}
