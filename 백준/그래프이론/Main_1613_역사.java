package 백준.그래프이론;

import java.time.temporal.IsoFields;
import java.util.*;
import java.io.*;

public class Main_1613_역사 {
    static int n, k, s;
    static int[][] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        graph = new int[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());
            graph[front][back] = 1;
        }
        for (int t = 1; t <= n; t++) {
            for (int i = 1; i <= n; i++) {
                if(graph[i][t] == 0) continue;
                for (int j = 1; j <= n; j++) {
                    if(graph[t][j] == 1){
                        graph[i][j] = 1;
                    }
                }
            }
        }
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                System.out.printf("%d ",graph[i][j]);
//            }
//            System.out.println();
//        }
//        System.out.println("--------------------------------");
        s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());
            if(graph[front][back] != 0){
                bw.write("-1"+"\n");
            }else if(graph[back][front] != 0){
                bw.write("1"+"\n");
            }else{
                bw.write("0"+"\n");
            }
        }
        bw.flush();
    }
}
