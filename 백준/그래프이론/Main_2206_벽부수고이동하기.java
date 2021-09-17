package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206_벽부수고이동하기 {
    public static int n, m;
    static int[][] graph;
    static int[][][] visit;
    static int[][][] dp;
    static int[] row_move = {-1, 1, 0, 0};
    static int[] col_move = {0, 0, -1, 1};

    public static void bfs() {
        Queue<node> q = new LinkedList<>();
        q.add(new node(0, 0, 1, 1));
        visit[0][0][1] = 1;
        visit[0][0][0] = 1;

        int sol = 0;
        boolean find = false;
        while (!q.isEmpty() && !find) {
            node poll = q.poll();
//            System.out.println("q size : " + q.size());
//            System.out.printf("row :%d , col :%d , crash :%d , count %d\n", poll.row, poll.col, poll.crash, poll.count);
            for (int i = 0; i < 4; i++) {
                int nrow = poll.row + row_move[i];
                int ncol = poll.col + col_move[i];
                if (!validation(nrow, ncol)) continue;
                if (visit[nrow][ncol][poll.crash] == 1) continue;
                ;
                if (graph[nrow][ncol] == 1) {
                    if (poll.crash == 0) continue;
                    //뿌술수있다
                    q.add(new node(nrow, ncol, 0, poll.count + 1));
                } else {
                    q.add(new node(nrow, ncol, poll.crash, poll.count + 1));
                }
                visit[nrow][ncol][poll.crash] = 1;
                if (nrow == n - 1 && ncol == m - 1) {
                    sol = poll.count + 1;
                    find = true;
                    break;
                }
            }
        }
        if (find)
            System.out.println(sol);
        else
            System.out.println(-1);
    }

    static boolean validation(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    static class node {
        int row;
        int col;
        int crash;
        int count;

        public node(int row, int col, int crash, int count) {
            this.row = row;
            this.col = col;
            this.crash = crash;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visit = new int[n][m][2];
        dp = new int[n][m][2];


        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }
        if(n == 1 && m == 1){
            System.out.println(1);
            return;
        }
        bfs();

    }
}
