package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_가장큰정사각형_1915 {
    public static int n, m;
    public static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                int elem = Integer.parseInt(String.valueOf(str.charAt(j)));
                graph[i][j] = elem;
            }
        }
        int k = Math.min(n, m);
        int tmpn = n;
        int tmpm = m;
        n-=2;
        m-=2;
        int max = 0;
        while(n >= 0 && m>= 00){
            if(graph[n][m] != 0){
                int min = Math.min(graph[n+1][m], Math.min(graph[n][m+1],graph[n+1][m+1]));
                graph[n][m] = min + 1;
                max = Math.max(max, min + 1);
            }
            for(int col = m - 1; col >=0 ;col--){
                if(graph[n][col] == 0) continue;
                int min = Math.min(graph[n+1][col], Math.min(graph[n][col+1],graph[n+1][col+1]));
                graph[n][col] = min+ 1;
                max = Math.max(max, min + 1);
            }
            for(int row = n-1 ; row >=0 ;row--){
                if(graph[row][m] == 0) continue;
                int min = Math.min(graph[row+1][m], Math.min(graph[row][m+1],graph[row+1][m+1]));
                graph[row][m] = min + 1;
                max = Math.max(max, min + 1);

            }
            n--;m--;
        }

        if(max == 0){
            for(int i = 0;i< tmpn ; i++){
                for (int j = 0; j < tmpm; j++) {
                    max = Math.max(max, graph[i][j]);
                }
            }
        }

        System.out.println(max*max);
    }



}
