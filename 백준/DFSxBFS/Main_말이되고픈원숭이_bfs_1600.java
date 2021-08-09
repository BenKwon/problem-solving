package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.*;

public class Main_말이되고픈원숭이_bfs_1600 {
    public static int[][] graph;
    public static int[][][] visit; //각 좌표에서 상하좌우로 들어올때 동작수
    static int k, w, h;
    static int[] row_move = new int[]{-1, 1, 0, 0};
    static int[] row_horse_move = new int[]{-2, -1, 1, 2, -2, -1, 1, 2};
    static int[] col_move = new int[]{0, 0, -1, 1};
    static int[] col_horse_move = new int[]{-1, -2, -2, -1, 1, 2, 2, 1};

    static int min = Integer.MAX_VALUE;

    public static void bfs() {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0,0,0,k));
        visit[0][0][0] = 1;

        while (!q.isEmpty()) {
            Pair poll = q.poll();
            if (poll.row == h - 1 && poll.col == w - 1) {
                System.out.println(poll.cnt);
                return;
            }
            System.out.println(visit[poll.row][poll.col][poll.k]);
            if(visit[poll.row][poll.col][poll.k] == 1 )continue;
//            visit[poll.row][poll.col][poll.k] = 1;
            for (int i = 0; i < 4; i++) {
                int n_row = poll.row+ row_move[i];
                int n_col = poll.col + col_move[i];
                int count = poll.cnt + 1;
                int k_remain =poll.k;
                if (n_row < 0 || n_row >= h || n_col < 0 || n_col >= w || graph[n_row][n_col] == 1) {
                    continue;
                }
                if (visit[n_row][n_col][k_remain] == 1) continue;
                q.add(new Pair(n_row, n_col, count, k_remain));
            }
            if(poll.k == 0) continue;
            for (int i = 0; i < 8; i++) {
                int n_row = poll.row + row_horse_move[i];
                int n_col = poll.col + col_horse_move[i];
                int count = poll.cnt + 1;
                int k_remain = poll.k - 1;
                if (n_row < 0 || n_row >= h || n_col < 0 || n_col >= w || graph[n_row][n_col] == 1) {
                    continue;
                }
                if (visit[n_row][n_col][k_remain] == 1) continue;
                q.add(new Pair(n_row, n_col, count , k_remain ));
            }
        }
        System.out.println(-1);
    }
    static class Pair{
        int row;
        int col;
        int cnt;
        int k;

        public Pair(int row, int col, int cnt, int k) {
            this.row = row;
            this.col = col;
            this.cnt = cnt;
            this.k = k;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        graph = new int[h][w];
        visit = new int[h][w][31];
        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }
}
