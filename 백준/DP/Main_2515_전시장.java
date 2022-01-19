package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2515_전시장 {
    static int n, s;
    static ArrayList<Painting> paintings = new ArrayList<>();
    static int[][] dp;
    static int[] visit;

    static class Painting {
        int height;
        int price;

        public Painting(int height, int price) {
            this.height = height;
            this.price = price;
        }
    }

    static int binarySearch(int start, int end, int key) {
        int mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            int height = paintings.get(mid).height;
            if (height < key) {
                start = mid + 1;
            } else if (height > key) {
                end = mid - 1;
            } else { //key found
                return mid;
            }
        }
        return -(start + 1); //key not fount
    }

//    static int[] dfs(int seq) {
//        if (visit[seq] == 1) return dp[seq];
//        Painting paint = paintings.get(seq);
//        int totalValue = paint.price;
//        int max = 0;
//
//        int index = binarySearch(seq + 1, n, paint.height + s);
//
//
//
//        visit[seq] = 1;
//        return
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][2];
        visit = new int[n + 1];
        paintings.add(new Painting(-1, 0));
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            paintings.add(new Painting(height, price));
        }
        Collections.sort(paintings, ((o1, o2) -> {
            if(o1.height == o2.height) return o1.price - o2.price;
            return o1.height - o2.height;
        }
        ));
//        for (
//                Painting p : paintings) {
//            System.out.println(p.height + " " + p.price);
//        }

        dp[n][1] = paintings.get(n).price;
//        System.out.println("start !!");
        for (
                int i = n - 1;
                i >= 1; i--) {
            Painting paint = paintings.get(i);
//            System.out.println("-----------------------------------");
//            System.out.println("i = " + i);
            int index = binarySearch(i + 1, n, paint.height + s);
            if (index < 0) index = Math.abs(index + 1);
//            System.out.println("index = " + index);
            if (index <= n) {
                dp[i][1] = Math.max(dp[index][1], dp[index][0]) + paint.price;
            } else {
                dp[i][1] = paint.price;
            }
            dp[i][0] = Math.max(dp[i + 1][0], dp[i + 1][1]);
//            System.out.printf("dp[%d] : {%d, %d}\n", i, dp[i][0], dp[i][1]);
        }
//        int result = dfs(1);
//        for (int i = 1; i <= n; i++) {
//            solution = Math.max(solution, dp[i]);
//        }
        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
}
