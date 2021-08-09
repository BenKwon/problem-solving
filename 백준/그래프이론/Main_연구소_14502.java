package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_연구소_14502 {
    public static int[][] graph;
    public static ArrayList<int[]> candidate = new ArrayList<>();
    public static ArrayList<int[]> build_wall = new ArrayList<>();
    public static ArrayList<int[]> virus_init = new ArrayList<>();
    public static int[] row_move = new int[]{-1, 1, 0, 0};
    public static int[] col_move = new int[]{0, 0, -1, 1};
    static int n,m;
    public static int bfs(int[][] graph) {
        Queue<int[]> q = new LinkedList<>();
        int[][] tmp_graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tmp_graph[i][j] = graph[i][j];
            }
        }
        for (int i = 0; i < virus_init.size(); i++) {
            q.offer(virus_init.get(i));
        }
        int[][] visit = new int[n][m];
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            visit[poll[0]][poll[1]] = 1;
            for(int i = 0 ; i < 4;  i++) {
                int nrow = poll[0] + row_move[i];
                int ncol = poll[1] + col_move[i];
//                System.out.printf("row : %d , col : %d \n", poll[0],poll[1]);
//                System.out.printf("nrow :%d , ncol : %d\n",nrow,ncol);
                if(nrow < 0 || nrow >= n|| ncol < 0 || ncol >= m) continue;
                if(tmp_graph[nrow][ncol] == 1) continue;
                if(visit[nrow][ncol] == 1) continue;
                if (tmp_graph[nrow][ncol] == 0) {
                    q.offer(new int[]{nrow, ncol});
                    tmp_graph[nrow][ncol] = 2;
                }
            }
        }
        int answer  = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(tmp_graph[i][j] ==0) answer++;
            }
        }
//        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 0) {
//                    System.out.printf(" i : %d, j : %d \n", i ,j);
                    candidate.add(new int[]{i, j});
                }
                if (graph[i][j] == 2) {
                    virus_init.add(new int[]{i, j});
                }
            }

        }
//        System.out.println("candidate = " + candidate.size());
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < candidate.size() - 2 ; i++) {
            graph[candidate.get(i)[0]][candidate.get(i)[1]] = 1;
            for (int j = i + 1; j < candidate.size() - 1; j++) {
                graph[candidate.get(j)[0]][candidate.get(j)[1]] = 1;
                for (int k = j + 1; k < candidate.size(); k++) {
                    graph[candidate.get(k)[0]][candidate.get(k)[1]] = 1;
//                    System.out.println("--------------------------------------------------------");
//                    System.out.printf("[%d , %d] , [%d, %d] , [%d, %d]\n",candidate.get(i)[0],candidate.get(i)[1],candidate.get(j)[0],candidate.get(j)[1],candidate.get(k)[0],candidate.get(k)[1]);
//                    for (int t = 0; t < n; t++) {
//                        for (int o = 0; o < m; o++) {
//                            System.out.printf("%d ", graph[t][o]);
//                        }
//                        System.out.println();
//                    }
                    int bfs = bfs(graph);
//                    System.out.println("bfs = " + bfs);
                    max = Math.max(max, bfs);
                    graph[candidate.get(k)[0]][candidate.get(k)[1]] = 0;
                }
                graph[candidate.get(j)[0]][candidate.get(j)[1]] = 0;

            }
            graph[candidate.get(i)[0]][candidate.get(i)[1]] = 0;

        }

        System.out.println(max);

    }
}
