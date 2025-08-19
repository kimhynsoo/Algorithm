import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args)throws IOException {
        // 100x100 도화지 (문제 조건: 최대 100)
        // 색종이는 10x10 크기이므로 여유를 두어 101x101로 선언
        int [][] board = new int[101][101];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 색종이 개수 입력

        // 색종이 정보를 입력받아 board에 색종이 영역을 표시
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()); // 왼쪽 변의 좌표 (x)
            int c = Integer.parseInt(st.nextToken()); // 아래쪽 변의 좌표 (y)

            // 색종이는 (r, c)부터 가로 10, 세로 10 크기를 차지
            for(int j=r; j<r+10; j++){
                for(int k=c; k<c+10; k++){
                    board[j][k] = 1; // 색종이가 덮인 영역은 1로 표시
                }
            }
        }

        // 상, 하, 좌, 우 방향 벡터
        int [] dx = {-1, 1, 0, 0};
        int [] dy = {0, 0, 1, -1};

        int cnt = 0; // 둘레 길이 저장 변수

        // 도화지 전체를 탐색
        for(int i=0; i<100; i++){
            for(int j=0; j<100; j++){
                if(board[i][j] == 1){ // 색종이가 덮인 칸이면
                    // 네 방향을 확인
                    for(int k=0; k<4; k++){
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        // 경계 밖이거나(board 배열 바깥)
                        // 이웃 칸이 색종이가 아닌 경우(0)
                        // => 현재 칸이 외곽선에 닿아 있는 것
                        if(nx < 0 || ny < 0 ||nx==100||ny==100|| board[nx][ny] == 0){
                            cnt++;
                        }
                    }
                }
            }
        }

        // 총 둘레 길이 출력
        System.out.println(cnt);
    }
}
