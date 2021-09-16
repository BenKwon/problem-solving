package programmers.공채;

import java.util.Arrays;

public class 프로그램4 {
    static int[] g_info;
    static int[] backTracking;
    static int max = Integer.MIN_VALUE;
    static int apeach;
    static int[] sol = new int[11];
    // level : 0 이 10점 짜리 과녁부터 시작
    public static int dfs(int level, int arrows_num, boolean shoot_before_level, int count, int bitmask) {

        if (level == 10 || arrows_num <= 0) {
            int total = 0;
            int tmp_apeach = apeach;
            int[] sol_tmp = new int[11];
            for (int i = 0; i <= 10; i++) {
                if ((bitmask & 1 << i) != 0) {
                    total += (10 - i);
                    if (g_info[i] != 0)
                        tmp_apeach -= (10 - i);
                    sol_tmp[i] = 1;
                }
            }
            if (total > tmp_apeach) {
                int new_max = total - tmp_apeach;
                if (max < new_max) {
                    sol = new int[11];
                    max = new_max;
                    for (int i = 0; i < sol_tmp.length; i++) {
                        if(sol_tmp[i] == 1){
                            sol[i] = g_info[i] + 1;
                        }
                    }
                }
            }

//            System.out.println(total);
            return 0;
        }
        int tmp1;
        int tmp2;
        if (shoot_before_level == true) {
            if (arrows_num - (g_info[level] + 1) >= 0) {
                tmp1 = dfs(level + 1, arrows_num - (g_info[level] + 1), true, count + (10 - level), bitmask | (1 << (level)));
                tmp2 = dfs(level + 1, arrows_num - (g_info[level] + 1), false, count + (10 - level), bitmask | (1 << (level)));
            } else {
                return 0;
            }
        } else {
            tmp1 = dfs(level + 1, arrows_num, true, count, bitmask);
            tmp2 = dfs(level + 1, arrows_num, false, count, bitmask);
        }

        int sol;
        if (tmp1 > tmp2) {
            //다음 과녁을 포함한것
            sol = tmp1;
        } else {
            //다음 과녁을 포함하지 않은것
            sol = tmp2;
        }
//        if(shoot_before_level){
//            System.out.printf("level : %d ,  arr : %d ,true -> sol : %d ,,, \n",level,arrows_num,sol);
//        }else{
//            System.out.printf("level : %d ,  arr : %d ,false -> sol : %d ,,, \n",level,arrows_num,sol);
//
//        }


        return sol;
    }

    public static int[] solution(int n, int[] info) {
        int[] answer = {};
        g_info = info.clone();
        apeach = 0;
        for (int i = 0; i < info.length; i++) {
            if (info[i] != 0) {
                apeach += (10 - i);
            }
        }
        for (int i = 0; i < info.length; i++) {
            g_info[i] = info[i];
        }
//        backTracking = new int[11];
        dfs(-1, n, false, 0, 0);

//        for (int i = 0; i < sol.length; i++) {
//            System.out.printf("%d ",sol[i]);
//        }
        if(max == Integer.MIN_VALUE){
            answer = new int[]{-1};
            return answer;
        }else{
            return sol;
        }
    }

    public static void main(String[] args) {
        int n = 1;
        int[] info = {
                1,0,0,0,0,0,0,0,0,0,0
        };
//        int[] result = {
//                0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0
//        };
        int[] solution = solution(n, info);
        for (int i : solution) {
            System.out.printf("%d ",i);

        }
    }
}
