package 백준.그래프이론;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_2250_트리의높이와너비 {
    static int n;
    static ArrayList<int[]> graph = new ArrayList<>();
    static int colSeq = 1;
    static Node[] nodes;

    static class Node {
        int num;
        Node left;
        Node right;
        Node parent;
        int col;

        public Node(int num) {
            this.num = num;
        }

    }

    //왼 루 오
    static void inorder(Node node, int depth) {

        if(node.left != null){
            inorder(node.left, depth + 1);
        }

        node.col = colSeq++;
        int[] minMax = graph.get(depth);
        if(minMax[0] == 0) minMax[0] = node.col;
        if(minMax[1] == 0 )minMax[1] = node.col;
        minMax[0] = Math.min(node.col, minMax[0]);
        minMax[1] = Math.max(node.col, minMax[1]);
        if(node.right != null){
            inorder(node.right, depth + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new int[]{0, 0}); // {최솟값, 최댓값}
            nodes[i] = new Node(i);
            set.add(i);
        }
        set.remove(0);
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            if(set.contains(left)) set.remove(left);
            if(set.contains(right)) set.remove(right);
            if (left > 0)
                nodes[node].left = nodes[left];
            if (right > 0)
                nodes[node].right = nodes[right];
        }
        int root = 1;
        for(int i : set){
            root = i;
        }
        inorder(nodes[root], 1);
        int solDepth = -1;
        int max = -1;
        for (int i = 1; i <= n; i++) {
            int[] arr = graph.get(i);
            int diff = Math.abs(arr[1] - arr[0] + 1);
            if(max < diff){
                max = diff;
                solDepth = i;
            }
        }
        System.out.printf("%d %d", solDepth , max);
    }
}
