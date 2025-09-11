import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {

    // 사람 클래스
    static class Person implements Comparable<Person> {
        int r, c;             // 사람 좌표
        int arrivaltime;      // 계단까지 도착 시간
        int time = 0;         // 계단 내려가는 시간 카운트

        // 계단 정보 포함 생성자 -> 계단까지 도착 시간 계산
        Person(int r, int c, Stair s) {
            this.r = r;
            this.c = c;
            this.arrivaltime = Math.abs(r - s.r) + Math.abs(c - s.c); // 맨해튼 거리
        }

        // 좌표만 저장하는 생성자
        Person(int r, int c) {
            this.r = r;
            this.c = c;
        }

        // 도착시간 기준 우선순위 정렬 (빠른 사람이 먼저)
        @Override
        public int compareTo(Person o) {
            return this.arrivaltime - o.arrivaltime;
        }
    }

    // 계단 클래스
    static class Stair {
        int r, c;   // 계단 좌표
        int time;   // 계단 내려가는 데 걸리는 시간

        public Stair(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }

    static int N;                 // 지도 크기
    static int idx;               // 사람 수
    static int[][] board;         // 지도 정보
    static Person[] people;       // 사람 배열
    static Stair[] stairs;        // 계단 배열
    static int result;            // 최소 소요 시간
    static ArrayList<Person> A, B; // 각 계단에 배정된 사람 리스트
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for(int tc=1; tc<=T; tc++){
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            people = new Person[10]; // 최대 10명
            stairs = new Stair[2];   // 계단은 항상 2개
            idx = 0;
            int idx2 = 0;

            // 지도 입력 및 사람/계단 위치 저장
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    int n = Integer.parseInt(st.nextToken());
                    board[i][j] = n;
                    if(n==1) people[idx++] = new Person(i,j);      // 사람
                    else if(n>1) stairs[idx2++] = new Stair(i,j,n); // 계단
                }
            }

            // 사람들을 두 계단 그룹(A,B)으로 나누기 위한 리스트 초기화
            A = new ArrayList<>();
            B = new ArrayList<>();
            result = Integer.MAX_VALUE;

            // 모든 배정 경우 탐색
            find(0);

            // 테스트 케이스 결과 출력
            System.out.printf("#%d %d\n", tc, result);
        }
    }

    

    // 사람들을 두 계단 그룹으로 배정하는 재귀
    static void find(int i){
        if(i==idx){  // 모든 사람 배정 완료
            result = Math.min(result, simulate(A,B)); // 해당 배정 경우 시뮬레이션 후 최소 시간 갱신
            return;
        }

        // i번째 사람을 계단 A에 배정
        A.add(new Person(people[i].r, people[i].c, stairs[0]));
        find(i+1); // 다음 사람 배정
        A.remove(A.size()-1); // 되돌리기(backtracking)

        // i번째 사람을 계단 B에 배정
        B.add(new Person(people[i].r, people[i].c, stairs[1]));
        find(i+1);
        B.remove(B.size()-1); // 되돌리기
    }

    // 계단별 시뮬레이션: 소요 시간 계산
    static int simulate(List<Person> first, List<Person> second){
        // 계단 도착 시간 기준 우선순위 큐
        PriorityQueue<Person> f_stairs = new PriorityQueue<>(first);
        PriorityQueue<Person> s_stairs = new PriorityQueue<>(second);

        // 계단 내려가는 중인 사람 관리
        Queue<Person> f_process = new ArrayDeque<>();
        Queue<Person> s_process = new ArrayDeque<>();

        int time = 1;

        // 계단에 아직 도착하지 않았거나 내려가는 중인 사람이 남아있으면 반복
        while(!f_stairs.isEmpty() || !s_stairs.isEmpty() || !f_process.isEmpty() || !s_process.isEmpty()){
            // 계단 입장: 도착한 사람 큐에 추가, 최대 3명 제한
            while(!f_stairs.isEmpty() && f_stairs.peek().arrivaltime < time && f_process.size()<3){
                Person p = f_stairs.poll();
                p.time = 1; // 계단 시작
                f_process.offer(p);
            }
            while(!s_stairs.isEmpty() && s_stairs.peek().arrivaltime < time && s_process.size()<3){
                Person p = s_stairs.poll();
                p.time = 1;
                s_process.offer(p);
            }

            // 계단 진행: 내려가는 시간 증가, 완료 시 큐에서 제거
            List<Person> f_done = new ArrayList<>();
            for(Person p : f_process){
                p.time++;
                if(p.time > stairs[0].time) f_done.add(p); // 계단 다 내려감
            }
            f_process.removeAll(f_done);

            List<Person> s_done = new ArrayList<>();
            for(Person p : s_process){
                p.time++;
                if(p.time > stairs[1].time) s_done.add(p);
            }
            s_process.removeAll(s_done);

            time++; // 시뮬레이션 시간 증가
        }

        return time; // 모든 사람이 내려간 시간 반환
    }
}
