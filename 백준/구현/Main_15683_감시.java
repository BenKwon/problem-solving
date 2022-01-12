package 백준.구현;

import java.util.*;
import java.io.*;

class cctv {
    int row;
    int col;
    int type;

    public cctv(int row, int col, int type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    @Override
    public String toString() {
        return "cctv{" +
                "row=" + row +
                ", col=" + col +
                ", type=" + type +
                '}';
    }
}

public class Main_15683_감시 {
    static int n, m;
    static int[][] graph;
    static ArrayList<cctv> cameras = new ArrayList<>();
    static int numOfCamera;
    static int minNumOfNoVisibleArea = Integer.MAX_VALUE;
    static void dfs(int cctvIndex) {
        if (cctvIndex == numOfCamera) {
//            System.out.println("----------------------------------------");
//            drawGraph(graph);
//            System.out.println("----------------------------------------");
//            System.out.println("num :  " + findNumOfNoVisibleArea());
            minNumOfNoVisibleArea = Math.min(minNumOfNoVisibleArea, findNumOfNoVisibleArea());
            return;
        }
        cctv cctv = cameras.get(cctvIndex);
//        System.out.println(cctv.toString());
        int[][] tmpGraph = new int[n][m];
        copyBoard(graph,tmpGraph);
        switch (cctv.type){
            case 1:
                for (int direction = 0; direction < 4; direction++) {
                    checkVisibleAreaController(cctv.row,cctv.col,cctv.type, direction);
                    dfs(cctvIndex + 1);
                    copyBoard(tmpGraph, graph);
                }
                break;
            case 2:
                for (int direction = 0; direction < 2; direction++) {
                    checkVisibleAreaController(cctv.row,cctv.col,cctv.type, direction);
                    dfs(cctvIndex + 1);
                    copyBoard(tmpGraph, graph);
                }
                break;
            case 3:
                for (int direction = 0; direction < 4; direction++) {
                    checkVisibleAreaController(cctv.row,cctv.col,cctv.type, direction);
                    dfs(cctvIndex + 1);
                    copyBoard(tmpGraph, graph);
                }
                break;
            case 4:
                for (int direction = 0; direction < 4; direction++) {
                    checkVisibleAreaController(cctv.row,cctv.col,cctv.type, direction);
                    dfs(cctvIndex + 1);
                    copyBoard(tmpGraph, graph);
                }
                break;
            case 5:
                checkVisibleAreaController(cctv.row,cctv.col,cctv.type, 0);
                dfs(cctvIndex + 1);
                copyBoard(tmpGraph, graph);
                break;
        }
    }

    static void copyBoard(int[][] origin, int[][] target) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                target[i][j] = origin[i][j];
            }
        }
    }
    static int findNumOfNoVisibleArea() {
        int numOfNoVisibleArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0)
                    numOfNoVisibleArea++;
            }
        }
        return numOfNoVisibleArea;
    }

    static void drawGraph(int[][] graph) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(graph[i][j] < 0)
                    System.out.printf("%d ",graph[i][j]);
                else
                    System.out.printf(" %d ",graph[i][j]);
            }
            System.out.println();
        }
    }

    static void checkVisibleAreaController(int row, int col, int type, int direction) {
        if (type == 1) {
            checkVisibleAreaTypeOne(row, col, direction); //direction 상하좌우 :0123
        } else if (type == 2) {
            checkVisibleAreaTypeTwo(row, col, direction); //direction 상하 0, 좌우 1
        } else if (type == 3) {
            checkVisibleAreaTypeThree(row, col, direction); //direction 상우:0 우하:1 하좌:2 좌상:#
        } else if(type ==4){
            checkVisibleAreaTypeFour(row, col, direction);
        } else{
            checkVisibleAreaTypeFive(row, col);
        }
    }

    private static void upCheck(int row, int col) {
//        System.out.println("up");
        for (int i = row - 1; i >= 0; i--) {
            if (graph[i][col] == 6) break;
            if (graph[i][col] != 0) continue;
            graph[i][col] = -1;
        }
//        drawGraph(graph);
    }

    private static void underCheck(int row, int col) {
//        System.out.println("under");
        for (int i = row + 1; i < n; i++) {
            if(graph[i][col] == 6) break;
            if (graph[i][col] != 0) continue;
            graph[i][col] = -1;
        }
//        drawGraph(graph);
    }

    private static void leftCheck(int row, int col) {
//        System.out.println("left");
        for (int i = col - 1; i >= 0; i--) {
            if (graph[row][i] == 6) break;
            if (graph[row][i] != 0) continue;
            graph[row][i] = -1;
        }
//        drawGraph(graph);

    }

    private static void rightCheck(int row, int col) {
//        System.out.println("right");
        for (int i = col + 1; i < m; i++) {
            if (graph[row][i] == 6) break;
            if (graph[row][i] != 0) continue;
            graph[row][i] = -1;
        }
//        drawGraph(graph);
    }


    static void checkVisibleAreaTypeOne(int row, int col, int direction) {
        if (direction == 0) {
            upCheck(row, col);
        } else if (direction == 1) {
            underCheck(row, col);
        } else if (direction == 2) {
            leftCheck(row, col);
        } else {
            rightCheck(row, col);
        }
    }

    static void checkVisibleAreaTypeTwo(int row, int col, int direction) {
        if (direction == 0) {
            upCheck(row, col);
            underCheck(row, col);
        } else if (direction == 1) {
            leftCheck(row, col);
            rightCheck(row, col);
        }
    }

    static void checkVisibleAreaTypeThree(int row, int col, int direction) {
//        System.out.println("direction : " + direction);
        if (direction == 0) { //상 우
            upCheck(row, col);
            rightCheck(row, col);
        } else if (direction == 1) {// 우 하
            rightCheck(row, col);
            underCheck(row, col);
        } else if (direction == 2) {// 하 좌
            underCheck(row, col);
            leftCheck(row, col);
        } else {//좌 상
            leftCheck(row, col);
            upCheck(row, col);
        }
    }

    static void checkVisibleAreaTypeFour(int row, int col, int direction) {
        if (direction == 0) {//좌상우
            leftCheck(row, col);
            upCheck(row, col);
            rightCheck(row, col);
        } else if (direction == 1) {//상우하
            upCheck(row, col);
            rightCheck(row, col);
            underCheck(row, col);
        } else if (direction == 2) {//우하좌
            rightCheck(row, col);
            underCheck(row, col);
            leftCheck(row, col);
        } else {//하좌상
            underCheck(row, col);
            leftCheck(row, col);
            upCheck(row, col);
        }
    }

    static void checkVisibleAreaTypeFive(int row, int col) {
        upCheck(row, col);
        underCheck(row, col);
        leftCheck(row, col);
        rightCheck(row, col);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int type = Integer.parseInt(st.nextToken());
                graph[i][j] = type;
                if (type == 0 || type == 6) continue;
                cameras.add(new cctv(i, j, type));
            }
        }
        numOfCamera = cameras.size();
        dfs(0);
        System.out.println(minNumOfNoVisibleArea);

    }

}
