package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17090_미로탈출하기 {
    static int n, m;
    static char[][] graph;
    static int[][] dp;
    static int[][] visit;

    static boolean validation(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    static int dfs(int row, int col) {
//        System.out.printf("row : %d , col : %d \n", row ,col);
        if (!validation(row, col)) return 1;
        if (visit[row][col] == 1) return dp[row][col];
//        System.out.println("cut");
        char word = graph[row][col];
        visit[row][col] = 1;
        int count = 0;
        switch (word) {
            case 'U':
                count = dfs(row - 1, col);
                break;
            case 'D':
                count = dfs(row + 1, col);
                break;
            case 'L':
                count = dfs(row, col - 1);
                break;
            case 'R':
                count = dfs(row, col + 1);
                break;
        }
        return dp[row][col] = count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        dp = new int[n][m];
        visit = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j);
            }
        }

        int sol = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int tmp = dfs(i, j);
                sol += tmp;
            }
        }
        System.out.println(sol);

    }
}
