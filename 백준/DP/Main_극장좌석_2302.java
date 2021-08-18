package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_극장좌석_2302 {
    public static int n, m;
    public static ArrayList<Integer> tickets = new ArrayList<>();
    public static int[] seats;
    public static int[][][][] dp;

    /**
     * dp[현재 티켓 실제 번호][실제로 앉은 자리 번호][이전 티켓 번호가 앉은 자리번호][전전의 티켓의 번호가 앉은 자리번호]
     * 현재 티켓에 써있는 번호가 앉을수있는 경우의수는 이전 티켓 번호가 앉은 자리와
     * 전전 티켓번호 앉은자리가 영향을 끼쳐서 이렇게 3개로 묶어 메모제이션을 해야한다.
     */

    public static int dfs(int cur_ticket_idx, int one_b, int two_b) {
        int ticket_num = tickets.get(cur_ticket_idx);
        int left = tickets.get(cur_ticket_idx) - 1;
        int right = tickets.get(cur_ticket_idx) + 1;
        int answer = 0;
        if (cur_ticket_idx != 0 && tickets.get(cur_ticket_idx) == 0) {
            return 1;
        }
//        System.out.printf("ticket_num = %d, left : %d, right : %d , one_b : %d , tow_b :%d\n" , ticket_num
//        ,left,right,one_b,two_b);

        if (left != one_b && left != two_b && seats[left] != 1) {
//            System.out.printf("ticket num : %d , take left\n",ticket_num);
            if (dp[ticket_num][left][one_b][two_b] == 0){
                dp[ticket_num][left][one_b][two_b] = dfs(cur_ticket_idx + 1, left, one_b);
                dp[ticket_num][left][two_b][one_b] =dp[ticket_num][left][one_b][two_b];
            }else{
//                System.out.println("dp!");
            }

            answer += dp[ticket_num][left][one_b][two_b];
//            System.out.printf("ticket : %d, answer: %d\n", cur_ticket_idx,answer);

        }
        if (ticket_num != one_b && ticket_num != two_b && seats[ticket_num] != 1) {
//            System.out.printf("ticket num : %d , take\n",ticket_num);

            if (dp[ticket_num][ticket_num][one_b][two_b] == 0){
                dp[ticket_num][ticket_num][one_b][two_b] = dfs(cur_ticket_idx + 1, ticket_num, one_b);
                dp[ticket_num][ticket_num][two_b][one_b] = dp[ticket_num][ticket_num][one_b][two_b];
            }else{
//                System.out.println("dp!");
            }
            answer += dp[ticket_num][ticket_num][one_b][two_b];
//            System.out.printf("ticket : %d, answer: %d\n", cur_ticket_idx,answer);


        }
        if (right != one_b && right != two_b  && seats[right] != 1) {
//            System.out.printf("ticket num : %d , take right\n",ticket_num);

            if (dp[ticket_num][right][one_b][two_b] == 0){
                dp[ticket_num][right][one_b][two_b] = dfs(cur_ticket_idx + 1, right, one_b);
                dp[ticket_num][right][two_b][one_b] = dp[ticket_num][right][one_b][two_b];
            }else{
//                System.out.println("dp!");
            }
            answer += dp[ticket_num][right][one_b][two_b];
//            System.out.printf("ticket : %d, answer: %d\n", cur_ticket_idx,answer);

        }
//        System.out.printf("ticket : %d, answer: %d\n", cur_ticket_idx,answer);
        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        HashSet<Integer> vip = new HashSet<>();
        seats = new int[n + 2];

        dp = new int[42][42][42][42];
        long seat_info = (1 | 1 << (n + 1));
        for (int v = 0; v < m; v++) {
            int tmp = Integer.valueOf(br.readLine());
            vip.add(tmp);
            seat_info |= (1 << tmp);
            seats[tmp] = 1;
        }
        seats[0] = 1;
        seats[n + 1] = 1;
        tickets.add(0);
        for (int i = 1; i <= n; i++) {
            if (!vip.contains(i)) {
                tickets.add(i);
            }
        }
        Collections.sort(tickets);
        tickets.add(0);

        int dfs = dfs(1, 0, 0);
        System.out.println(dfs);

    }
}
