package 백준.DFSxBFS;

import java.util.*;
import java.io.*;

public class Main_16954_움직이는미로탈출 {
    static int[][][] graph = new int[10][8][8];
    static int[] dx = {0, -1, 1, 0, 0, 1, -1, 1, -1};
    static int[] dy = {0, 0, 0, -1, 1, 1, -1, -1, 1};

    static class pos {
        int row;
        int col;
        int time;

        public pos(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;

        }
    }

    static boolean inRange(int row, int col) {
        if (row < 0 || row >= 8 || col < 0 || col >= 8) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            String row = br.readLine();
            for (int j = 0; j < 8; j++) {
                char elem = row.charAt(j);
                if (elem == '#') {
                    graph[0][i][j] = -1;
                }
            }
        }

        for (int i = 1; i < 8; i++) {
            for (int r = 0; r < 8; r++) {
                for (int c = 0; c < 8; c++) {
                    if (graph[i - 1][r][c] == -1) {
                        if (r + 1 < 8)
                            graph[i][r + 1][c] = -1;
                    }
                }
            }
        }
//        for (int r = 0; r < 8; r++) {
//            for (int c = 0; c < 8; c++) {
//                if (graph[8][r][c] < 0) {
//                    System.out.printf("#");
//                } else {
//                    System.out.printf(".");
//                }
//            }
//            System.out.println();
//        }
        Queue<pos> q = new LinkedList<>();
        q.add(new pos(7, 0, 0));
        while (!q.isEmpty()) {
            pos poll = q.poll();
            int row = poll.row;
            int col = poll.col;
            int time = poll.time;
//            System.out.printf("row : %d, col : %d, time : %d\n", row, col, time);

            if (row == 0 && col == 7 || time >= 8) {
                System.out.println(1);
                return;
            }
            if (graph[time][row][col] < 0) {
                continue;
            }
            for (int i = 0; i < 9; i++) {
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if (!inRange(nrow, ncol)) continue;
                if (graph[time][nrow][ncol] < 0) continue;
                q.add(new pos(nrow, ncol, time + 1));
            }

        }
        System.out.println(0);


    }
}
