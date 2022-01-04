package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_곡예비행_21923 {
    static int[][] graph;
    static int n, m;
    static int[][] dpUpFromBelow;
    static int[][] dpUpFromLeft;
    static int[][] visitUp;

    static int[] flyUpRowMove = {-1, 0};
    static int[] flyUpColMove = {0, 1};

    static int[][] dpDown;
    static int[][] visitDown;
    static int[] flyDownRowMove = {-1, 0};
    static int[] flyDownColMove = {0, -1};

    static class Node {
        int row;
        int col;
        int comeFrom; //0 is from below , 1 is from left

        public Node(int row, int col, int comeFrom) {
            this.row = row;
            this.col = col;
            this.comeFrom = comeFrom;
        }
    }

    public static void upFly(int row, int col, int accumSum, int comeFrom) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row, col, -1));
        visitUp[row][col] = 1;
        dpUpFromBelow[row][col] = graph[row][col];
        dpUpFromLeft[row][col] = graph[row][col];
        while (!q.isEmpty()) {
            Node poll = q.poll();
            for (int i = 0; i < 2; i++) {
                int nrow = poll.row + flyUpRowMove[i];
                int ncol = poll.col + flyUpColMove[i];
                if (!validation(nrow, ncol)) continue;
                if (visitUp[nrow][ncol] == 0) {
                    q.add(new Node(nrow, ncol, i));
                    visitUp[nrow][ncol] = 1;
                }
                if (i == 0) {
                    dpUpFromBelow[nrow][ncol] =
                            Math.max(dpUpFromBelow[poll.row][poll.col],
                                    dpUpFromLeft[poll.row][poll.col]) + graph[nrow][ncol];
                } else if (i == 1) {
                    dpUpFromLeft[nrow][ncol] =
                            Math.max(dpUpFromBelow[poll.row][poll.col],
                                    dpUpFromLeft[poll.row][poll.col]) + graph[nrow][ncol];
                }
            }
        }
    }

    public static void downFly(int row, int col, int accumSum) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(row, col, -1));
        visitDown[row][col] = 1;
        dpDown[row][col] = graph[row][col];
        while (!q.isEmpty()) {
            Node poll = q.poll();
//            System.out.printf("row : %d, col :%d\n",poll.row,poll.col);
            for (int i = 0; i < 2; i++) {
                int nrow = poll.row + flyDownRowMove[i];
                int ncol = poll.col + flyDownColMove[i];
                if (!validation(nrow, ncol)) continue;
                if (visitDown[nrow][ncol] == 0) {
                    q.add(new Node(nrow, ncol, i));
                    visitDown[nrow][ncol] = 1;
                    dpDown[nrow][ncol] = graph[nrow][ncol] + dpDown[poll.row][poll.col];
//                    System.out.printf("dpDown[%d][%d] : %d\n",nrow,ncol,dpDown[nrow][ncol]);
                }else{
                    dpDown[nrow][ncol] = Math.max(graph[nrow][ncol] + dpDown[poll.row][poll.col], dpDown[nrow][ncol]);
//                    System.out.printf("dpDown[%d][%d] : %d\n",nrow,ncol,dpDown[nrow][ncol]);

                }
            }
        }
    }

    static boolean validation(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= m) return false;
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        dpUpFromBelow = new int[n][m];
        dpUpFromLeft = new int[n][m];
        visitUp = new int[n][m];
        dpDown = new int[n][m];
        visitDown = new int[n][m];
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                dpUpFromLeft[i][j] = Integer.MIN_VALUE;
                dpUpFromBelow[i][j] = Integer.MIN_VALUE;
                dpDown[i][j] = Integer.MIN_VALUE;
            }
        }


        upFly(n - 1, 0, 0, -1);
        downFly(n -1 , m -1 ,0);
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.printf("%d ", Math.max(dpUpFromBelow[i][j],dpUpFromLeft[i][j]));
//            }
//            System.out.println();
//        }
//
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.printf("%d ", dpDown[i][j]);
//            }
//            System.out.println();
//        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max = Math.max(max, Math.max(dpUpFromBelow[i][j],dpUpFromLeft[i][j]) + dpDown[i][j]);
            }
        }
        System.out.println(max);
    }
}
