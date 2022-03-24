package 백준.트리;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_15681_트리와쿼리 {
    static int n ,r, q;
    static ArrayList<ArrayList<Integer>> connectInfo = new ArrayList<>();
    static int[] subtrees;
    static int[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());
        subtrees = new int[n + 1];
        visit = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            connectInfo.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            connectInfo.get(u).add(v);
            connectInfo.get(v).add(u);
        }

        dfs(r);

        for (int i = 0; i < q; i++) {
            int u = Integer.parseInt(br.readLine());
            bw.write(subtrees[u] + "\n");
        }
        bw.flush();
    }
    static int dfs(int node){
        ArrayList<Integer> connects = connectInfo.get(node);
        visit[node] = 1;
        int count = 1;
        for (int next : connects) {
            if(visit[next] == 1 ) continue;
            count += dfs(next);
        }
        return subtrees[node] = count;
    }

}
