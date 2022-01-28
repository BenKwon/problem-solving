package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23288_주사위굴리기2 {
    static int n, m, k;
    static int[][] board;
    static int[] dice;
    static int[] dx = {0, 1, 0, -1}; // 동 남 서 북
    static int[] dy = {1, 0, -1, 0};

    static void moveDice(int dir) { //dir : 0 : 동 : 1 서 2남 3 북
        int[] newDice = new int[7];
        for (int i = 1; i < 7; i++) {
            newDice[i] = dice[i];
        }
        if (dir == 0) { // 동
            newDice[1] = dice[4];
            newDice[3] = dice[1];
            newDice[4] = dice[6];
            newDice[6] = dice[3];
        } else if (dir == 2) { // 서
            newDice[1] = dice[3];
            newDice[3] = dice[6];
            newDice[4] = dice[1];
            newDice[6] = dice[4];
        } else if (dir == 1) { // 남
            newDice[2] = dice[6];
            newDice[1] = dice[2];
            newDice[5] = dice[1];
            newDice[6] = dice[5];
        } else { //북
            newDice[2] = dice[1];
            newDice[1] = dice[5];
            newDice[5] = dice[6];
            newDice[6] = dice[2];
        }
        dice = newDice;
    }

    static boolean inRange(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        dice = new int[7];
        for (int i = 1; i < 7; i++) {
            dice[i] = i;
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //처음에 동쪽으로 1번 이동
        int curRow = 0;
        int curCol = 1;
        int dir = 0;
        moveDice(0);
        int diceBelow = dice[6];
        int curBoard = board[curRow][curCol];
        if (diceBelow > curBoard) {
            dir = (dir + 1) % 4;
        } else if (diceBelow < curBoard) {
            dir = dir - 1 < 0 ? 3 : dir - 1;
        }
        k--;
        int totalScore = getScore(curRow, curCol);
        for (int i = 0; i < k; i++) {
//            System.out.println("--------------------------------");
            //주사위 다음 좌표
            int nrow = curRow + dx[dir];
            int ncol = curCol + dy[dir];
            //이동방향 칸이 없으면 반대방향으로 방향 변경
            if (!inRange(nrow, ncol)) {
                dir = otherDir(dir);
            }
//            System.out.println("dir = " + dir);
            //주사위 좌표 확정
            curRow += dx[dir];
            curCol += dy[dir];
//            System.out.printf("currow : %d, curcol : %d \n", curRow, curCol);
            //주사위 굴리기
            moveDice(dir);
            //점수 추가
            totalScore += getScore(curRow, curCol);

            //주사위 이동 방향 결정
            diceBelow = dice[6];
//            System.out.println("diceBelow = " + diceBelow);
            curBoard = board[curRow][curCol];
            if (diceBelow > curBoard) {
                dir = (dir + 1) % 4;
            } else if (diceBelow < curBoard) {
                dir = dir - 1 < 0 ? 3 : dir - 1;
            }
        }
        System.out.println(totalScore);
    }

    static int otherDir(int dir) {
//        System.out.println("벽에 부딪힘! 방향 반대로");
        if (dir == 0) return 2;
        if (dir == 1) return 3;
        if (dir == 2) return 0;
        if (dir == 3) return 1;
        return -1;
    }

    static int getScore(int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        int[][] visit = new int[n][m];
        int initScore = board[row][col];
        int count = 0;
        visit[row][col] = 1;
        q.add(new int[]{row, col});
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            count++;
            for (int i = 0; i < 4; i++) {
                int nRow = poll[0] + dx[i];
                int nCol = poll[1] + dy[i];
                if (!inRange(nRow, nCol)) continue;
                if (board[nRow][nCol] != initScore) continue;
                if(visit[nRow][nCol] == 1) continue;
                visit[nRow][nCol] = 1;
                q.add(new int[]{nRow, nCol});
            }
        }
        return count * initScore;
    }
}
