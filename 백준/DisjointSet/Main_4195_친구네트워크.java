package 백준.DisjointSet;

import java.util.*;
import java.io.*;

public class Main_4195_친구네트워크 {
    static int f;
    static HashMap<String, Integer> map;
    static int[] parent;
    static int[] parentNum;

    static int getParent(int node) {
        if (parent[node] == node) return node;
        return parent[node] = getParent(parent[node]);
    }

    static void union(int node1, int node2) {
        node1 = getParent(node1);
        node2 = getParent(node2);
        if (node1 < node2) {
            parent[node2] = node1;
            parentNum[node1] += parentNum[node2];
        } else {
            parent[node1] = node2;
            parentNum[node2] += parentNum[node1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            f = Integer.parseInt(br.readLine());
            int people = 0;
            parent = new int[200001];
            parentNum = new int[200001];
            map = new HashMap<>();
            for (int i = 0; i < 200001; i++) {
                parent[i] = i;
            }
            Arrays.fill(parentNum, 1);
            for (int i = 0; i < f; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String person1 = st.nextToken();
                String person2 = st.nextToken();
                int intPerson1 = 0;
                int intPerson2 = 0;
                if (!map.containsKey(person1)) {
                    intPerson1 = people;
                    map.put(person1, intPerson1);
                    people++;
                }
                if (!map.containsKey(person2)) {
                    intPerson2 = people;
                    map.put(person2, intPerson2);
                    people++;
                }
                intPerson1 = map.get(person1);
                intPerson2 = map.get(person2);
                int parent1 = getParent(intPerson1);
                int parent2 = getParent(intPerson2);
                if (parent1 != parent2)
                    union(intPerson1, intPerson2);
                bw.write(parentNum[getParent(intPerson1)] +"\n");
            }
        }
        bw.flush();
    }
}
