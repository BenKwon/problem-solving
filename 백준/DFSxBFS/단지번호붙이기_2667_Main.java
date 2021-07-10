package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class 단지번호붙이기_2667_Main {
    public static int[][] visited;
    public static int n;
    public static int[][] graph;
    public static int count = 0;
    public static ArrayList<Integer> solution = new ArrayList<>();
    public static int dfs(int x, int y){
        if(x < 0 || x >= n || y < 0 || y>=n){
            return 0;
        }
        if(graph[x][y] == 1 && visited[x][y] == 0){
//            System.out.printf("x : %d y: %d\n",x,y);
            visited[x][y] = 1;
            count++;
            dfs(x - 1, y);
            dfs(x + 1, y);
            dfs(x, y - 1);
            dfs(x, y + 1);
            return 1;
        }
        return 0;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        graph = new int[n][n];
        visited = new int[n][n];
        for(int i = 0 ; i  < n ;i++){
            String line = br.readLine();
            for(int j = 0 ; j < n ;j++){
                graph[i][j] = Integer.parseInt(String.valueOf(line.charAt(j)));
            }
        }
        int num_sol = 0;
        for(int x = 0 ; x < n ; x++){
            for (int y = 0; y < n; y++) {
//                System.out.println("================");
                count = 0;
                if(dfs(x,y) == 1){
                    num_sol++;
                    solution.add(count);
//                    System.out.printf("count : %d\n",count);
                }
            }
        }
        Collections.sort(solution);
        System.out.println(num_sol);
        for(int i = 0 ; i < solution.size(); i++){
            System.out.println(solution.get(i));
        }
    }

    static class Node{
        int x;
        int y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}
