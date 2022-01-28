package 백준;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19236_청소년상어 {
    static int[][] board = new int[4][4];
    static int[][] dirBoard = new int[4][4];
    static int[] dx = {0,-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0,0, -1, -1, -1, 0, 1, 1, 1};
    static int maxFishSum = Integer.MIN_VALUE;

    static class pos {
        int fishNum; //상어는 -1번이라고 하자
        int dir;

        public pos(int fishNum, int dir) {
            this.fishNum = fishNum;
            this.dir = dir;
        }
    }

    static void copyBoard(int[][] source, int[][] target) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                target[i][j] = source[i][j];
            }
        }
    }

    static boolean inRange(int row, int col) {
        if (row < 0 || row >= 4 || col < 0 || col >= 4) return false;
        return true;
    }

    static void fishMove(int[][] board, int[][] dirBoard) {
        for (int n = 1; n <= 16; n++) {
//            System.out.println("-------------------------");
//            System.out.println("n = " + n);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
//                    System.out.printf("board[%d][%d] : %d\n",i,j,board[i][j]);
                    if (board[i][j] != n) continue;
                    int initDir = dirBoard[i][j];
                    boolean canMove = true;
                    while (true) {
                        int nRow = i + dx[dirBoard[i][j]];
                        int nCol = j + dy[dirBoard[i][j]];
                        if (inRange(nRow, nCol) && board[nRow][nCol] != -1) {
                            break;
                        }
                        dirBoard[i][j] = rotateDir(dirBoard[i][j]);
                        if (dirBoard[i][j] == initDir) {
                            canMove = false;
                            break;
                        }
                    }
                    //물고기 이동 불가능 하면 끝
                    if (!canMove){
                        i = 5;
                        break;
                    }
                    //물고기 자리 교체
                    int tmpDir = dirBoard[i][j];
//                    System.out.println("tmpDir = " + tmpDir);
                    int nRow = i + dx[dirBoard[i][j]];
                    int nCol = j + dy[dirBoard[i][j]];
                    board[i][j] = board[nRow][nCol];
                    dirBoard[i][j] = dirBoard[nRow][nCol];
                    board[nRow][nCol] = n;
                    dirBoard[nRow][nCol] = tmpDir;
                    i = 5;
                    break;
                }
            }
//            for (int i = 0; i < 4; i++) {
//                for (int j = 0; j < 4; j++) {
//                    System.out.printf("%d ", board[i][j]);
//                }
//                System.out.println();
//            }
        }
    }

    static int rotateDir(int dir) {
        int next = (dir + 1) % 9;
        if(next == 0) return 1;
        return next;
    }

    static void dfs(int row, int col, int accum) {
//        System.out.println("------------------------");
        boolean lastLevel = true;
        int jump = 0;
        int dir = dirBoard[row][col]; // 상어를 먹고 들어옴
        int[][] tmpBoard = new int[4][4];
        int[][] tmpDirBoard = new int[4][4];
        copyBoard(board, tmpBoard);
        copyBoard(dirBoard, tmpDirBoard);
//        System.out.printf("row : %d ,col : %d, dir :%d\n",row ,col,dir);
        while (true) {
            jump++;
            int nRow = row + (dx[dir] * jump);
            int nCol = col + (dy[dir] * jump);
            //범위 초과시 끝
            if (!inRange(nRow, nCol)) break;
            //이동 위치에 물고기 없으면 패스
            if (board[nRow][nCol] == 0) continue;
            lastLevel = false;
            //상어 이동 및 식사
            int eatFish = board[nRow][nCol];
            board[nRow][nCol] = -1;
            board[row][col] = 0;
            dirBoard[row][col] = 0;
            //물고기 이동
            fishMove(board,dirBoard);
            dfs(nRow, nCol, accum + eatFish);
            copyBoard(tmpBoard, board);
            copyBoard(tmpDirBoard, dirBoard);
        }
        if (lastLevel) {
            maxFishSum = Math.max(maxFishSum, accum);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int fishNum = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken());
                board[i][j] = fishNum;
                dirBoard[i][j] = dir;
            }
        }



        int initTargetFish = board[0][0];
        board[0][0] = -1;
        fishMove(board,dirBoard);
        dfs(0, 0, initTargetFish);
        System.out.println(maxFishSum);
    }
}
