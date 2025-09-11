import java.util.Scanner;

public class boj_1074_Z {
    static int order = 0;   // 방문 순서(카운트)
    static int N, targetRow, targetCol; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력: 2^N x 2^N 크기 배열, 목표 좌표 (R, C)
        N = sc.nextInt();
        targetRow = sc.nextInt();
        targetCol = sc.nextInt();

        // Z-모양 탐색 시작
        int size = (int) Math.pow(2, N);  
        search(0, 0, size);

        System.out.println(order);
    }

    /**
     * Z-모양 순서 탐색 (분할 정복)
     * @param row 현재 시작 행
     * @param col 현재 시작 열
     * @param size 현재 탐색하는 정사각형 크기
     */
    static void search(int row, int col, int size) {
        // base case: 크기가 2x2인 경우
        if (size == 2) {
            int midRow = row + size / 2;
            int midCol = col + size / 2;

            if (targetRow < midRow && targetCol < midCol) { // 1사분면 (좌상단)
                // 변화 없음
            } 
            else if (targetRow < midRow && targetCol >= midCol) { // 2사분면 (우상단)
                order += 1;
            } 
            else if (targetRow >= midRow && targetCol < midCol) { // 3사분면 (좌하단)
                order += 2;
            } 
            else if (targetRow >= midRow && targetCol >= midCol) { // 4사분면 (우하단)
                order += 3;
            }
            return;
        }

        // 현재 영역을 4분할
        int half = size / 2;
        int midRow = row + half;
        int midCol = col + half;
        int quadrantSize = half * half; // 한 사분면에 속하는 칸 개수

        // 목표 좌표가 어느 사분면에 있는지 판별
        if (targetRow < midRow && targetCol < midCol) { // 1사분면 (좌상단)
            search(row, col, half);
        } 
        else if (targetRow < midRow && targetCol >= midCol) { // 2사분면 (우상단)
            order += quadrantSize;
            search(row, col + half, half);
        } 
        else if (targetRow >= midRow && targetCol < midCol) { // 3사분면 (좌하단)
            order += quadrantSize * 2;
            search(row + half, col, half);
        } 
        else if (targetRow >= midRow && targetCol >= midCol) { // 4사분면 (우하단)
            order += quadrantSize * 3;
            search(row + half, col + half, half);
        }
    }
}
