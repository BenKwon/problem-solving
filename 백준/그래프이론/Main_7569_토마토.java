package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569_토마토 {
    static int[][][] graph;
    static int[][][] visit;
    static int n, m, h;
    static ArrayList<box> start = new ArrayList<>();
    static int[] row_move = {-1, 1, 0, 0, 0, 0};
    static int[] col_move = {0, 0, -1, 1, 0, 0};
    static int[] h_move = {0, 0, 0, 0, -1, 1};
    static int sol = 0;
    static int total_zero_tomato = 0;

    static void bfs() {
        Queue<box> q = new LinkedList<>();
        for (box box : start) {
            q.add(box);
        }
        while (!q.isEmpty()) {
            box poll = q.poll();
            for (int i = 0; i < 6; i++) {
                int n_h = poll.h + h_move[i];
                int n_row = poll.row + row_move[i];
                int n_col = poll.col + col_move[i];
                if (!validation(n_h, n_row, n_col)) continue;
                if (visit[n_h][n_row][n_col] == 1) continue;
                if (graph[n_h][n_row][n_col] != 0) continue;
                q.add(new box(n_h, n_row, n_col, poll.time + 1));
                graph[n_h][n_row][n_col] = 1;
                total_zero_tomato--;
                sol = Math.max(sol, poll.time + 1);
            }
        }
        if (total_zero_tomato != 0) System.out.println(-1);
        else System.out.println(sol);

    }

    static boolean validation(int height, int row, int col) {
        if (height < 0 || height >= h || row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        graph = new int[h][n][m];
        visit = new int[h][n][m];

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    graph[k][i][j] = Integer.parseInt(st.nextToken());
                    if (graph[k][i][j] == 1) start.add(new box(k, i, j, 0));
                    else if (graph[k][i][j] == 0) total_zero_tomato++;
                }
            }
        }
        bfs();

    }

    static class box {
        int h;
        int row;
        int col;
        int time;

        public box(int h, int row, int col, int time) {
            this.h = h;
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }
}
