package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main_10026_적록색약 {
    static char[][] graph;
    static char[][] graph2;

    static int[][] visit;
    static int n;
    static int[] row_move = {-1, 1, 0, 0};
    static int[] col_move = {0, 0, -1, 1};
    static ArrayList<ArrayList<Color>> green_store = new ArrayList<>();
    static class Color{
        int row;
        int col;

        public Color(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void bfs(int row, int col, char color){
        Queue<Color> q = new LinkedList<>();
        q.add(new Color(row,col));

        while (!q.isEmpty()) {
            Color poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int n_row = poll.row + row_move[i];
                int n_col = poll.col + col_move[i];
                if(!validate(n_row,n_col) || visit[n_row][n_col] == 1 || graph[n_row][n_col] != color) continue;
                visit[n_row][n_col] = 1;
                q.add(new Color(n_row, n_col));
            }
        }
        return;
    }
    public static void bfs2(int row, int col, char color){
        Queue<Color> q = new LinkedList<>();
        q.add(new Color(row,col));
        while (!q.isEmpty()) {
            Color poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int n_row = poll.row + row_move[i];
                int n_col = poll.col + col_move[i];
                if(!validate(n_row,n_col) || visit[n_row][n_col] == 1)continue;
                if(graph2[n_row][n_col] != color) continue;
                visit[n_row][n_col] = 1;
                q.add(new Color(n_row, n_col));
            }
        }
        return;
    }
    static boolean validate(int row, int col) {
        if(row < 0 || row>=n || col < 0 || col >=n) return false;
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new char[n][n];
        graph2 = new char[n][n];

        visit = new int[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                graph[i][j] = str.charAt(j);
                graph2[i][j] = str.charAt(j);
                if(graph2[i][j] == 'G') graph2[i][j] = 'R';
            }
        }
        int normal_sol = 0;
        int color_sol = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visit[i][j] == 0){
                    bfs(i,j,graph[i][j]);
                    normal_sol++;
                }
            }
        }

        visit = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visit[i][j] == 0){
                    bfs2(i,j,graph2[i][j]);
                    color_sol++;
                }
            }
        }

        System.out.println(normal_sol + " " + color_sol);

    }

}
