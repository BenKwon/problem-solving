package programmers;

import java.util.*;
import java.util.*;

public class 양과늑대 {
    public static void main(String[] args) {
        int[] info = {0, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 1};
        int[][] edges = {{0, 1}, {1, 2}, {1, 4}, {0, 8}, {8, 7}, {9, 10}, {9, 11}, {4, 3}, {6, 5}, {4, 6}, {8, 9}};
        solution(info, edges);
    }

    static class Node {
        int id;
        int type; //wolf : 1, lmb: 0
        Node left;
        Node right;
        Node parent;

        public Node(int id, int type) {
            this.id = id;
            this.type = type;
        }
    }

    static class Temp {
        int id;
        int mask;
        int sheep;
        int wolf;

        public Temp(int id, int mask, int sheep, int wolf) {
            this.id = id;
            this.mask = mask;
            this.sheep = sheep;
            this.wolf = wolf;
        }
    }

    static int n;
    static Node[] nodes;
    static int[][] visit;

    public static int solution(int[] info, int[][] edges) {
        int answer = 0;
        n = info.length;
        visit = new int[n][1 << n];
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i, info[i]);
        }
        for (int i = 0; i < edges.length; i++) {
            int parentId = edges[i][0];
            int childId = edges[i][1];
            Node parent = nodes[parentId];
            Node child = nodes[childId];
            if (parent.left == null) {
                parent.left = child;
                child.parent = parent;
            } else {
                parent.right = child;
                child.parent = parent;
            }
        }
        return answer = start();
    }

    static int start() {
        Queue<Temp> q = new LinkedList<>();
        q.add(new Temp(0, 1, 1, 0));
        visit[0][1] = 1;
        int max = 0;
        while (!q.isEmpty()) {
            Temp poll = q.poll();
            int id = poll.id;
            int mask = poll.mask;
            int sheep = poll.sheep;
            int wolf = poll.wolf;
            if (sheep <= wolf) continue;
            max = Math.max(max, poll.sheep);
//            System.out.println("id = " + id);
            for (int i = 0; i < n; i++) {
                int checkMask = (1 << i);
                if ((mask & checkMask) == 0) {
                    Node parent = nodes[i].parent;
                    int parentMask = (1 << parent.id);
                    //갈수 있는 노드 발견
                    if ((mask & parentMask) != 0) {
                        Node next = nodes[i];
                        int newMask = mask | (1 << i);
                        if (next.type == 0 && sheep + 1 > wolf) {
                            visit[i][mask] = 1;
                            q.add(new Temp(i, newMask, sheep + 1, wolf));
                        } else if(next.type ==1  && sheep > wolf + 1) {
                            visit[i][mask] = 1;
                            q.add(new Temp(i, newMask, sheep, wolf + 1));
                        }
                    }
                }
            }
        }
        System.out.println("max = " + max);
        return max;
    }


}
