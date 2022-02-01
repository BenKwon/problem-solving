package 백준.이분탐색;

import java.util.*;
import java.io.*;

public class Main_2143_두배열의합 {
    static int t, n, m;
    static int[] a, b;
    static int[] aAccum, bAccum;
    static HashMap<Integer, Integer> aMap = new HashMap<>();
    static HashMap<Integer, Integer> bMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        //a 누적합 구하기
        n = Integer.parseInt(br.readLine());
        a = new int[n + 1];
        aAccum = new int[n + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            a[i] = (Integer.parseInt(st.nextToken()));
            aAccum[i] = aAccum[i - 1] + a[i];
        }

        //b 누적합 구하기
        m = Integer.parseInt(br.readLine());
        b = new int[m + 1];
        bAccum = new int[m + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++) {
            b[i] = (Integer.parseInt(st.nextToken()));
            bAccum[i] = bAccum[i - 1] + b[i];
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                int start = j;
                int end = j + i;
                if (end > n) break;
                int sum = aAccum[end] - aAccum[start - 1];
                if (aMap.containsKey(sum)) {
                    aMap.put(sum, aMap.get(sum) + 1);
                } else {
                    aMap.put(sum, 1);
                }
            }
        }
        for(int i  : aMap.keySet()){
            System.out.printf("%d : %d개 \n", i, aMap.get(i));
        }
        System.out.println("--------------------------------------");


        for (int i = 0; i < m; i++) {
            for (int j = 1; j <= m; j++) {
                int start = j;
                int end = j + i;
                if (end > m) break;
                int sum = bAccum[end] - bAccum[start - 1];
                if (bMap.containsKey(sum)) {
                    bMap.put(sum, bMap.get(sum) + 1);
                } else {
                    bMap.put(sum, 1);
                }
            }
        }
        for(int i  : bMap.keySet()){
            System.out.printf("%d : %d개 \n", i, bMap.get(i));
        }
        long sol = 0;
        for (int i : aMap.keySet()) {
            int j = t - i;
            if (!bMap.containsKey(j)) continue;
            sol += (long) aMap.get(i) * (long) bMap.get(j);
        }
        System.out.println(sol);
        int test =3000000;
        int test2 = 2000000;
    }
}
