package 백준.DisjointSet;

import java.io.*;
import java.util.*;

public class Main_10216_CountCircleGroups {
    static class pos {
        int x;
        int y;
        int range;

        public pos(int x, int y, int range) {
            this.x = x;
            this.y = y;
            this.range = range;
        }
    }

    static int getParent(int[] parent, int child) {
        if (parent[child] == child) return child;
        return parent[child] = getParent(parent, parent[child]);
    }

    static void union(int[] parent, int childA, int childB) {
        childA = getParent(parent, childA);
        childB = getParent(parent, childB);
        if (childA < childB) {
            parent[childB] = childA;
        } else {
            parent[childA] = childB;
        }
    }

    static boolean checkSameParent(int[] parent, int childA, int childB) {
        if (getParent(parent, childA) == getParent(parent, childB)) return true;
        return false;
    }

    static boolean checkCommunication(pos a, pos b) {
        int fullRange = a.range + b.range;
        double btw = Math.sqrt((Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2)));
        if(btw <= fullRange){
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {
            int n = Integer.parseInt(br.readLine());
            pos[] arr = new pos[n];
            int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int range = Integer.parseInt(st.nextToken());
                arr[i] = new pos(x, y, range);
            }

            for (int i = 0; i < n; i++) {
                pos begin = arr[i];
                for (int j = i + 1; j < n; j++) {
                    pos dest = arr[j];
                    if(checkCommunication(begin, dest)){
                        union(parent,i,j);
                    }
                }
            }
            HashSet<Integer> set = new HashSet<>();
            for (int i = 0; i < n; i++) {
                set.add(getParent(parent, i));
            }
            System.out.println(set.size());
        }
    }
}
