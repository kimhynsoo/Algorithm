import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static class Flower implements Comparable<Flower>{
        int sM, sD, eM, eD;
        public Flower(int sM, int sD, int eM, int eD) {
            this.sM = sM;
            this.sD = sD;
            this.eM = eM;
            this.eD = eD;
        }

        // (시작일 오름차순) → (종료일 내림차순)
        @Override
        public int compareTo(Flower o) {
            if (this.sM == o.sM) {
                if (this.sD == o.sD) {
                    if (this.eM == o.eM) {
                        return o.eD - this.eD;   // 종료일은 내림차순
                    }
                    return o.eM - this.eM;
                }
                return this.sD - o.sD;
            }
            return this.sM - o.sM;
        }
    }

    static int toDay(int m, int d) {
        return m * 100 + d;     // ex. 3,1 → 301
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Flower> list = new ArrayList<>();

        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sm = Integer.parseInt(st.nextToken());
            int sd = Integer.parseInt(st.nextToken());
            int em = Integer.parseInt(st.nextToken());
            int ed = Integer.parseInt(st.nextToken());
            list.add(new Flower(sm, sd, em, ed));
        }

        Collections.sort(list);

        int target = 301;   // 3월 1일
        int end = 1130;     // 11월 30일
        int idx = 0;
        int cnt = 0;
        int maxEnd = 0;

        while (target <= end) {
            boolean updated = false;

            // 시작일이 target 이하인 동안, 가장 멀리 끝나는 꽃 탐색
            while (idx < N && toDay(list.get(idx).sM, list.get(idx).sD) <= target) {
                int flowerEnd = toDay(list.get(idx).eM, list.get(idx).eD);
                if (flowerEnd > maxEnd) maxEnd = flowerEnd;
                idx++;
                updated = true;
            }

            if (!updated) {
                // target 날짜를 덮을 꽃이 없음 → 불가능
                System.out.println(0);
                return;
            }

            target = maxEnd; // 구간 확장
            cnt++;
        }

        System.out.println(cnt);
    }
}
