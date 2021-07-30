package 백준.DP;

import java.util.Scanner;

public class Main_이모티콘_14226 {
    public static int n;
    public static int dfs(int cur_emoji, int cur_copy, int count, int before_copy){
        if(cur_emoji == n) {
//            System.out.println(count);
            if(before_copy == 1) return count - 1;
            return count;
        }

        if(cur_emoji > n || cur_emoji < 0) return Integer.MAX_VALUE;

        //현재 클립보드를 붙여넣기하고 이전 클립보드를(cur copy) 그대로 사용할때
        return Math.min(dfs(cur_emoji + cur_copy, cur_copy, count + 1,0),
                Math.min(dfs(cur_emoji + cur_copy - 1, cur_copy, count + 1 + 1,0),
                        Math.min(dfs(cur_emoji + cur_copy - 1, cur_emoji + cur_copy, count + 1 + 1 + 1,1),dfs(cur_emoji + cur_copy, cur_emoji + cur_copy, count + 1 + 1,1))));
        //현재 클립보드를 붙여넣기하고 현재 화면 갯수만큼 다시 복사
        //현재 클립보드에서 하나삭제

    }

    public static void main(String[] args) {
        n = new Scanner(System.in).nextInt();
        System.out.println(dfs(1, 1, 1, 1));
    }
}
