package 백준.구현;

import java.io.*;
import java.util.*;

public class Main_19638_센티와마법의뿅망치 {
    static int n, h, anInt;
    static PriorityQueue<Integer> pqMax = new PriorityQueue<>(Collections.reverseOrder());

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            int next = Integer.parseInt(br.readLine());
            if (next >= h) {
                pqMax.add(next);
            }
        }
        int count = 0;
        if (pqMax.isEmpty()) {
            System.out.println("YES");
            System.out.println(0);
            return;
        }
        while (count < t) {
            int poll = pqMax.poll();
            if (poll == 1) {
                pqMax.add(1);
                count++;
            } else if (poll >= h) {
                pqMax.add(poll / 2);
                count++;
                if (pqMax.peek() < h) {
                    System.out.println("YES");
                    System.out.println(count);
                    return;
                }
            }
        }
        System.out.println("NO");
        System.out.println(pqMax.peek());

    }
}
