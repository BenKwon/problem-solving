package 백준.트리;

import java.io.*;
import java.util.*;

public class Main_1068_트리 {
    static int n, root, targetRemoved;
    static ArrayList<ArrayList<Integer>> connect_info = new ArrayList<>();
    static int[] visit;
    static int numOfLeaves = 0;

    public static void dfs(int node) {
        ArrayList<Integer> connect = connect_info.get(node);
        if (node == targetRemoved) return;
        boolean leaf = true;
        for (int i = 0; i < connect.size(); i++) {
            int next = connect.get(i);
            if (next == targetRemoved) continue;
            leaf = false;
            dfs(next);
        }
        if (leaf) numOfLeaves++;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        root = 0;
        for (int i = 0; i < n; i++) {
            connect_info.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(st.nextToken());
            if (next == -1) {
                root = i;
                continue;
            }
            connect_info.get(next).add(i);
        }
        targetRemoved = Integer.parseInt(br.readLine());

        dfs(root);
        System.out.println(numOfLeaves);

    }
}
