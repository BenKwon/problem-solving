package 백준.트리;

import java.util.*;
import java.io.*;

public class Main_16437_양구출작전 {
    static int n;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i, 0);
        }
        //루트 노드 제거
        for (int i = 2; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char type = st.nextToken().charAt(0);
            long value = 1;
            if (type == 'W') {
                value = -1;
            }
            value *= Integer.parseInt(st.nextToken());
            int parent = Integer.parseInt(st.nextToken());
            nodes[i].value = value;
            nodes[i].parent = parent;
            nodes[parent].children.add(i);
        }
        System.out.println(solution(1));
    }

    static long solution(int id) {
        long answer = 0;
        Node node = nodes[id];
        ArrayList<Integer> children = node.children;
        for (int child : children) {
            answer += solution(child);
        }
        answer += node.value;

        if (answer <= 0) return 0;
        return answer;
    }

    static class Node {
        int id;
        long value;
        int parent;
        ArrayList<Integer> children;

        public Node(int id, long value) {
            this.id = id;
            this.value = value;
            children = new ArrayList<>();
        }
    }
}
