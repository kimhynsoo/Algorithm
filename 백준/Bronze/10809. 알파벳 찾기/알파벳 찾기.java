import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args)throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        int []alp = new int[26];
        Arrays.fill(alp, -1);
        for(int i=0; i<line.length(); i++){
            int num = line.charAt(i)-'a';
            if(alp[num]==-1){
                alp[num]=i;
            }
        }

        for(int i=0;i<26; i++){
            System.out.print(alp[i]+" ");
        }

    }
}