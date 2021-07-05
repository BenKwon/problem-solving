package 백준.Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 골드 4이지만 문제 자체는 몇몇 예외 상황만 따져주면 쉽다
 * 기본적으로 음수 큐와 양수 큐로 나누어서 푼다.
 * 숫자가 1인경우 곱하면 오히려 더했을때 작아지므로 숫자1은 다른숫자와 묶지않는다.
 * 숫자가 0인경우 음수 와 곱했을때 음수를 없애주어 최댓값이 나오는데 기여할 수 있으므로
 * 음수 큐에 넣는다.
 * 음수와 음수를 곱했을때는 양수가 나온다.
 *
 * 위 4가지를 이용하여 풀었다.
 */
public class 수묶기_1744_Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> negative = new PriorityQueue<>();
        PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
        Queue<Integer> zero = new PriorityQueue<>();
        int sol = 0;

        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt((br.readLine()));
            if (a == 1) {
                sol += 1;
            } else if (a <= 0) {
                negative.offer(a);
            } else if (a > 0) {
                positive.offer(a);
            }
        }
        //음수 계산
        while (true) {
            if (negative.size() == 0) {
                break;
            }
            if (negative.size() < 2) {
                sol += negative.poll();
            } else {
                sol += negative.poll() * negative.poll();
            }
        }

        //양수 계산
        while (true) {
            if (positive.size() == 0) {
                break;
            }
            if (positive.size() < 2) {
                sol += positive.poll();
            } else {
                sol += positive.poll() * positive.poll();
            }
        }
        System.out.println(sol);
    }
}
