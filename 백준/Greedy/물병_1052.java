package 백준.Greedy;

import java.util.PriorityQueue;
import java.util.Scanner;

public class 물병_1052 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        PriorityQueue<Long> pq = new PriorityQueue<>();
        while(N > 1){
            int copy_n = N;
            int seq_square = 0;
            while(copy_n > 1){
                copy_n = copy_n / 2;
                seq_square++;
            }
            Long new_bottle = (long)Math.pow((double) 2, (double) seq_square);
            pq.offer(new_bottle);
            N -= new_bottle;
        }
        if(N != 0){
            pq.offer((long)N);
        }

        long sol = 0;
        while(pq.size() > K){
            long bottle1 = pq.poll();
            long bottle2 = pq.poll();
            if(bottle1 == bottle2){
                pq.offer(bottle1 + bottle2);
            }
            else{
                sol += bottle2 - bottle1;
                pq.offer(bottle2 * 2);
            }
        }
        System.out.println(sol);
    }
}
