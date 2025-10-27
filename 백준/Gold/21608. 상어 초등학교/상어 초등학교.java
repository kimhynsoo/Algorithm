import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int [][] graph;
    static int N; // 반의 크기 (N*N)
    static int[] dr={-1,1,0,0};
    static int[] dc={0,0,-1,1};
    static Map<Integer,int[]> love; //<학생번호, 학생이 좋아하는 친구들의 번호>
    static class Seat { //자리에 대한 정보
        int r,c, empty, friend;

        public Seat(int r, int c, int empty, int friend) {
            this.r = r;
            this.c = c;
            this.empty = empty;
            this.friend = friend;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int studentNum= (int)Math.pow(N, 2);
        graph = new int[N][N];
        love = new HashMap<>();
        
        for(int i=0; i<studentNum; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(st.nextToken());
            int [] person  = new int[4];
            for(int j=0; j<4; j++){
                int p = Integer.parseInt(st.nextToken());
                person[j]=p;
            }
            love.put(student, person);
            
            putStudentSeat(student); //해당 번호의 학생을 자리 배정
        }


        System.out.println(getScore()); //최종 스코어 반환


    }

    static void putStudentSeat(int student){
        int [] friends = love.get(student);
        ArrayList<Seat> seats = new ArrayList<>(); //모든 자리의 정보를 담을 리스트

        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){ //모든 자리를 돌면서 해당 학생의 앉힐 자리에 대한 정보를 구한다.
                if(graph[r][c]!=0) continue; //이미 그 자리에 배정 받은 학생이 있다면  skip
                int friendCnt=0;
                int emptyCnt=0;

                for(int d=0; d<4; d++){ //4방위 탐색
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    if(nr<0 || nc<0 || nr>=N || nc>= N) continue; //out of range
                    for(int i=0; i<4; i++){
                        if(graph[nr][nc]==friends[i]){ //r,c 주변 4방위 중 좋아하는 친구가 있다면
                            friendCnt++;    //좋아하는 친구 +1
                        }
                    }
                    if(graph[nr][nc]==0) emptyCnt++; // 빈 칸이라면 빈칸 +1
                }

                seats.add(new Seat(r, c, emptyCnt, friendCnt)); //해당 정보를 리스트에 넣어준다
            }
        }

        seats.sort((o1,o2)->{//1.친구가 많은 자리 2.빈칸이 많은자리 3. 행이 작은 자리 4. 열이 작은 자리
            if(o1.friend == o2.friend){
                if(o1.empty == o2.empty){
                    if(o1.r == o2.r){
                        return o1.c - o2.c; //친구, 빈 칸, 행이 같다면 열로 오름차순
                    }
                    return o1.r - o2.r; //친구, 빈 칸이 같다면 열로 오름차순
                }
                return o2.empty - o1.empty; //친구가 같다면 빈칸으로 내림차순
            }
            return o2.friend - o1.friend; //친구로 내림차순
        });

        for(Seat s : seats){
            graph[s.r][s.c] = student; //가장 처음 seat가 best seat이므로 해당 칸에 학생을 배치하고 return
            return;
        }
    }

    static int getScore(){
        int score=0;
        for(int r=0; r<N; r++){
            for(int c=0; c<N; c++){ //모든 칸 순회
                int cnt=0;
                int [] friends = love.get(graph[r][c]);

                for(int d=0; d<4; d++){
                    int nr = r+dr[d];
                    int nc = c+dc[d];

                    if(nr <0 || nc<0|| nr>=N || nc>=N ) continue;

                    for(int i=0; i<4; i++){
                        if(graph[nr][nc]==friends[i]){
                            cnt++; //해당 주위에 좋아하는 친구가 있다면 cnt ++
                        }
                    }
                }
                if(cnt>0){
                    score+= Math.pow(10, cnt-1); //10의 (cnt-1)제곱을 score에 더해준다.
                }
            }
        }


        return score;
    }

}
