package 백준.DFSxBFS;

import java.util.*;
import java.io.*;

public class Main_1577_도로의개수 {
    static int n, m;
    static int[][] graph;
    static int[][] visit;
    static long[][] dp;
    static ArrayList<Node[]> construction = new ArrayList<>();
    static int[] dRow = {0, 1};
    static int[] dCol = {1, 0};

    static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[m + 1][n + 1];
        int k = Integer.parseInt(br.readLine());
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            construction.add(new Node[]{new Node(b, a), new Node(d, c)});
        }
        visit = new int[m + 1][n + 1];
        dp = new long[m + 1][n + 1];
        System.out.println(dfs(0, 0));
    }

    public static long dfs(int row, int col) {
        if (row == m && col == n) return 1;
        if(visit[row][col] ==  1) return dp[row][col];
        long count = 0;
        for (int i = 0; i < 2; i++) {
            int nrow = row + dRow[i];
            int ncol = col + dCol[i];
            if (!canGo(row, col, nrow, ncol)) continue;
            count += dfs(nrow, ncol);
        }
        visit[row][col] = 1;
        return dp[row][col] = count;
    }

    public static boolean canGo(int startRow, int startCol, int destRow, int destCol) {
        if (destRow < 0 || destRow > m || destCol < 0 || destCol > n) return false;
        for (Node[] nodeArr : construction) {
            Node constNodeA = nodeArr[0];
            Node constNodeB = nodeArr[1];
            if (constNodeA.row == startRow && constNodeA.col == startCol) {
                if (constNodeB.row == destRow && constNodeB.col == destCol) {
                    return false;
                }
            }

            if (constNodeA.row == destRow && constNodeA.col == destCol) {
                if (constNodeB.row == startRow && constNodeB.col == startCol) {
                    return false;
                }
            }
        }
        return true;
    }
}
