import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

    // 원자 정보 클래스
    static class Atom implements Comparable<Atom>{ 
        int x, y;   // 좌표
        int dir;    // 이동 방향 (0:상, 1:하, 2:우, 3:좌)
        int e;      // 에너지

        public Atom(int x, int y, int dir, int e) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.e = e;
        }

        @Override
        public int compareTo(Atom o) {
            // 원자 정렬 기준: x좌표가 작은 순, x가 같으면 y좌표가 작은 순
            return this.x < o.x ? -1 
                    : (this.x != o.x ? Integer.compare(this.x, o.x) : Integer.compare(this.y, o.y));
        }
        // @Override
        // public int compareTo(Atom o) {
        //     //원자 정렬기준 : x좌표 작은 순 , x가 같으면 y좌표 작은 순
        //     if(this.x< o.x){
        //         return -1;
        //     }else if(this.x!= o.x){
        //         return Integer.compare(this.x, this.x);
        //     }else{
        //         return Integer.compare(this.y, o.y);
        //     }

        // }
    }
    
    // 충돌 정보를 담는 클래스
    static class Pair implements Comparable<Pair>{ 
        int i, j;   // 충돌하는 두 원자의 인덱스
        int time;   // 충돌 시간

        public Pair(int i, int j, int time) {
            this.i = i;
            this.j = j;
            this.time = time;
        }
        @Override
        public int compareTo(Pair o) {
            // 시간 기준 오름차순 정렬
            return Integer.compare(this.time, o.time);
        }
    }
    
    static int N;                // 원자 개수
    static ArrayList<Atom> list; // 원자 목록
    
    public static void main(String[] args) throws NumberFormatException, IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(in.readLine()); // 테스트 케이스 개수
        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(in.readLine());  // 원자 수
            list = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(in.readLine(), " ");
                int x = Integer.parseInt(st.nextToken()) * 2; // 좌표를 2배로 늘려 소수 시간 계산 피함
                int y = Integer.parseInt(st.nextToken()) * 2;
                int d = Integer.parseInt(st.nextToken());     // 방향
                int e = Integer.parseInt(st.nextToken());     // 에너지
                list.add(new Atom(x, y, d, e));
            }
            // 시뮬레이션 후 결과 출력
            System.out.println("#" + tc + " " + makeBoomPair());
        }
    }

    // 원자 충돌 후보를 모두 찾아서 시뮬레이션 진행
    private static int makeBoomPair() {
        Collections.sort(list);             // 좌표 기준으로 정렬
        ArrayList<Pair> boomList = new ArrayList<>(); // 충돌 후보 리스트

        // 모든 원자 쌍(i, j)에 대해 충돌 가능성 검사
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                Atom a = list.get(i);
                Atom b = list.get(j);
                
                // 1. 같은 x좌표 위에서 위/아래로 마주 이동하는 경우
                if (a.x == b.x) {
                    if (a.dir == 0 && b.dir == 1) { // a:상, b:하
                        boomList.add(new Pair(i, j, Math.abs(a.y - b.y) / 2));
                    }
                }
                // 2. 같은 y좌표 위에서 좌/우로 마주 이동하는 경우
                if (a.y == b.y) {
                    if (a.dir == 3 && b.dir == 2) { // a:우, b:좌
                        boomList.add(new Pair(i, j, Math.abs(a.x - b.x) / 2));
                    }
                }
                // 3. '/' 대각선 위에서 충돌 (x-y 값이 같음)
                if (a.x - a.y == b.x - b.y) {
                    if ((a.dir == 3 && b.dir == 1) || (a.dir == 0 && b.dir == 2)) {//a:우 b:하 a:상 b:좌
                        boomList.add(new Pair(i, j, Math.abs(a.x - b.x)));
                    }
                }
                // 4. '\' 대각선 위에서 충돌 (x+y 값이 같음)
                if (a.x + a.y == b.x + b.y) {
                    if ((a.dir == 1 && b.dir == 2) || (a.dir == 3 && b.dir == 0)) {//a:우 b:상 a:하 b:좌
                        boomList.add(new Pair(i, j, Math.abs(a.x - b.x)));
                    }
                }
            }
        }
        
        // 충돌 리스트를 기반으로 실제 소멸 에너지 합산
        return getTotalEnergy(boomList);
    }

    // 충돌 리스트를 시뮬레이션하여 총 에너지 계산
    private static int getTotalEnergy(ArrayList<Pair> boomList) {
        Collections.sort(boomList);       // 시간 순으로 정렬
        int INF = Integer.MAX_VALUE;
        int boomTimes[] = new int[N];     // 각 원자의 소멸 시간 기록
        Arrays.fill(boomTimes, INF);      // 아직 소멸 안한 상태는 INF
        int sum = 0;                      // 총 에너지 합

        // 충돌 후보를 시간순으로 처리
        for (Pair p : boomList) {
            // 이미 더 이른 시간에 소멸한 원자가 있으면 무시
            if (boomTimes[p.i] < p.time || boomTimes[p.j] < p.time) continue;
            
            // 원자 i가 아직 살아있다면 이번 시간에 소멸
            if (boomTimes[p.i] == INF) {
                boomTimes[p.i] = p.time;           // 소멸 시간 기록
                sum += list.get(p.i).e;            // 에너지 합산
            }
            // 원자 j가 아직 살아있다면 이번 시간에 소멸
            if (boomTimes[p.j] == INF) {
                boomTimes[p.j] = p.time;           // 소멸 시간 기록
                sum += list.get(p.j).e;            // 에너지 합산
            }
        }
        return sum;
    }
}
