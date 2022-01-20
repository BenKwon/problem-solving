package 백준.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_puyopuyo_11559 {
    static char[][] board;
    static int[][] visit;
    static ArrayList<pos> bombInitPos;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int explosionTotal = 0;

    static class pos {
        int row;
        int col;

        public pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static boolean inRange(int row, int col) {
        if(row < 0 || row >= 12 || col < 0 || col >= 6) return false;
        return true;
    }

    static int dfs(int row, int col, char type) {
        if (visit[row][col] == 1) return 0;
        int count = 1;
        visit[row][col] = 1;
        for (int i = 0; i < 4; i++) {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            if (!inRange(nrow, ncol)) continue;
            if (board[nrow][ncol] != type) continue;
            count += dfs(nrow, ncol, type);
        }
        return count;
    }

    static int dfsBomb(int row, int col, char type) {
        if (visit[row][col] == 1) return 0;
        int count = 1;
        visit[row][col] = 1;
        board[row][col] = '.';
//        System.out.printf("row : %d, col :%d is bombed -> board : %c\n",row,col,board[row][col]);
        for (int i = 0; i < 4; i++) {
            int nrow = row + dx[i];
            int ncol = col + dy[i];
            if (!inRange(nrow, ncol)) continue;
            if (board[nrow][ncol] != type) continue;
            count += dfsBomb(nrow, ncol, type);
        }
        return count;
    }

    static void detectExplosion() {
//        System.out.println("-------------------------------------");
        bombInitPos = new ArrayList<>();
        visit = new int[12][6];
        for (int i = 11; i >= 0; i--) {
            for (int j = 0; j < 6; j++) {
                if (board[i][j] == '.') continue;
                int count = dfs(i, j, board[i][j]);
//                System.out.printf("[%d, %d] , type : %c \n",i,j,board[i][j]);
                if (count >= 4) {
//                    System.out.println("Add!");
                    bombInitPos.add(new pos(i, j));
                }
            }
        }
    }

    static int explosion() {
        visit = new int[12][6];
        for (pos p : bombInitPos) {
            int row = p.row;
            int col = p.col;
            dfsBomb(row, col, board[row][col]);
        }
        if(bombInitPos.size()>0) explosionTotal++;
        return bombInitPos.size();
    }

    static void gravity() {
        for (int i = 0; i < 6; i++) { // i is col
            boolean findInit = false;
            ArrayList<pos> fallPos = new ArrayList<>();
            for (int j = 11; j >= 0; j--) { // j is row
                if (!findInit && board[j][i] != '.') {
                    findInit = true;
//                    System.out.printf("fall [ %d, %d]\n",j,i);
                    fallPos.add(new pos(j, i));
                } else if (board[j][i] != '.') {
                    fallPos.add(new pos(j, i));
                }
            }
            //해당 컬럼에 뿌요가 없거나 맨 밑에 이미 뿌요가 있을 경우 패스
            if (!findInit) continue;
            int fallDistance = 11 - fallPos.get(0).row;
            for (pos fall : fallPos) {
                int row = fall.row;
                int col = fall.col;
                char type = board[row][col];

                while(true){
                    if(row == 11) break;
                    if(board[row + 1][col] != '.') break;
                    row++;
                }
                board[fall.row][fall.col] = '.';
                board[row][col] = type;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        board = new char[12][6];
        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            for (int j = 0; j < 6; j++) {
                board[i][j] = str.charAt(j);
            }
        }
        while (true) {
//            System.out.println("----------------------------------");
            detectExplosion();
            if (explosion() == 0) break;
            gravity();
//            for (int i = 0; i < 12; i++) {
//                for (int j = 0; j < 6; j++) {
//                    System.out.printf("%c",board[i][j]);
//                }
//                System.out.println();
//            }
        }
//        System.out.println("----------------------------------");

//        for (int i = 0; i < 12; i++) {
//            for (int j = 0; j < 6; j++) {
//                System.out.printf("%c",board[i][j]);
//            }
//            System.out.println();
//        }
        System.out.println(explosionTotal);
    }
}
