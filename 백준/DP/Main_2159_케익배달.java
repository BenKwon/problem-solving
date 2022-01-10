package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class pos {
    int row;
    int col;

    public pos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return "pos{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}

public class Main_2159_케익배달 {
    static long[][] dp;
    static int n;
    static ArrayList<pos> customers = new ArrayList<>();
    static int[] dx = {0,-1, 1, 0, 0};
    static int[] dy = {0, 0, 0, -1, 1};

    public static boolean validation(pos pos) {
        if (pos.row < 0 || pos.row > 100000 || pos.col < 0 || pos.col > 100000) return false;
        return true;
    }

    public static pos movePos(pos pos, int operation) {
        int nrow = pos.row + dx[operation];
        int ncol = pos.col + dy[operation];
        return new pos(nrow, ncol);
    }

    public static long posDiff(pos cur, pos next){
        return Math.abs(cur.row - next.row) + Math.abs(cur.col - next.col);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new long[n + 1][5];
        StringTokenizer st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());
        customers.add(new pos(startRow, startCol));
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            customers.add(new pos(row, col));
        }
        for (int i = customers.size() - 2; i >= 0; i--) {
            pos cur = customers.get(i);
            pos next = customers.get(i + 1);
            for (int j = 0; j < 5; j++) {
                pos nCurPos = movePos(cur,j);
                if(!(validation(nCurPos)))continue;
//                System.out.println(nCurPos.toString());
                long min = Long.MAX_VALUE;
                for (int k = 0; k < 5; k++) {
                    pos nNextPos = movePos(next, k);
                    if(!(validation(nNextPos))) continue;
                    if(dp[i+1][k] + posDiff(nCurPos,nNextPos)  < min){
                        min = dp[i+1][k] + posDiff(nCurPos,nNextPos);
                    }
                }
                dp[i][j] = min;
//                System.out.printf("dp[%d][%d] : %d \n",i,j,dp[i][j]);
            }
        }
        System.out.println(dp[0][0]);


    }

}
