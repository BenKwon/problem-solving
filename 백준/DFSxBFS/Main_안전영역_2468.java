package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_안전영역_2468 {
    public static int[][] graph;
    public static int[][] visit;
    public static int rain, n;
    public static ArrayList<ArrayList<int[]>> list;
    public static int[] row_move = new int[]{-1, 1, 0, 0};
    public static int[] col_move = new int[]{0, 0, -1, 1};

    public static int dfs(int row, int col) {
        Queue<int[]> q = new LinkedList<>();
        if(visit[row][col] == 1) return 0;
        visit[row][col] = 1;
        int result = 1;
        for (int i = 0; i < 4; i++) {
            int nrow = row + row_move[i];
            int ncol = col + col_move[i];
            if (nrow < 0 || nrow >= n || ncol < 0 || ncol >= n) continue;
            if (graph[nrow][ncol] <= rain) continue;
            if(visit[nrow][ncol] == 1) continue;
            result += dfs(nrow, ncol);
        }
        return result;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            list.add(new ArrayList<>());
        }
        graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                list.get(graph[i][j]).add(new int[]{i, j});

            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i <= 100; i++) {
            rain = i;
            ArrayList<int[]> tmp = new ArrayList<>();
            visit = new int[n][n];
            for (int h = rain + 1; h <= 100; h++) {
                ArrayList<int[]> cord = list.get(h);
                for (int k = 0; k < cord.size(); k++) {
                    tmp.add(cord.get(k));
                }
            }

//            System.out.printf("rain : [%d] \n",rain);
            int area_sum = 0;
            for (int j = 0; j < tmp.size(); j++) {
                int row = tmp.get(j)[0];
                int col = tmp.get(j)[1];
//                System.out.printf("row : %d , col :%d --> %d \n" ,row ,col, graph[row][col]);
                int dfs = dfs(row, col);
//                System.out.println("dfs = " + dfs);
                if(dfs > 0){
                    area_sum++;
                }
            }
            max = Math.max(max, area_sum);
//            System.out.println("===========================");
        }
        System.out.println(max);

    }
}
