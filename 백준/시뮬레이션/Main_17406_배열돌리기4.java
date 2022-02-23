package 백준.시뮬레이션;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main_17406_배열돌리기4 {
    static int n, m, k;
    static int[][] fixBoard;
    static int[][] board;
    static int[][] tmpBoard;
    static ArrayList<int[]> operation = new ArrayList<>();
    static void rotate(int startX, int startY, int endX, int endY) {
        //가로 두 변 (startX , endX 한번씩)
        for (int y = startY; y < endY; y++) {
            tmpBoard[startX][y + 1] = board[startX][y];
        }
        for (int y = startY; y < endY; y++) {
            tmpBoard[endX][y] = board[endX][y + 1];
        }
        //세로 두변 (startY, endY 한번씩)
        for (int x = startX; x < endX; x++) {
            tmpBoard[x + 1][endY] = board[x][endY];
        }
        for (int x = startX; x < endX; x++) {
            tmpBoard[x][startY] = board[x + 1][startY];
        }


    }
    static int calculateMin(){
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            int value = 0;
            for (int j = 1; j <= m; j++) {
                value += board[i][j];
            }
            min = Math.min(min, value);
        }
        return min;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        board = new int[n + 1][m + 1];
        fixBoard = new int[n + 1][m + 1];
        tmpBoard = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                fixBoard[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            operation.add(new int[]{r, c,s});
        }
        operationNum = operation.size();
        backTracking = new int[operationNum];
        for (int i = 0; i < operationNum; i++) {
            seq = new ArrayList<>();
            seq.add(i);
            backTracking[i] = 1;
            seqDfs(1);
            backTracking[i] = 0;
        }
        System.out.println(answer);


    }
    static int operationNum;
    static int[] backTracking;
    static ArrayList<Integer> seq = new ArrayList<>();
    static int answer = 2500 * 101;
    static void seqDfs(int count){
        if(count == operationNum){
//            for (int i = 0; i < seq.size(); i++) {
//                System.out.printf("%d ",seq.get(i));
//            }
//            System.out.println();
            copyBoard(fixBoard,board);
            solution();
            return;
        }
        for (int i = 0; i < operationNum; i++) {
            if(backTracking[i] == 1) continue;
            backTracking[i] = 1;
            seq.add(i);
            seqDfs(count + 1);
            seq.remove(seq.size() - 1);
            backTracking[i] = 0;
        }
    }

    static int solution(){
//        System.out.println("--------------solution()-----------------");
        for (int i = 0; i < seq.size(); i++) {
            int[] oper = operation.get(seq.get(i));
            int r = oper[0];
            int c = oper[1];
            int s = oper[2];
//            System.out.printf("r: %d , c : %d, s : %d\n",r,c,s);
            int startX = r - s;
            int startY = c - s;
            int endX = r + s;
            int endY = c + s;
            tmpBoard = new int[n + 1][m + 1];
            tmpBoard[r][c] = board[r][c];
            for (int k = 1; k <= s; k++) {
                rotate(r - k, c - k, r + k, c + k);
            }
            for (int k = startX; k <= endX; k++) {
                for (int j = startY; j <= endY; j++) {
                    board[k][j] = tmpBoard[k][j];
                }
            }
//            printBoard();
//            System.out.println("==================================");
        }
        return answer = Math.min(answer, calculateMin() );
    }

    static void copyBoard(int[][] origin, int[][] target){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                target[i][j] = origin[i][j];
            }
        }
    }
    static void printBoard() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                System.out.printf("%d ", board[i][j]);
            }
            System.out.println();
        }
    }
}
