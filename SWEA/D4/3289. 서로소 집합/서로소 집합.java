import java.io.*;
import java.util.*;

/**
 * SWEA 3289번 문제 "서로소 집합" 솔루션
 * Disjoint Set Union (Union-Find) 알고리즘을 사용하여 구현
 */
public class Solution {
    static int N, M; // N: 원소의 개수, M: 연산의 개수
    static int[] parents; // 각 원소의 부모를 저장하는 배열
    static int[] rank; // 트리의 높이(랭크)를 저장하여 합집합 연산을 최적화하기 위한 배열

    /**
     * 초기화 함수: N개의 원소가 각각 별개의 집합을 이루도록 초기화한다.
     */
    static void make() {
        for (int i = 1; i <= N; i++) {
            parents[i] = i; // 모든 원소는 자기 자신을 부모로 가리키게 한다.
            // rank 배열은 0으로 자동 초기화된다.
        }
    }

    static int find(int a) {
        // 기저 사례: 자기 자신이 부모이면 루트 노드이다.
        if (parents[a] == a) return a;
        // 경로 압축(Path Compression): 재귀적으로 루트를 찾고, 그 과정에서 만나는 모든 노드의 부모를 루트로 직접 연결한다.
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        // 각 원소의 루트 노드를 찾는다.
        int rootA = find(a);
        int rootB = find(b);

        // 두 원소가 이미 같은 집합에 속해 있다면 합칠 필요가 없다.
        if (rootA == rootB) return false;
        
        // 랭크 기반 합치기(Union by Rank): 랭크가 낮은 트리를 랭크가 높은 트리 밑으로 붙인다.
        if (rank[rootA] > rank[rootB]) {
            parents[rootB] = rootA; // rootB의 부모를 rootA로 설정
        } else if (rank[rootA] < rank[rootB]) {
            parents[rootA] = rootB; // rootA의 부모를 rootB로 설정
        } else { // 랭크가 같은 경우
            parents[rootB] = rootA; // 한쪽(B)을 다른 쪽(A)에 붙이고
            rank[rootA]++; // 붙여진 쪽(A)의 랭크를 1 증가시킨다.
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken()); // 테스트 케이스 수

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            // 각 테스트 케이스마다 배열을 새로 할당하고 초기화한다.
            parents = new int[N + 1]; // 원소 번호가 1부터 시작하므로 N+1 크기
            rank = new int[N + 1];
            make(); // 서로소 집합 초기화

            StringBuilder sb = new StringBuilder(); // 출력을 효율적으로 처리하기 위한 StringBuilder

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int op = Integer.parseInt(st.nextToken()); // 연산 종류 (0: union, 1: find)
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (op == 0) { // 합집합 연산
                    union(a, b);
                } else if (op == 1) { // 두 원소가 같은 집합에 있는지 확인하는 연산
                    // find(a)와 find(b)가 같다면 두 원소는 같은 집합에 속해 있다.
                    if (find(a) == find(b)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }
            }
            // 최종 결과 출력
            System.out.printf("#%d %s\n", tc, sb.toString());
        }
    }
}