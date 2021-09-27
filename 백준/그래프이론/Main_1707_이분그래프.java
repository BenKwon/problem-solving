package 백준.그래프이론;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * 내 풀이 : DFS OR BFS, HashSet
 * dfs를 이용하여 연결되어있는 노드들을 그룹화 한다. -> 각 그룹들을 ArrayList형태로 group 에 저장해놓음
 * group에서 각 그룹을 하나씩 가져와서 dfs탐색을 하면서 각 노드들을 탐색하면서 왼쪽 Hashset에 들어가는지 오른쪽 Hashset에 들어가는지 체크한다.(각 노드들의 인접노드을 탐색하면서 set에 있는지 검사하면서)
 * 만약 두 set다 들어갈수없다면 해당 testcase의 정답은 NO
 * 너무 어렵게 풀었다.. 결국 다른 사람풀이와 생각해보면 똑같은 방식이지만 접근법이 어렵다.
 *
 * 다른 사람 풀이 : 큐를 활용한 BFS
 * BFS로 탐색하며 각 노드에 번호 1, 2를 번갈아가면서 달아준다.
 *
 */
public class Main_1707_이분그래프 {
    //    static int[][] graph;
    static ArrayList<Integer>[] graph;
    static int test, v, e;
    static ArrayList<ArrayList<Integer>> group;
    static int[] visit;
    static HashSet<Integer> left;
    static HashSet<Integer> right;
    static boolean sol = true;

    public static void dfs(int start, int group_idx) {
        if (visit[start] == 1) return;
        visit[start] = 1;
        group.get(group_idx).add(start);
        ArrayList<Integer> nexts = graph[start];
        for (int i = 0; i < nexts.size(); i++) {
            dfs(nexts.get(i), group_idx);
        }
    }

    public static void dfs2(int start) {
        if (!sol) return;
        if (visit[start] == 1) return;
        visit[start] = 1;
        ArrayList<Integer> nexts = graph[start];
        boolean leftPut = true;
        boolean rightPut = true;
        for (int i = 0; i < nexts.size(); i++) {
            if (left.contains(nexts.get(i))) {
                leftPut = false;
            }
            if (right.contains(nexts.get(i))) {
                rightPut = false;
            }
        }
        if (leftPut || rightPut) {
            if (leftPut) {
                left.add(start);
            } else {
                right.add(start);
            }
        } else { //그룹화 불가능
            sol = false;
            return;
        }
        for (int i = 0; i < nexts.size(); i++) {
                dfs2(nexts.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int test = Integer.parseInt(br.readLine());

        for (int t = 0; t < test; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            v = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            graph = new ArrayList[v + 1];
            visit = new int[v + 1];
            left = new HashSet<>();
            right = new HashSet<>();
            group = new ArrayList<>();
            for (int i = 1; i <= v; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int i = 0; i < e; i++) {
                st = new StringTokenizer(br.readLine());
                int node1 = Integer.parseInt(st.nextToken());
                int node2 = Integer.parseInt(st.nextToken());
                graph[node1].add(node2);
                graph[node2].add(node1);
            }
            int group_index = 0;
            group.add(group_index, new ArrayList<>());
            for (int i = 1; i <= v; i++) {
                dfs(i, group_index);
                if (group.get(group_index).size() == 0) {
                    continue;
                }
                group_index++;
                group.add(group_index, new ArrayList<>());
            }
            visit = new int[v + 1];
            sol = true;
            for (int g = 0; g < group.size(); g++) {
                if (group.get(g).size() <= 0) continue;
                dfs2(group.get(g).get(0));
            }
            if (sol) {
                bw.write("YES\n");
            } else {
                bw.write("NO\n");
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
