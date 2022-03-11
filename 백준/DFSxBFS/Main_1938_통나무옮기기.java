package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1938_통나무옮기기 {
    static int n;
    static char[][] board;
    static HashMap<Integer, int[]> posMap = new HashMap<>();
    static ArrayList<Integer> start = new ArrayList<>();
    static int startType = 0;
    static int endType = 0;
    static ArrayList<Integer> end = new ArrayList<>();
    static int endRow;
    static int endCol;
    static int INF = Integer.MAX_VALUE;
    static int solution = INF;
    static class Node {
        int[] middle; //[?,중앙좌표,?]
        int type; //가로배치 or 세로 배치
        int count;

        public Node(int[] middle, int type, int count) {
            this.middle = middle;
            this.type = type;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new char[n][n];
        int id = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'B') {
                    start.add(id);
                } else if (board[i][j] == 'E') {
                    end.add(id);
                }
                posMap.put(id++, new int[]{i, j});
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'B') {
                    if (j+1 < n && board[i][j + 1] == 'B') startType = 1;
                } else if (board[i][j] == 'E') {
                    if (j+1 < n && board[i][j + 1] == 'E') endType = 1;
                }
                posMap.put(id++, new int[]{i, j});
            }
        }
        int[] endPos = posMap.get(end.get(1));
        endRow = endPos[0];
        endCol = endPos[1];
//        System.out.printf("end[%d,%d] , type:%d\n",endRow,endCol,endType);
        solution();
    }

    static void solution() {
        Queue<Node> q = new LinkedList<>();
        int[] startpos = posMap.get(start.get(1));
        q.add(new Node(startpos, startType, 0));
        int[][][] visit = new int[n][n][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visit[i][j], INF);
            }
        }
        visit[startpos[0]][startpos[1]][startType] = 0;
        while (!q.isEmpty()) {
            Node poll = q.poll();
            int[] middle = poll.middle;
            int row = middle[0];
            int col = middle[1];
            int count = poll.count;
            int type = poll.type;
            //solution check
            if(type == endType && row==endRow && col == endCol){
                solution = Math.min(count, solution);
                break;
            }
//            System.out.println("+++++++++++++++++++++++++++++++++++++");
//            System.out.printf("[%d,%d], type:%d\n",row,col,type);
            for(int i = 0 ; i < 5; i++){
//                System.out.println("--------------------------");
                //0,1,2,3,4 상하좌우회전
                boolean result;
                if(i == 0) result = upCheck(middle,type);
                else if(i == 1) result = downCheck(middle, type);
                else if(i == 2) result = leftCheck(middle, type);
                else if(i == 3) result = rightCheck(middle, type);
                else result = rotateCheck(middle,type);
                if(!result) continue;
//                System.out.println("i:"+i+", result:" +result);
                int[] nextMid = {row,col};
                int nextType = type;
                if(i == 0) nextMid = new int[]{row-1,col};
                else if(i == 1) nextMid = new int[]{row+1,col};
                else if(i == 2) nextMid = new int[]{row,col-1};
                else if(i == 3) nextMid = new int[]{row,col +1};
                else nextType = nextType == 1 ? 0 : 1;
                int nrow = nextMid[0];
                int ncol = nextMid[1];
//                System.out.println("visit[nrow][ncol][nextType]  = " + visit[nrow][ncol][nextType] );
//                System.out.printf("n:[%d,%d], type:%d\n",nrow,ncol,nextType);
                if(visit[nrow][ncol][nextType] <= count + 1) continue;
//                System.out.println("take!!");
                visit[nrow][ncol][nextType] = count + 1;
                q.add(new Node(nextMid, nextType, count + 1));
            }
        }
        if(solution == INF) System.out.println(0);
        else System.out.println(solution);
    }

    static boolean upCheck(int[] middle, int type) {
        int row = middle[0];
        int col = middle[1];
        try {
            if (board[row - 1][col] == '1') return false;
            if (type == 0) {
                if (board[row - 2][col] == '1') return false;
            } else {
                if (board[row - 1][col - 1] == '1' || board[row - 1][col + 1] == '1') return false;
            }
        } catch (Exception e) { //범위 벗어나면 에러 발생
            return false;
        }
        return true;
    }

    static boolean downCheck(int[] middle, int type) {
        int row = middle[0];
        int col = middle[1];
        try {
            if (board[row + 1][col] == '1') return false;
            if (type == 0) {
                if (board[row + 2][col] == '1') return false;
            } else {
                if (board[row + 1][col - 1] == '1' || board[row + 1][col + 1] == '1') return false;
            }
        } catch (Exception e) { //범위 벗어나면 에러 발생
            return false;
        }
        return true;
    }

    static boolean leftCheck(int[] middle, int type) {
        int row = middle[0];
        int col = middle[1];
        try {
            if (board[row][col - 1] == '1') return false;
            if (type == 0) {
                if (board[row-1][col-1] =='1'|| board[row+1][col-1]=='1') return false;
            } else {
                if (board[row][col-2] == '1') return false;
            }
        } catch (Exception e) { //범위 벗어나면 에러 발생
            return false;
        }
        return true;
    }

    static boolean rightCheck(int[] middle, int type) {
        int row = middle[0];
        int col = middle[1];
        try {
            if (board[row][col + 1] == '1') return false;
            if (type == 0) {
                if (board[row-1][col + 1] =='1'|| board[row+1][col + 1]=='1') return false;
            } else {
                if (board[row][col + 2] == '1') return false;
            }
        } catch (Exception e) { //범위 벗어나면 에러 발생
            return false;
        }
        return true;
    }

    static boolean rotateCheck(int[] middle, int type){
        int row = middle[0];
        int col = middle[1];
        try {
            if (type == 0) {
                if(board[row-1][col-1] =='1' || board[row][col-1] =='1'
                    || board[row+1][col-1]=='1' || board[row-1][col+1]=='1'
                    || board[row][col+1] =='1' || board[row+1][col + 1] =='1') return false;
            } else {
                if(board[row-1][col-1]=='1' || board[row-1][col]=='1'
                    || board[row-1][col+1]=='1' || board[row+1][col-1] =='1'
                    || board[row+1][col] =='1' || board[row+1][col+1]=='1')return false;
            }
        } catch (Exception e) { //범위 벗어나면 에러 발생
            return false;
        }
        return true;
    }

}
