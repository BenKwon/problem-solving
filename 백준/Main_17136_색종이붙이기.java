package 백준;

import java.util.*;
import java.io.*;

public class Main_17136_색종이붙이기 {
    static int[][] board = new int[10][10];
    static int[][][] visit = new int[6][10][10];
    static ArrayList<int[]>[] starts = new ArrayList[6];
    static int totalTargetCount = 0;
    static int answer = Integer.MAX_VALUE;
    static int INF = Integer.MAX_VALUE;
    static int[] max = new int[6];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) totalTargetCount++;
            }
        }
        for (int i = 1; i <= 5; i++) {
            starts[i] = new ArrayList<>();
        }
        //각 사이즈별 시작점 수집
        for (int i = 5; i >= 1; i--) {
            check(i);
        }
        dfs(5, 0, 0);
        answer = answer == INF ? -1 : answer;
        System.out.println(answer);
    }

    static void dfs(int size, int countSize, int totalCount) {
        if (countSize > 5) return;
        if (size == 0) {
            answer = Math.min(answer, totalCount);
//            System.out.println(answer);
            return;
        }
        ArrayList<int[]> nexts = starts[size];
        System.out.println("=========start============");
        System.out.printf("size : %d, countSize : %d, totalCount :%d\n",size,countSize,totalCount);
        for (int[] next : nexts) {
            int row = next[0];
            int col = next[1];
            if(visit[size][row][col] == 1) System.out.printf("1 ");
            else System.out.printf("0 ");
        }
        System.out.println();
        System.out.println("=========end============");
        int count = 0;
        for (int[] next : nexts) {
            int row = next[0];
            int col = next[1];
            if (visit[size][row][col] == 1) continue;
            if (checkIfPossiblePaste(size, row, col)){
                count++;
                visit[size][row][col] = 1;
                paste(size, row, col);
                dfs(size, countSize + 1, totalCount + 1);
                pastOff(size, row, col);
                visit[size][row][col] = 0;
            }
        }
        if (count == 0) {
//            System.out.println("v");
            if(countSize < max[size]){
                return;
            }
            max[size] = countSize;
            dfs(size - 1, 0, totalCount);
        }
    }

    static boolean checkIfPossiblePaste(int size, int row, int col) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (board[i][j] != 1) return false;
            }
        }
        return true;
    }

    static void paste(int size, int row, int col) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] = -1;
            }
        }
    }

    static void pastOff(int size, int row, int col) {
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                board[i][j] = 1;
            }
        }
    }

    static void check(int size) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int startRow = i;
                int startCol = j;
                boolean pastePossible = true;
                //[i,j]기준으로 대각 아래로 size크기의 색종이를 붙일 수 있는지 검사
                for (int l = startRow; l < startRow + size; l++) {
                    for (int p = startCol; p < startCol + size; p++) {
                        if (!inRange(l, p) || board[l][p] != 1) pastePossible = false;
                        if (!pastePossible) break;
                    }
                }
                //색종이를 붙일 수 있을 때 board에 =-1로 표시
                if (pastePossible) {
                    starts[size].add(new int[]{startRow, startCol});
                }

            }
        }

    }

    static boolean inRange(int row, int col) {
        return !(row < 0 || row >= 10 || col < 0 || col >= 10);
    }
}
