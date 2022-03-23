package programmers;

import java.util.*;
public class 경주로건설 {
    static int[] dx = {-1,  1, 0 ,0};
    static int[] dy = {0 ,0 , -1, 1};
    static int n;
    static int[][][] visit;
    static boolean inRange(int row, int col){
        if(row < 0 || row >= n || col < 0|| col>= n) return false;
        return true;
    }
    static public int solution(int[][] board) {
        int answer = Integer.MAX_VALUE;
        n = board.length;
        visit = new int[n][n][2];
        Queue<Node> q = new LinkedList<>();
        for(int i = 0 ; i < n ;i++){
            for(int  j= 0 ; j < n ;j++){
                Arrays.fill(visit[i][j], Integer.MAX_VALUE);
            }

        }
        visit[0][0][0] = 0;
        visit[0][0][1] = 1;
        if(board[0][1] == 0){
            q.add(new Node(0,1,100,3));
            visit[0][1][1] = 100;
        }
        if(board[1][0] == 0){
            q.add(new Node(1,0,100,1));
            visit[1][0][0] = 100;
        }

        while(!q.isEmpty()){
            Node poll = q.poll();
            int row = poll.row;
            int col = poll.col;
            int cost = poll.cost;
            int from = poll.from;
            if(row ==n -1&& col ==n-1){
                answer = Math.min(answer, cost);
                continue;
            }

            for(int i = 0 ; i < 4; i++){
                int nrow = row + dx[i];
                int ncol = col + dy[i];
                if(!inRange(nrow,ncol) || board[nrow][ncol] == 1)continue;
                int dir = -1;
                int addCost = 100;
                if(from == 0 || from == 1){
                    if(i == 0 || i == 1){
                        dir = 0;
                    }else{
                        dir = 1;
                        addCost +=500;
                    }
                }else{
                    if(i == 2 || i == 3){
                        dir = 1;
                    }else{
                        dir = 0;
                        addCost += 500;
                    }
                }
                if(cost + addCost < visit[nrow][ncol][dir]){
                    visit[nrow][ncol][dir] = cost +addCost;
                    q.add(new Node(nrow,ncol,cost + addCost,i));
                }
            }
        }

        return answer ;
    }

    static class Node{
        int row;
        int col;
        int cost;
        int from;
        public Node(int row , int col, int cost, int from){
            this.row =row;
            this.col = col;
            this.cost = cost;
            this.from = from;
        }
    }

}
