package 백준.Greedy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 풀이 방법
 일단 문자열을 최초에 N의 갯수만큼 B로 초기화 한다.
 EX) N = 6
 BBBBBB 이때 조건을 만족하는 문자열의 쌍은 0개
 처음에 마지막 으로 부터 2번째자리에 A를넣으면
 BBBBAB 이때 조건을 만족하는  문자열의 쌍은 1개가 되고
 BBBABB 이때는 2개
 BBABBB 이때는 3개 이런식으로 A가 왼쪽으로 갈때마다 조건을 만족하는 쌍이 1개씩 증가한다.
 A가 마지막까지 옮겨 지면
 ABBBBB가 된다 이때 만족하는 쌍은 5개이다.
 여기서 부터 다시 처음에 했던 것보다 1자리 더 큰 3번쩨자리에 A를 넣으면
 ABBABB게되고 이때 만족하는 쌍은 6개
 다시 A를 왼쪽으로 한칸을 옮길때마다 만족하는 쌍은 1개씩늘어나고 A가 끝까지 올때까지 반복하고
 그래도 K에 못미치면 다시 오른쪽에서 4번째자리에 A를 새로 넣고 왼쪽으로 옮기면 만족하는 쌍이 1개씩 늘어난다.
 늘어날때마다 현재 만족하는 쌍의 개수와 K를 비교하면서 같다면 정답!
 */
public class AB_12970_Main {
    //A 는 1 B 는 0으로 하겠다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] ab = new int[n];
        int sol = 0;

        // k <= n-1;
        int rot = 2;
        int start = 0;
//        if()
        if (k != 0) {
            while (true) {
                int edit_idx;
//            System.out.println("====================");
//            System.out.println("rot = " + rot);
//            System.out.println("start = " + start);
                if (start > n - rot) {
                    System.out.println(-1);
                    return;
                }
                for (edit_idx = n - rot; edit_idx >= start; edit_idx--) {
//                System.out.println("edit_idx = " + edit_idx);
                    sol++;
                    if (sol == k) {
                        ab[edit_idx] = 1;
                        break;
                    }
                }
                if (sol == k) {
//                System.out.println(sol);
                    break;
                } else {
                    ab[start] = 1;
                    start++;
                    rot++;
                }
            }
        }
        int len = ab.length;
        for (int i = 0; i < len; i++) {
            if (ab[i] == 1) {
                sb.append('A');
            } else {
                sb.append('B');
            }
        }

        System.out.println(sb);


    }
}
