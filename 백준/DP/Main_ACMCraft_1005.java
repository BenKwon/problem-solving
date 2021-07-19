package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_ACMCraft_1005 {
    public static int n, k;
    public static int[] build_weight;
    public static HashMap<Integer, Integer> parent_nodes;
    public static ArrayList<ArrayList<Integer>> relation;
    public static Queue<Integer> q;
    public static int[] dp;
    public static int[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test_num = stoi(br.readLine());
        for (int i = 0; i < test_num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = stoi(st.nextToken()); //빌딩 수
//            System.out.println("i = " + i);
//            System.out.println("n = " + n);
            k = stoi(st.nextToken()); //규칙 수
            build_weight = new int[n + 1]; //각 빌딩의 건설 시간 비용
            parent_nodes = new HashMap<>(); // 최상위 부모 노드 모음
            relation = new ArrayList<>();
            relation.add(new ArrayList<Integer>());
            st = new StringTokenizer(br.readLine());
            for (int b = 1; b <= n; b++) {
                relation.add(new ArrayList<Integer>());
                parent_nodes.put(b, 1);
                build_weight[b] = stoi(st.nextToken());
            }

            for (int c = 0; c < k; c++) {
                st = new StringTokenizer(br.readLine());
                int start_node = stoi(st.nextToken());
                int next_node = stoi(st.nextToken());
                relation.get(start_node).add(next_node);
                if (parent_nodes.get(next_node) != null) {
                    parent_nodes.remove(next_node);
                }
            }
            int w = stoi(br.readLine());
            q = new LinkedList<>();
            dp = new int[n + 1];
            visit = new int[n + 1];
            for (int parent : parent_nodes.keySet()) {
//                System.out.println("parent = " + parent);
                q.offer(parent);
                visit[parent] = 1;
                dp[parent] = build_weight[parent];
            }

            bfs();
//            for (int i1 : dp) {
//                System.out.println("i1 = " + i1);
//
//            }
            System.out.println(dp[w]);
        }

    }

    private static void bfs() {
        while (!q.isEmpty()) {
            int poll_node = q.poll();
            ArrayList<Integer> children = relation.get(poll_node);
            for (int i = 0; i < children.size(); i++) {
                int child = children.get(i);
                int value = dp[poll_node] + build_weight[child];
                if (visit[child] != 0) {
                    if (dp[child] < value) {
                        dp[child] = value;
                        q.offer(child);
                    }
                } else {
                    visit[child] = 1;
                    dp[child] = value;
                    q.offer(child);
                }
            }
        }
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
