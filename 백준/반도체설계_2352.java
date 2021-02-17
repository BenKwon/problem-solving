package 백준;

import java.util.Scanner;

/*
최장 증가 부분 수열 문제
 */
//2352
public class 반도체설계_2352 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] port_pair = new int[n];
        int max = 0;
        int[] length = new int[n];

        for (int i = 0; i < n; i++) {
            port_pair[i] = scanner.nextInt();
        }

        for (int i = 0; i < n; i++) {
            length[i] = 1;
            for(int j = 0 ; j < i ; j++){
                if(port_pair[j] < port_pair[i]){
                    length[i] = Math.max(length[i], length[j]+1);
                }
            }
            if(max < length[i]){
                max = length[i];
            }
        }
        System.out.println(max);
    }
}
