package 백준.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Scanner;

public class Main_1793_타일링 {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int test = Integer.parseInt(br.readLine());
        BigInteger[] dp = new BigInteger[251];
        dp[0] = BigInteger.valueOf(1);
        dp[1] = BigInteger.valueOf(1);
        dp[2] = BigInteger.valueOf(3);

        for (int i = 3; i < 251; i++) {
            dp[i] = dp[i - 2].multiply(new BigInteger("2"));
            dp[i] = dp[i].add(dp[i - 1]);
        }
        while(sc.hasNextInt()) { // 입력이 존재하면 계속 반복한다. , 테스트 케이스가 존재하지 않기 때문
            int n=sc.nextInt(); // n이 들어오면
            System.out.println(dp[n]); // 해당 n값을 출력한다.
        }
    }
}
