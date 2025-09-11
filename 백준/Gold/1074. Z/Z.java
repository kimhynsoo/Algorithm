import java.util.Scanner;

public class Main {
    static int cnt=0;
    static int N,R,C;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        C = sc.nextInt();
        draw(0, 0, (int)Math.pow(2, N));
        System.out.println(cnt);
        
    }
    static boolean end_flag;
    static void draw(int r, int c, int n){
        if(n==2){
            int r_n = r+n/2;
            int c_n = c+n/2;
            if(R<r_n && C<c_n){ //1사
                
            }
            else if(R<r_n && C>=c_n){ //2사
                cnt += 1;
                
            }
            else if(R>=r_n && C<c_n){ //3사
                cnt += 2;
                
            }
            else if(R>=r_n && C>=c_n){ //4사
                cnt += 3;
                
            }
        }else{
            int next_n =n/2;
            int r_n = r+next_n;
            int c_n = c+next_n;
            
            int plus = (n/2) *(n/2);
            if(R<r_n && C<c_n){ //1사
                draw(r, c, next_n);
            }
            else if(R<r_n && C>=c_n){ //2사
                cnt +=  plus;
                draw(r, c+next_n, next_n);
            }
            else if(R>=r_n && C<c_n){ //3사
                cnt += plus*2;
                draw(r+next_n, c, next_n);
            }
            else if(R>=r_n && C>=c_n){ //4사
                cnt += plus*3;
                draw(r+next_n, c+next_n, next_n);
            }
        }

    
    }
}
