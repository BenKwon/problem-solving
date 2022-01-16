package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_2186_문자판 {
    static HashMap<Character, ArrayList<Pos>> posMap = new HashMap<>();
    static int n, m, k;
    static char[][] graph;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][][] dp;
    static int[][][] visit;
    static String engWord = "";

    static class Pos {
        int row;
        int col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    public static int dfs(int wordIndex, Pos pos) {
        if (wordIndex == engWord.length() - 1) return 1;
        if (visit[wordIndex][pos.row][pos.col] == 1) return dp[wordIndex][pos.row][pos.col];
        char nextAlphabet = engWord.charAt(wordIndex + 1);
//        System.out.printf("wordIndex : %d \n", wordIndex);
//        System.out.println(pos);
        int count = 0;
        for (int lk = 1; lk <= k; lk++) {
            for (int i = 0; i < 4; i++) {
                int nrow = pos.row + (dx[i] * lk);
                int ncol = pos.col + (dy[i] * lk);
                if (!validator(nrow, ncol)) continue;
                if (graph[nrow][ncol] != nextAlphabet) continue;
                count += dfs(wordIndex + 1, new Pos(nrow, ncol));
            }
        }
        visit[wordIndex][pos.row][pos.col] = 1;
        return dp[wordIndex][pos.row][pos.col] = count;
    }

    static boolean validator(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new char[n][m];
        dp = new int[80][n][m];
        visit = new int[80][n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j);
                if (!posMap.containsKey(graph[i][j])) {
                    posMap.put(graph[i][j], new ArrayList<>());
                }
                posMap.get(graph[i][j]).add(new Pos(i, j));
            }
        }
        engWord = br.readLine();
        ArrayList<Pos> startCharPos = posMap.get(engWord.charAt(0));
        ArrayList<Pos> endCharPos = posMap.get(engWord.charAt(engWord.length() - 1));
        if (startCharPos == null || endCharPos == null) {
            System.out.println(0);
            return;
        }
        for (int i = 0; i < endCharPos.size(); i++) {
            Pos pos = endCharPos.get(i);
            dp[engWord.length() - 1][pos.row][pos.col] = 1;
            visit[engWord.length() - 1][pos.row][pos.col] = 1;
        }
        int sol = 0;
        for (int i = 0; i < startCharPos.size(); i++) {
            sol += dfs(0, startCharPos.get(i));
        }
        System.out.println(sol);
    }
}
