package 백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_1700_멀티탭스케줄링 {
    static class Node {
        int num;
        int nextIdx;

        public Node(int num, int nextIdx) {
            this.num = num;
            this.nextIdx = nextIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Node[] seq = new Node[k];
        st = new StringTokenizer(br.readLine());
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < k; i++) {
            seq[i] = new Node(Integer.parseInt(st.nextToken()), -1);
        }
        for (int i = k - 1; i >= 0; i--) {
            Node next = seq[i];
            if (map.containsKey(next.num)) {
                next.nextIdx = map.get(next.num);
            }
            map.put(next.num, i);
        }
        Node[] multiTab = new Node[n];
        for (int i = 0; i < n; i++) {
            multiTab[i] = new Node(-1, -1);
        }
        int countPullPlug = 0;
        for (int i = 0; i < k; i++) {
            int next = seq[i].num;
//            System.out.println("---------------------------------");
//            System.out.println("i = " + i);
            boolean find = false;
            int maxNextIdx = -1000;
            int targetIdx = -1000;
            boolean last = false;
            for (int j = 0; j < n; j++) {
//                System.out.printf("multitab[%d] : [%d, %d]\n",j,multiTab[j].num,multiTab[j].nextIdx);
                if (multiTab[j].nextIdx >= 0 && !last) {
                    if (multiTab[j].nextIdx > maxNextIdx) {
                        maxNextIdx = multiTab[j].nextIdx;
                        targetIdx = j;
                    }
                } else if (multiTab[j].nextIdx == -1) {
                    if(!last) targetIdx = j;
                    last = true;
                    if(targetIdx >= 0){
                        if(multiTab[j].num == -1){
                            targetIdx = j;
                        }
                    }
                }
                if (multiTab[j].num == next) {
                    find = true;
//                    System.out.println("find");
                    multiTab[j].nextIdx = seq[i].nextIdx;
                }
            }
            if (find) continue;
            if(multiTab[targetIdx].num != -1)  countPullPlug++;
//            System.out.println("maxNextIdx = " + maxNextIdx);
//            System.out.printf("target : %d\n", targetIdx);
            multiTab[targetIdx] = seq[i];
//            System.out.println("countPullPlug = " + countPullPlug);
        }
        System.out.println(countPullPlug);

    }
}
