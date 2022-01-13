package 백준.그래프이론;

import java.util.*;
import java.io.*;

public class Main_16946_벽부수고이동하기4 {
    static int n, m;
    static int[][] graph;
    static int[][] visit;
    static int[][] copyGraph;
    static ArrayList<Node> zeros = new ArrayList<>();
    static ArrayList<Node> walls = new ArrayList<>();

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] areaNum;

    static class Node {
        int row;
        int col;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int dfs(int row, int col, int area) {
        if (visit[row][col] == 1) return 0;
        int count = 1;
        visit[row][col] = 1;
        graph[row][col] = area;
        for (int i = 0; i < 4; i++) {
            int nRow = row + dx[i];
            int nCol = col + dy[i];
            if (!validator(nRow, nCol)) continue;
            if(graph[nRow][nCol] != 0) continue;
            count += dfs(nRow, nCol, area);
        }
        return count;
    }

    public static boolean validator(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visit = new int[n][m];
        copyGraph = new int[n][m];
        areaNum = new int[1000003];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j) - 48;
                if (graph[i][j] == 0) {
                    zeros.add(new Node(i, j));
                } else if (graph[i][j] == 1) {
                    walls.add(new Node(i, j));
                }
            }
        }
        for (int i = 0; i < zeros.size(); i++) {
            int count = dfs(zeros.get(i).row, zeros.get(i).col, i + 2);
            if (count > 0) {
                areaNum[i + 2] = count;
            }
        }

        for (int i = 0; i < walls.size(); i++) {
            Node node = walls.get(i);
            int row = node.row;
            int col = node.col;
            int movable = 1;
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                int nRow = row + dx[j];
                int nCol = col + dy[j];
                if(!validator(nRow, nCol))continue;
                set.add(graph[nRow][nCol]);
            }
            Iterator<Integer> iter = set.iterator();
            while(iter.hasNext()){
                int next = iter.next();
                movable += areaNum[next];
            }
            copyGraph[node.row][node.col] = movable%10;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                bw.write(copyGraph[i][j] + "");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
        br.close();
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.printf("%d ",graph[i][j]);
//            }
//            System.out.println();
//        }
    }
}
