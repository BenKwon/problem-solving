package 백준.Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class 크게만들기_2812 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        String digit = sc.next();

        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                } else {
                    return o1[0] - o2[0];
                }
            }
        });


        int len = digit.length();
        for (int i = 0; i < len; i++) {
            int a[] = {Integer.parseInt(String.valueOf(digit.charAt(i))), i};
            priorityQueue.offer(a);
        }

        for (int i = 0; i < k; i++) {
            int[] target = priorityQueue.poll();
            int index = target[1];
            if (index < len - 1) {
                digit = digit.substring(0, index) + "." + digit.substring(index + 1);
            } else {
                digit = digit.substring(0, index) + ".";

            }
        }

        String sol = "";
        for (int i = 0; i < len; i++) {
            if (digit.charAt(i) != '.') {
                sol += digit.charAt(i);
            }
        }
        System.out.println(Integer.parseInt(sol));


    }
}
