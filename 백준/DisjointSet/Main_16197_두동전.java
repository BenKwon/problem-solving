package 백준.DisjointSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16197_두동전 {
    static int[][][][] visit;
    static int n,m;
    static char[][] board;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visit = new int[n + 2][m + 2][n + 2][m + 2];
        board = new char[n + 2][m + 2];
        int coinOneRow = -1;
        int coinOneCol = -1;
        int coinTwoRow = -1;
        int coinTwoCol = -1;
        for (int i = 1; i <= n; i++) {
            String row = br.readLine();
            for (int j = 1; j <= m; j++) {
                char next = row.charAt(j - 1);
                if(next == 'o'){
                    if(coinOneCol < 0){
                        coinOneRow = i;
                        coinOneCol = j;
                    }else{
                        coinTwoRow = i;
                        coinTwoCol = j;
                    }
                }
                board[i][j] = next;
            }
        }
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, coinOneRow, coinOneCol, coinTwoRow, coinTwoCol));
        visit[coinOneRow][coinOneCol][coinTwoRow][coinTwoCol] = 1;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            int oneRow = poll.oneRow;
            int oneCol = poll.oneCol;
            int twoRow = poll.twoRow;
            int twoCol = poll.twoCol;
            if(!inRange(oneRow,oneCol) && !inRange(twoRow,twoCol)) continue;
            else if((!inRange(oneRow,oneCol) && inRange(twoRow,twoCol))||
            (inRange(oneRow,oneCol) && !inRange(twoRow,twoCol))){
                if(poll.count > 10) poll.count = -1;
                System.out.println(poll.count);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int newOneRow = poll.oneRow + dx[i];
                int newOneCol = poll.oneCol + dy[i];
                int newTwoRow = poll.twoRow + dx[i];
                int newTwoCol = poll.twoCol + dy[i];
                if(board[newOneRow][newOneCol] =='#'){
                    newOneRow = oneRow;
                    newOneCol = oneCol;
                }
                if(board[newTwoRow][newTwoCol] == '#'){
                    newTwoRow = twoRow;
                    newTwoCol = twoCol;
                }
                if(visit[newOneRow][newOneCol][newTwoRow][newTwoCol] == 1) continue;
                visit[newOneRow][newOneCol][newTwoRow][newTwoCol] = 1;
                q.add(new Node(poll.count + 1, newOneRow, newOneCol, newTwoRow, newTwoCol));
            }

        }
        System.out.println(-1);
    }

    static boolean inRange(int row, int col) {
        if(row <= 0 || row > n || col <= 0 || col > m) return false;
        return true;
    }
    static class Node{
        int count;
        int oneRow;
        int oneCol;
        int twoRow;
        int twoCol;

        public Node(int count, int oneRow, int oneCol, int twoRow, int twoCol) {
            this.count = count;
            this.oneRow = oneRow;
            this.oneCol = oneCol;
            this.twoRow = twoRow;
            this.twoCol = twoCol;
        }
    }
}
