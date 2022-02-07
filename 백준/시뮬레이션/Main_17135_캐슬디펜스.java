package 백준.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17135_캐슬디펜스 {
    static class pos {
        int row;
        int col;

        public pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static int n, m, d;
    static int[][] board;
    static ArrayList<pos> enemies = new ArrayList<>();
    static int kill = 0;
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 1) {
                    enemies.add(new pos(i, j));
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    simulation(i, j, k);
                }
            }
        }
        System.out.println(max);
    }

    static void arrCopy(ArrayList<pos> origin, ArrayList<pos> target) {
        for (int i = 0; i < origin.size(); i++) {
            target.add(new pos(origin.get(i).row, origin.get(i).col));
        }
    }


    static int simulation(int a, int b, int c) {
        ArrayList<pos> tmp = new ArrayList<>();
        arrCopy(enemies, tmp);
//        for (int i = 0; i < tmp.size(); i++) {
//            System.out.printf("[ %d , %d ]\n",tmp.get(i).row, tmp.get(i).col);
//        }
        kill = 0;
//        System.out.printf("a : %d , b : %d, c: %d\n",a,b,c);
        int[] archer = {a, b, c};
        while (!tmp.isEmpty()) {
            ArrayList<pos> target = new ArrayList<>();
            int[][] checkTarget = new int[n][m];
            for (int i = 0; i < 3; i++) {
                pos result = findTargetEnemy(archer[i], tmp, checkTarget);
                if (result.row == -1) continue;
                if (checkTarget[result.row][result.col] > 1) continue;
                target.add(result);
            }
            kill += target.size();
//            System.out.println("kill = " + kill);
            ArrayList<pos> newtmp = new ArrayList<>();
            for (pos p : tmp) {
                boolean pass = false;
                for (pos t : target) {
//                    System.out.printf("target row,col : [%d, %d]\n",t.row,t.col);
                    if (p.row == t.row && p.col == t.col) pass = true;
                }
                if (!pass) {
                    newtmp.add(p);
                }
            }

            tmp = newtmp;
//            for (int i = 0; i < tmp.size(); i++) {
//                System.out.printf("[ %d , %d ]\n",tmp.get(i).row, tmp.get(i).col);
//            }
//            System.out.println(tmp.size());
            tmp = moveEnemy(tmp);
        }
        max = Math.max(kill, max);
        return 0;
    }

    static ArrayList<pos> moveEnemy(ArrayList<pos> tmp) {
        ArrayList<pos> newtmp = new ArrayList<>();
        for (pos p : tmp) {
            if (p.row + 1 >= n) continue;
            newtmp.add(new pos(p.row + 1, p.col));
        }
        return newtmp;
    }

    static pos findTargetEnemy(int archerCol, ArrayList<pos> tmp, int[][] check) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[2] == b[2]) {
                    return a[1] - b[1];
                } else {
                    return a[2] - b[2];
                }
            }
        });
        for (pos p : tmp) {
            int dis = Math.abs(p.row - n) + Math.abs(p.col - archerCol);
            if (dis <= d)
                pq.add(new int[]{p.row, p.col, dis});
        }
        if (pq.isEmpty()) {
            return new pos(-1, -1);
        }
        int[] poll = pq.poll();
        check[poll[0]][poll[1]]++;
        return new pos(poll[0], poll[1]);
    }
}
