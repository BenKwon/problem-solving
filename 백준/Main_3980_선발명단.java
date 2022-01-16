package 백준;

import java.io.*;
import java.util.*;

public class Main_3980_선발명단 {
    static int[][] graph;
    static int max;
    static ArrayList<Integer>[] playerAbility;
    static void dfs(int level, int bitmask, int sum) {
        if(level == 11){
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < playerAbility[level].size(); i++) {
            int position = playerAbility[level].get(i);
            if((bitmask & (1 << position)) != 0) continue;
            bitmask |= (1 << position);
            int ability = graph[level][position];
            dfs(level + 1, bitmask, sum + ability);
            bitmask ^= (1 << position);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            graph = new int[11][11];
            playerAbility = new ArrayList[11];
            max = Integer.MIN_VALUE;
            for (int i = 0; i < 11; i++) {
                playerAbility[i] = new ArrayList<>();
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                    if(graph[i][j] != 0){
                        playerAbility[i].add(j);
                    }
                }
            }
            for (int i = 0; i < playerAbility[0].size(); i++) {
                int position = playerAbility[0].get(i);
                int bitmask = 1 << position;
                int ability = graph[0][position];
                dfs(1, bitmask, ability);
            }

            System.out.println(max);
        }

    }
}
