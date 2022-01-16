package 백준.구현;

import java.io.*;
import java.util.*;

class Node {
    int row;
    int col;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}

public class Main_2573_빙산 {
    static int n, m;
    static int[][] graph;
    static int[][] copyGraph;
    static int[][] visit;
    static Queue<Node> q = new LinkedList<>();
    static boolean stop = false;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void copyBoard(int[][] originBoard, int[][] targetBoard) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                targetBoard[i][j] = originBoard[i][j];
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        copyGraph = new int[n][m];
        visit = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int n = Integer.parseInt(st.nextToken());
                graph[i][j] = n;
                if (graph[i][j] > 0) {
                    q.add(new Node(i, j));
                }
            }
        }

        int year = 1;
        while (!q.isEmpty()) {
            // 녹이기
            melt();
            // 덩어리 개수 구하기
//            drawGraph(graph);
            int numArea =checkAreaNum();
            if(numArea >= 2){
                System.out.println(year);
                return;
            }
            year++;
        }
        System.out.println(0);
    }
    static void drawGraph(int[][] graph){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.printf("%d ",graph[i][j]);
            }
            System.out.println();
        }
    }
    static boolean validation(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    static void melt() {
        Iterator<Node> iterator = q.stream().iterator();
        copyGraph = new int[n][m];
        int count = q.size();
        while (count > 0) {
            Node node = q.poll();
            int value = graph[node.row][node.col];
            for (int i = 0; i < 4; i++) {
                int nRow = node.row + dx[i];
                int nCol = node.col + dy[i];
                if (!validation(nRow, nCol)) continue;
                if (graph[nRow][nCol] == 0) value--;
            }
            if(value > 0) {
                copyGraph[node.row][node.col] = value;
                q.add(node);
            }
            count--;
        }
        copyBoard(copyGraph,graph);
    }
    static int checkAreaNum(){
        Iterator<Node> iterator = q.stream().iterator();
        visit = new int[n][m];
        int count = 0;
        while (iterator.hasNext()) {
            Node node = iterator.next();
            int result =  dfs(node.row, node.col);
            if(result > 0) count++;
        }
        return count;
    }
    static int dfs(int row ,int col){
        if(visit[row][col] == 1) return 0;
        visit[row][col] = 1;
        int count = 1;
        for (int i = 0; i < 4; i++) {
            int nRow = row + dx[i];
            int nCol = col + dy[i];
            if(!validation(nRow, nCol)) continue;
            if(graph[nRow][nCol] == 0) continue;
            count += dfs(nRow, nCol);
        }
        return count;
    }

}
