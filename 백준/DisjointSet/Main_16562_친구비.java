package 백준.DisjointSet;
import java.util.*;
import java.io.*;
public class Main_16562_친구비 {
    static int n,m, k;
    static int[] price;
    static int[] group;
    static int[] groupRecord;
    static int getParent(int node){
        if(group[node] == node) return node;
        return group[node] = getParent(group[node]);
    }

    static void union(int node1, int node2) {
        node1 = getParent(node1);
        node2 = getParent(node2);
        if (node1 < node2) {
            group[node2] = node1;
        }else{
            group[node1] = node2;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        price = new int[n + 1];
        group = new int[n + 1];
        groupRecord = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        Arrays.fill(groupRecord, Integer.MAX_VALUE);
        for (int i = 1; i <= n; i++) {
            price[i] = Integer.parseInt(st.nextToken());
            group[i] = i;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int friend1 = Integer.parseInt(st.nextToken());
            int friend2 = Integer.parseInt(st.nextToken());
            union(friend1,friend2);
        }

        for (int i = 1; i <= n; i++) {
            int costForBeingFR = price[i];
            int parent = getParent(i);
            if (costForBeingFR < groupRecord[parent]) {
                groupRecord[parent] = costForBeingFR;
            }
        }

        int solution = 0;
        for (int i = 1; i <= n; i++) {
            int need = groupRecord[i];
            if(need == Integer.MAX_VALUE) continue;
            k = k - need;
            solution += need;
        }
        if(k < 0){
            System.out.println("Oh no");
        }else{
            System.out.println(solution);
        }
    }
}
