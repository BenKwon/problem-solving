package 백준.DFSxBFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 유기농배추_1012_Main {
    public static int[][] graph;
    public static int[][] visit;
    public static int[] x_move = new int[]{-1, 1, 0, 0};
    public static int[] y_move = new int[]{0, 0, 1, -1};
    public static Queue<int[]> q;
    public static boolean bfs(int x, int y,int n , int m){
        if(visit[y][x] == 1) return false;
        q = new LinkedList<>();
        q.offer(new int[]{x,y});
        while(!q.isEmpty()){
            int[] poll = q.poll();
            int poll_x = poll[0];
            int poll_y = poll[1];

            for (int i = 0; i < 4; i++) {
                int nx = poll_x + x_move[i];
                int ny = poll_y + y_move[i];
                if(nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                if(visit[ny][nx] == 1) continue;
                if(graph[ny][nx] == 1){
                    visit[ny][nx] = 1;
                    q.offer(new int[]{nx, ny});
                }
            }
        }
        return true;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        ArrayList<int[]> fruit_pos = new ArrayList<>();
        for(int t = 0 ; t < test; t++){
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
//            System.out.println("m :" + m + " n : " + n);
            graph = new int[n][m];
            visit = new int[n][m];
            fruit_pos = new ArrayList<>();
            for(int i = 0 ; i < k ; i++){
                str = br.readLine();
                st = new StringTokenizer(str);
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[y][x] = 1;
                fruit_pos.add(new int[]{x, y});
            }
            int sol = 0;
            for(int j = 0; j < fruit_pos.size(); j++){
                int x = fruit_pos.get(j)[0];
                int y = fruit_pos.get(j)[1];
                if(bfs(x, y,n,m))sol++;
            }
            System.out.println(sol);
        }

    }
}
