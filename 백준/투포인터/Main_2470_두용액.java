package 백준.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main_2470_두용액 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        ArrayList<Integer> seq = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            seq.add(Integer.parseInt(st.nextToken()));
        }
        seq.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(o2.intValue()) - Math.abs(o1.intValue());
            }
        });
        boolean findMinus = false;
        boolean findPlus = false;
        ArrayList<Integer> plusIndex = new ArrayList<>();
        ArrayList<Integer> minusIndex = new ArrayList<>();
        for (int i = 0; i < seq.size(); i++) {
            int num = seq.get(i);
            if (num < 0) {
                findMinus = true;
                minusIndex.add(i);
            } else if (num > 0) {
                findPlus = true;
                plusIndex.add(i);
            }
        }
        if (!findMinus) {
            System.out.printf("%d %d\n", seq.get(n - 1), seq.get(n - 2));
            return;
        } else if (!findPlus) {
            System.out.printf("%d %d\n", seq.get(n - 2), seq.get(n - 1));
            return;
        }
        int p1 = 0; //for minus index 알칼리
        int p2 = 0; //for plus index 산성
        int min = Integer.MAX_VALUE;
        int[] sol = new int[2];
        boolean end = false;
        while (!end) {
            int indexP1 = minusIndex.get(p1);
            int indexP2 = plusIndex.get(p2);
            if(Math.abs(seq.get(indexP1) + seq.get(indexP2)) < min){
                sol[0] = seq.get(indexP1);
                sol[1] = seq.get(indexP2);
                min = Math.abs(seq.get(indexP1) + seq.get(indexP2));
            }
//            System.out.printf("indexP1 : %d, indexP2 : %d, min : %d\n",indexP1,indexP2,seq.get(indexP1) + seq.get(indexP2));
            if (indexP1 > indexP2) {
                p2++;
                if (p2 >= plusIndex.size()) {
                    end = true;
                    break;
                }
                indexP2 = plusIndex.get(p2);
            } else {
                p1++;
                if (p1 >= minusIndex.size()) {
                    end = true;
                    break;
                }
                indexP1 = minusIndex.get(p1);
            }
        }
//        System.out.println(min);
        for (int i = 0; i < minusIndex.size() - 1; i++) {
            int realIndex = minusIndex.get(i);
            int realIndex2 = minusIndex.get(i + 1);
            int target = Math.abs(seq.get(realIndex) + seq.get(realIndex2));
            if(target < min){
                sol[0] = seq.get(realIndex);
                sol[1] = seq.get(realIndex2);
                min = target;
            }
        }
        for (int i = 0; i < plusIndex.size() - 1; i++) {
            int realIndex = plusIndex.get(i);
            int realIndex2 = plusIndex.get(i + 1);
            int target = Math.abs(seq.get(realIndex) + seq.get(realIndex2));
            if(target < min){
                sol[0] = seq.get(realIndex);
                sol[1] = seq.get(realIndex2);
                min = target;
            }
        }
        Arrays.sort(sol);
        System.out.printf("%d %d",sol[0],sol[1]);
    }
}

