package programmers;

import java.util.ArrayList;

import java.util.*;
import java.io.*;

public class 자물쇠와열쇠 {
    public static void main(String[] args) {

        int[][] lock = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        int[][] key  = {{1, 1, 1}, {1, 1, 1}, {0, 0, 0}};
//        int[][] key = {{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 1, 0}, {1, 1, 0, 1}};
//        int[][] lock = {{1, 1, 1, 1, 1}, {1, 1, 1, 1, 1}, {0, 1, 0, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 1, 1}};

        System.out.println(solution(key, lock));
    }

    static int n, m, k;
    static int[][] board;
    static ArrayList<int[]> keyPosList = new ArrayList<>();
    static ArrayList<int[]> lockPosList = new ArrayList<>();

    static boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;
        m = key.length;
        n = lock.length;
        k = n + ((m - 1) * 2);
        board = new int[k][k];
        int sRow = m - 1;
        int sCol = m - 1;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (key[i][j] == 1) {
                    keyPosList.add(new int[]{i, j});
                }
            }
        }
        //자물쇠 board로 옮기기
        for (int i = sRow; i < sRow + n; i++) {
            for (int j = sCol; j < sCol + n; j++) {
                board[i][j] = lock[i - sRow][j - sCol];
                if (board[i][j] == 0) {
                    lockPosList.add(new int[]{i, j});
                }
            }
        }

//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < m; j++) {
//                boolean a = true;
//                for(int[] next :keyPosList){
//                    int row = next[0];
//                    int col = next[1];
//                    if(row ==i && col == j){
//                        a = false;
//                        System.out.printf("%d ",1);
//                        break;
//                    }
//                }
//                if(a) System.out.printf("%d ", 0);
//            }
//            System.out.println();
//        }
        if(lockPosList.isEmpty()) return true;
        for (int i = 0; i < keyPosList.size(); i++) {
            for (int j = 0; j < lockPosList.size(); j++) {
                if (check(i, j)) {
                    answer = true;
                    break;
                }
            }
            System.out.println("+++++++++++++++++++++++");
        }

        return answer;
    }

    static boolean check(int keyPosIdx, int lockPosIdx) {
        int[] lockPos = lockPosList.get(lockPosIdx);
        System.out.println("-----------------------------");
        System.out.printf("lockPos [%d,%d]\n", lockPos[0], lockPos[1]);
        System.out.printf("keyPos [%d,%d]\n", keyPosList.get(keyPosIdx)[0], keyPosList.get(keyPosIdx)[1]);

        boolean find = false;
        //시계방향으로 90도 회전하면서 키가 맞는지 검사
        for (int i = 0; i < 4; i++) {
            rotate(i); //key 회전하기 i == 0 일땐 회전안함
//            if (find) continue;
            int[] keyPos = keyPosList.get(keyPosIdx);
            int rowDiff = lockPos[0] - keyPos[0];
            int colDiff = lockPos[1] - keyPos[1];
            int count = 0;
            //키가 맞는지 체크
            for (int k = 0; k < keyPosList.size(); k++) {
                int[] next = keyPosList.get(k);
                int row = next[0] + rowDiff;
                int col = next[1] + colDiff;
                System.out.printf(" dir : %d // -> [%d, %d] ", i, row, col);
                if(board[row][col] == 1) count = -1;
                if (board[row][col] == 0 && inRange(row, col)) {
                    System.out.printf("@ ");
                    count++;
                }
            }
            System.out.println();
            System.out.printf("cound : %d, size: %d\n",count, lockPosList.size());
            if (count == lockPosList.size()) {
                System.out.println("solution founded");
                find = true;
                break;
            }
        }
        //한번더 회전시켜서 원상복구 !!
        rotate(1);
        return find;
    }

    static boolean inRange(int row, int col) {
        if (row >= m - 1 && row < (m - 1) + n) {
            if (col >= m - 1 && col < (m - 1) + n) return true;
        }
        return false;
    }

    static void rotate(int dir) {
        if (dir == 0) return;
        for (int i = 0; i < keyPosList.size(); i++) {
            int[] next = keyPosList.get(i);
            int row = next[1];
            int col = Math.abs((m - 1) - next[0]);
            keyPosList.set(i, new int[]{row, col});
        }
    }


}
