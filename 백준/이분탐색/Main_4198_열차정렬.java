package 백준.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main_4198_열차정렬 {
    static int n;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if(n==0){
            System.out.println(0);
            return;
        }
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int solution = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int point = arr[i];
            //LIS(최장 증가 부분 수열)
            ArrayList<Integer> longSeq = new ArrayList<>();
//            System.out.printf("arr[%d] :  %d\n",i, point);
            for (int j = i + 1; j < n; j++) {
                int next = arr[j];
                if (next < point) continue;
                if (longSeq.size() == 0) longSeq.add(next);
                else if (next > longSeq.get(longSeq.size() - 1)) {
                    longSeq.add(next);
                } else {
                    int index = Math.abs(Collections.binarySearch(longSeq, next) + 1);
                    longSeq.set(index, next);
                }
            }
            int leftPart = longSeq.size();
            //LDS(촤장 감소 부분 수열)
            ArrayList<Integer> shortSeq = new ArrayList<>();
            for (int j = i + 1; j < n; j++) {
                int next = arr[j];
                if (next > point) continue;
//                System.out.println("j : " + j  + "/ " + "next " +  next);

                if (shortSeq.size() == 0) shortSeq.add(next);
                else if (next < shortSeq.get(shortSeq.size() - 1)) {
                    shortSeq.add(next);
                } else {
//                    for (int m = 0; m < shortSeq.size(); m++) {
//                        System.out.printf("%d ", shortSeq.get(m));
//                    }
//                    System.out.println();
                    int index = Math.abs(Collections.binarySearch(shortSeq, next,Collections.reverseOrder()) + 1);
//                    System.out.println("index : " + index);
                    shortSeq.set(index, next);
                }
            }
            int rightPart = shortSeq.size();
            solution = Math.max(solution, leftPart + rightPart + 1);
        }
        System.out.println(solution);
    }
}
