package 백준.Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class 주유소_13305 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 도시의 개수
        long[] distance = new long[n - 1];
        long[][] oil_price = new long[n - 1][2];
        long[] accum_distance = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            distance[i] = scanner.nextLong();
            accum_distance[i] = distance[i];
        }
        for (int i = 0; i < n - 1; i++) {
            oil_price[i][0] = scanner.nextLong();
            oil_price[i][1] = i;
        }
        scanner.nextLong(); // 마지막 도시 오일 가격 정보는 필요 없음

        Arrays.sort(oil_price, new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                if (o1[0] == o2[0]) {
                    return (int)(o1[1] - o2[1]);
                } else {
                    return (int)(o1[0] - o2[0]);
                }
            }
        });
        for (int d = n - 2; d >= 0; d--) {
            if (d == n - 2) {
                continue;
            } else {
                accum_distance[d] = accum_distance[d] + accum_distance[d + 1];
            }
        }

        long bound = 100000;
        long result_price = 0;
        for (int i = 0; i < n - 1; i++) {
            if (oil_price[i][1] < bound) {
                long tmp;
                if (i == 0) {
                    tmp = accum_distance[(int) oil_price[i][1]];
                } else {
                    tmp = accum_distance[(int)oil_price[i][1]] - accum_distance[(int)bound];
                }
                result_price += oil_price[i][0] * tmp;
                bound = oil_price[i][1];
            } else {
                continue;
            }
            bound = oil_price[i][1];
        }

        System.out.println(result_price);
    }
}
