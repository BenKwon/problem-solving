package programmers.공채;

import java.util.Arrays;

public class 프로그래밍4 {
    static int[] g_info;

    // level : 0 이 10점 짜리 과녁부터 시작
    public static int dfs(int level, int arrows_num) {
        int tmp1 = Integer.MIN_VALUE;

        if (level == 10 || arrows_num == 0) {
            return 0;
        }

        //이번 level 의 과녁 ( 과녁 점수 : 10 - level)을 통해 점수를 얻고 싶을때
        System.out.printf("g_info[%d] : %d\n", level + 1, g_info[level + 1]);
        System.out.printf("lev :%d , num :%d \n", level, arrows_num);

        if (arrows_num >= g_info[level + 1] + 1) {
            int base = 10 - (level + 1);
            tmp1 = base + dfs(level + 1, arrows_num - (g_info[level + 1] + 1));
//            sol = (10-level) + Math.max(sol, );
        }
        //이번 level의 과녁은 안 맞출거임
        int tmp2 = dfs(level + 1, arrows_num);
        int sol;
        if (tmp1 > tmp2) {
            sol = tmp1;
        } else {
            sol = tmp2;
        }
        return sol;
    }

    public static int[] solution(int n, int[] info) {
        int[] answer = {};
        g_info = info.clone();

        int sol = dfs(-1, n);

        System.out.println(sol);
        return answer;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] info = {
                0,0,0,0,0,0,0,0,3,4,3
        };
        int[] result = {
                0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0
        };
        solution(n, info);
    }
}
