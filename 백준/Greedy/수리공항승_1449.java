package 백준.Greedy;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class 수리공항승_1449 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 물이 새는 곳의 개수
        int L = scanner.nextInt(); // 테이프의 길이
        int[] broken_pos = new int[N];
        int repair_idx = 0; // 고칠 포지션 위치
        double cover = 0; //현재 붙여논 테이프로 커버 가능한 최대 위치
        int tape_num = 0 ; // solution : 필요한 테이프 개수
        for (int i = 0; i < N; i++) {
            broken_pos[i] = scanner.nextInt();
        }
        Arrays.sort(broken_pos);
        cover = broken_pos[repair_idx] - 0.5 + L;
        tape_num++;
        repair_idx++;
        while (repair_idx != N) {
            if (cover < broken_pos[repair_idx] + 0.5) { // 현재 붙여진 테이프가 현재 위치를 수리하지 못함
                cover = broken_pos[repair_idx] - 0.5 + L;
                tape_num++;
            } else { //현재 테이프가 현재 위치를 수리하고 있음
            }
            repair_idx++;
        }
        System.out.println(tape_num);
    }
}
