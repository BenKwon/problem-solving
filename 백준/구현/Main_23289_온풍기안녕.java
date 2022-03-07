package 백준.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_23289_온풍기안녕 {
    static int r, c, k;
    static int[][] board;
    static ArrayList<pos> targets = new ArrayList<>();
    static ArrayList<heater> heaters = new ArrayList<>();
    static boolean[][][][] wall;
    static int[][][] heaterVisit;
    static int[][] dx = {{0, 0, 0}, {-1, 0, 1}, {-1, 0, 1}, {-1, -1, -1}, {1, 1, 1}};
    static int[][] dy = {{0, 0, 0}, {1, 1, 1}, {-1, -1, -1}, {-1, 0, 1}, {-1, 0, 1}};

    static class pos {
        int row;
        int col;

        public pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static class heater extends pos {
        int type;
        int id;

        public heater(int row, int col, int type, int id) {
            super(row, col);
            this.type = type;
            this.id = id;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[r + 1][c + 1];
        wall = new boolean[r + 1][c + 1][r + 1][c +1];
        int heaterId = 0;
        for (int i = 1; i <= r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= c; j++) {
                int next = Integer.parseInt(st.nextToken());
                if (next == 0) continue;
                if (next == 5) {
                    targets.add(new pos(i, j));
                } else {
                    heaters.add(new heater(i, j, next, heaterId++));
                }
            }
        }
        int w = Integer.parseInt(br.readLine());
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            if(t == 0){
                wall[x][y][x - 1][y] = true;
                wall[x-1][y][x][y] = true;
            }else if(t == 1){
                wall[x][y][x][y + 1] = true;
                wall[x][y+1][x][y] = true;
            }
        }
        int chocolate = 0;
        while(true){
            runHeater();
            adjustDegree();
            edgeDownDegree();
            chocolate++;
            if(checkEnd())break;
            if(chocolate > 100){
                chocolate = 101;
                break;
            }
//            drawBoard();
//            System.out.println("----------------");
        }
//        drawBoard();
        System.out.println(chocolate);

    }

    public static void runHeater() {
        heaterVisit = new int[r + 1][c + 1][heaters.size()];
        for (heater h : heaters) {
            int[] add = new int[2];
            if(h.type == 1){
                add[1] = 1;
            }else if(h.type == 2){
                add[1] = -1;
            }else if(h.type == 3){
                add[0] = -1;
            }else{ //h.type == 4
                add[0] = 1;
            }
            int nrow = h.row + add[0];
            int ncol = h.col + add[1];
            if(!inRange(nrow,ncol))continue;
            windHeater(nrow, ncol, 5, h.id);
        }
    }
    public static void windHeater(int row, int col, int degree, int heaterId) {
        int heaterType = heaters.get(heaterId).type;
        if(degree == 0|| heaterVisit[row][col][heaterId] == 1)return;
        heaterVisit[row][col][heaterId] = 1;
        board[row][col] += degree;
//        System.out.printf("[%d,%d]\n",row,col);
        if (degree == 0) return;
        for (int i = 0; i < 3; i++) {
            int nrow = row + dx[heaterType][i];
            int ncol = col + dy[heaterType][i];
            if (!inRange(nrow, ncol)) continue;
            if (heaterVisit[nrow][ncol][heaterId] == 1) continue;
            //check wall
//            System.out.printf("nrow:[%d,%d]\n",nrow,ncol);
            if(checkWall(row,col,heaterType,i)) continue;
            windHeater(nrow, ncol, degree - 1, heaterId);
        }
    }

    public static boolean checkWall(int row, int col,int heaterType, int dir){
        boolean resultA = true;
        boolean resultB = false;
//        System.out.println("-----------------------------------------");
//        System.out.println("heaterType : " + heaterType);
        if(heaterType == 1){
            if(dir == 0){
                resultA=wall[row][col][row-1][col];
                resultB=wall[row-1][col][row-1][col+1];
            }else if(dir == 1){
                resultA = wall[row][col][row][col + 1];
            }else{
                resultA=wall[row][col][row + 1][col];
                resultB=wall[row + 1][col][row + 1][col + 1];
            }
        }else if(heaterType == 2){
            if(dir == 0){
                resultA = wall[row][col][row - 1][col];
                resultB =wall[row - 1][col][row - 1][col - 1];
            }else if(dir == 1){
                resultA = wall[row][col-1][row][col];
            }else{
                resultA = wall[row][col][row + 1][col];
                resultB = wall[row + 1][col][row + 1][col - 1];
            }
        }else if(heaterType == 3){
            if(dir == 0){
//                System.out.printf("[%d,%d]/[%d,%d]\n",row,col,row,col-1);
//                System.out.printf("[%d,%d]/[%d,%d]\n",row,col -1,row-1,col-1);
                resultA = wall[row][col][row][col - 1];
                resultB =wall[row][col - 1][row - 1][col - 1];
            }else if(dir == 1){
//                System.out.printf("[%d,%d]/[%d,%d]\n",row,col,row-1,col);
                resultA =wall[row][col][row - 1][col];
            }else{
//                System.out.printf("[%d,%d]/[%d,%d]\n",row,col,row,col+1);
//                System.out.printf("[%d,%d]/[%d,%d]\n",row,col+1,row-1,col+1);
                resultA =wall[row][col][row][col + 1];
                resultB =wall[row][col + 1][row - 1][col + 1];
            }
        }else{
            if(dir == 0){
                resultA=wall[row][col][row][col - 1];
                resultB =wall[row][col - 1][row + 1][col - 1];
            }else if(dir == 1){
                resultA = wall[row][col][row+1][col];
            }else{
                resultA = wall[row][col][row][col + 1];
                resultB= wall[row][col + 1][row + 1][col + 1];
            }
        }
//        System.out.println("a:" + resultA +", b:" + resultB);
        return (resultA || resultB);
    }


    public static void adjustDegree(){
        int[][] tmp = new int[r + 1][c + 1];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int[][][][] visit = new int[r + 1][c + 1][r + 1][c + 1];

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                int value = board[i][j];
                for (int k = 0; k < 4; k++) {
                    int nrow = i + dx[k];
                    int ncol = j + dy[k];
                    if(!inRange(nrow,ncol))continue;
                    if(visit[i][j][nrow][ncol] == 1 | visit[nrow][ncol][i][j] == 1) continue;
                    if(adjustDegreeCheckWall(i,j,nrow,ncol)) continue;
                    int targetValue = board[nrow][ncol];
                    visit[i][j][nrow][ncol] = 1;
                    if(value == targetValue) continue;
                    int diff = Math.abs(value - targetValue);
//                    System.out.printf("[%d,%d] <-> [%d,%d], diff : %d\n",i,j,nrow,ncol,diff);
                    if(value > targetValue) {
                        tmp[i][j] -= diff/4;
                        tmp[nrow][ncol] += diff/4;
                    }else{
                        tmp[i][j] += diff/4;
                        tmp[nrow][ncol] -= diff/4;
                    }
                }
            }
        }
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                board[i][j] += tmp[i][j];
            }
        }
    }
    static boolean adjustDegreeCheckWall(int row, int col, int nrow,int ncol){
        return wall[row][col][nrow][ncol];
    }

    static void edgeDownDegree(){
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                if(i == 1 || j == 1  ||  i == r || j == c){
                    if(board[i][j] >= 1) board[i][j]--;
                }
            }
        }
    }

    static boolean checkEnd(){
        boolean end = true;
        for(pos p : targets){
            if (board[p.row][p.col] < k) {
                end = false;
                break;
            }
        }
        return end;
    }
    //utils (drawBoard, inRange)
    static void drawBoard() {
        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                System.out.printf("%d ", board[i][j]);
            }
            System.out.println();
        }
    }
    static boolean inRange(int row, int col) {
        return !(row <= 0 || row > r || col <= 0 || col > c);
    }


}
