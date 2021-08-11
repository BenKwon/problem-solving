package 백준.DP;

import java.util.Scanner;

public class Main_이모티콘_14226 {
    public static int n;
    public static int[][] dp; // [display][clipboard]
    static int min = Integer.MAX_VALUE;
    public static int dfs(int display, int clipboard, int count){
        if(display == n){
            min = Math.min(min, count);
            return 0;
        }
        if(dp[display][clipboard]== 0){
            dp[display][clipboard] = count;
        }else{
            if (dp[display][clipboard] > count) {
                dp[display][clipboard] = count;
            }else{

                return 0;
            }
        }
        //클립보드에 저장
        //붙여 넣기
//        int result = Integer.MAX_VALUE;
        if(display < n){
            dfs(display, display, count + 1);
            dfs(display +  clipboard, clipboard, count + 1);
        }
        // 이모티콘 삭제
        if(display > 1){
            dfs(display - 1, clipboard, count + 1);
        }
        return 0;
    }

    public static void main(String[] args) {
        n = new Scanner(System.in).nextInt();
        dp = new int[2001][2001];
        dp[1][0] = 1;
        dfs(1, 1, 1);
//        System.out.println(dfs(1, 1, 1));
        System.out.println(min);

    }
}
