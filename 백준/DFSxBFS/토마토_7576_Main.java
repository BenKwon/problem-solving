package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * &
 */
public class 토마토_7576_Main {
    public static int m,n;
    public static int[][] visited;
    public static int[][] graph;
    public static Queue<Node> q = new LinkedList<>();
    public static int[] x_move = new int[]{-1, 1, 0, 0};
    public static int[] y_move = new int[]{0, 0, -1, 1};
    static class Node{
        int x;int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public static int bfs(){
        int result = 0;
        while (!q.isEmpty()){
            Node node = q.poll();
            int x = node.getX();
            int y = node.getY();
//            System.out.printf("graph[%d][%d] : %d\n", x,y, graph[x][y]);
            for(int i = 0 ; i < 4 ; i++){
                int nx = node.getX() + x_move[i];
                int ny = node.getY() + y_move[i];
//                System.out.printf("nx : %d , ny : %d\n",nx,ny);
                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny]==0 && graph[nx][ny] == 0){
                    visited[nx][ny] = 1;
                    graph[nx][ny] = graph[x][y] + 1;
                    result = graph[nx][ny];
//                    System.out.println("result : " + result);
                    q.offer(new Node(nx, ny));
                }
            }
        }
        for(int x = 0; x < n ; x++){
            boolean break_flag = false;
            for(int y =0 ; y< m;y++){
                if(visited[x][y] == 0 && graph[x][y] != -1){
                    return -1;
                }
            }
        }
        if(result == 0) return 0;
        return result - 1;

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new int[n][m];
        for(int x = 0 ; x < n ;x++){
            str = br.readLine();
            st = new StringTokenizer(str);
            for(int y = 0 ; y < m ; y++){
                int tmp = Integer.parseInt(st.nextToken());
                graph[x][y] = tmp;
                if(tmp == 1){
                    Node node = new Node(x, y);
                    q.offer(node);
                    visited[x][y] = 1;
                }
            }
        }

        System.out.println(bfs());

    }
}

