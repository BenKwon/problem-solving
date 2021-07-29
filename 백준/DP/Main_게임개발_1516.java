package 백준.DP;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 풀면서 간과했던점 : 만약 노드 4번이 1번 2번 3번 모두 지어지고 나서 지어져야 한다면
 * 1 번 2 번 3번 노드 각각까지 걸렸던 weight중에 가장 max인 weight에 노드 4번의 weight를 더해 dp를 구해야한다.
 * 노드 4번은 1번2번3번모두 지어진 상태에서만 지어져야하기 때문에 1번2번3번의 최솟값이 아닌 가장 최댓값에다 더해져야 한다.
 * 즉 1번2번3번노드중에 2번노드가 가장 늦게 지어지면 이 2번노드가 지어지고 나서야 비로소 4번 노드가 지어질수있다.
 */
public class Main_게임개발_1516 {
    public static int n;
    public static Queue<Integer> queue = new LinkedList<>();
    public static ArrayList<ArrayList<Integer>> connect_info = new ArrayList<>();
    public static ArrayList<ArrayList<Integer>> parent_info = new ArrayList<>();

    public static int[] weight;
    public static int[] dp;
    public static int[] visit;
    public static void bfs() {
        while (!queue.isEmpty()) {
            int poll = queue.poll();
            ArrayList<Integer> connect_nodes = connect_info.get(poll);
            visit[poll] = 1;
            for (int i = 0; i < connect_nodes.size(); i++) {
                int next = connect_nodes.get(i);
                if (dp[next] == 0) {
                    ArrayList<Integer> parents = parent_info.get(next);
                    boolean visit_parent = true;
                    int max = Integer.MIN_VALUE;
                    for (int p = 0; p < parents.size(); p++) {
                        if(visit[parents.get(p)] == 0){
                            visit_parent = false;
                            break;
                        }
                        max = Math.max(max, dp[parents.get(p)]);
                    }
                    if(visit_parent){
                        dp[next] = max + weight[next];
                        queue.offer(next);
                    }
                } else {
                    if (dp[next] > dp[poll] + weight[next]) {
                        dp[next] = dp[poll] + weight[next];
                        queue.offer(next);
                    }
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        connect_info.add(new ArrayList<>());
        parent_info.add(new ArrayList<>());
        weight = new int[n + 1];
        dp = new int[n + 1];
        visit = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            connect_info.add(new ArrayList<>());
            parent_info.add(new ArrayList<>());
        }
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());
            if (next == -1) {
                queue.offer(i);
                dp[i] = weight[i];
            } else {
                connect_info.get(next).add(i);
                parent_info.get(i).add(next);
                while(true){
                    next = Integer.parseInt(st.nextToken());
                    if(next == -1) break;
                    connect_info.get(next).add(i);
                    parent_info.get(i).add(next);


                }
            }
        }
        bfs();
        for (int i = 1; i <= n; i++) {
            if (i != n)
                bw.write(dp[i] + "\n");
            else
                bw.write(dp[i] + "");

        }
        bw.flush();
        bw.close();
        br.close();
    }
}
