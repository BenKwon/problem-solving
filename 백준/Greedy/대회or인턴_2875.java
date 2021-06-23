package 백준.Greedy;

import java.util.Scanner;

public class 대회or인턴_2875 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int K = scanner.nextInt();

        for (int i = 0; i < K; i++) {
            if (M / 2 >= N) {
                M--;
            } else {
                N--;
            }
        }

        if (M/2 > N) {
            System.out.println(N);
        } else {
            System.out.println(M/2);
        }
    }
}
