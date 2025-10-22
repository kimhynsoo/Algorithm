
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static class Trie{
        char alphabet;
        Map<Character, Trie> children = new HashMap<>();

        //알파벳 주어졌을 때 생성자
        Trie(char alphabet){
            this.alphabet = alphabet;
        }

        //기본 생성자
        Trie(){}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Trie head = new Trie(); //루트 노드

        //N개의 단어 입려받아서 트라이 구조로 만들기
        for(int i=0; i<N; i++){
            String word = br.readLine();
            Trie idxTrie = head;
            for(int j=0; j<word.length(); j++){
                char alphabet = word.charAt(j);

                //새로운 정점인 경우 하위에 추가
                if(!idxTrie.children.containsKey(alphabet)){
                    idxTrie.children.put(alphabet, new Trie(alphabet));
                }

                idxTrie = idxTrie.children.get(alphabet);
            }
        }

        //M개의 문자열에 대해 각각이 접두사인지 판단
        int cnt=0;
        for(int i=0; i<M; i++){
            String word = br.readLine();
            Trie idxTrie = head;
            for(int j=0; j<word.length(); j++){
                char alphabet = word.charAt(j);

                //접두사에 해당하지 않으면 탐색 종료
                if(!idxTrie.children.containsKey(alphabet)){
                    break;
                }
                idxTrie = idxTrie.children.get(alphabet);
                if (j== word.length()-1){
                    cnt++;
                }
            }
        }
        System.out.println(cnt);


        
    }
}

