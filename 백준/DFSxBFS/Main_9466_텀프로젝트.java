package 백준.DFSxBFS;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_9466_텀프로젝트 {
    public static int n;
    public static int[] team_graph, team_leader, team_info;
    static ArrayList<Integer> leader;
    public static void dfs(int start, int cur, int tmp) {
        if(tmp == 1 && start == cur){
            team_leader[start] = 1;
            leader.add(start);
        }
        int next = team_graph[cur];
        if(team_info[next] != 0){
            if(team_info[next] == start){
                team_leader[next] = 1;
                leader.add(next);
            }
            return;
        }else{
            team_info[next] = start;
            dfs(start, next, 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            n = Integer.parseInt(br.readLine());
            team_graph = new int[n + 1];
            team_leader = new int[n + 1];
            team_info = new int[n + 1];
            leader = new ArrayList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                team_graph[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 1; i <= n; i++) {
                int group = team_info[i];
                if(team_info[i] == 0){
                    team_info[i] = i;
                    dfs(i, i, 0);
                }
            }

            int sol = n;
            for (Integer integer : leader) {
//                System.out.println("integer = " + integer);
                int tmp = integer;
                while(true){
                    sol--;
                    int next = team_graph[tmp];
                    if(next == integer) break;
                    tmp = next;
                }
            }
//            int[] check_join_team = new int[n + 1];
            bw.write(sol + "\n");
//            System.out.printf("%d\n",sol);
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
