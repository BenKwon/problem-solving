package 백준.DFSxBFS;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * input
 * 5 6
 * 101010
 * 111111
 * 000001
 * 111111
 * 111111
 * <p>
 * output
 * 10
 */
public class 미로탈출＿이코테 {
    public static int[][] miro;
    public final static int[] dx = new int[]{-1,1,0,0};
    public final static int[] dy = new int[]{0,0,-1,1};
    public static int n;
    public static int m;

    public static int bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));

        while(!queue.isEmpty()){
            Node poll = queue.poll();
            x = poll.getX();
            y = poll.getY();
//            System.out.println("=================");
//            System.out.println("x = " + x);
//            System.out.println("y = " + y);
//            System.out.println("queue = " + queue.size());
            for(int i = 0 ; i< 4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny <0 || ny >= m) continue;
                if(miro[nx][ny] == 0 ) continue;
//                System.out.println("miro = " + miro[nx][ny]);

                if(miro[nx][ny] == 1){
                    miro[nx][ny] = miro[x][y] + 1;
//                    System.out.printf("nx : %d  ny : %d\n",nx,ny);
                    Node node = new Node(nx,ny);
                    node.x = nx;
                    node.y = ny;
                    queue.offer(node);
                }
            }
        }
        return miro[n-1][m-1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        miro = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = sc.next();
            for (int j = 0; j < m; j++) {
                miro[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
            }
        }

        System.out.println(bfs(0,0));

    }

    static class Node {
        int x;
        int y;


        public Node(int x, int y) {
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }

}
