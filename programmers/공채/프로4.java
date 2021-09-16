package programmers.공채;

public class 프로4 {
    static int[] g_info;
    // level : 0 이 10점 짜리 과녁부터 시작
    public static int dfs(int level, int arrows_num) {
        int tmp1 = Integer.MIN_VALUE;
        if(level == 3){
            return 0;
        }
        //
        if(arrows_num-(g_info[level] + 1) >= 0){
            tmp1 = level + dfs(level + 1 ,arrows_num-(g_info[level] + 1));
        }
        int tmp2  = dfs(level + 1 ,arrows_num);
        int sol = Math.max(tmp1, tmp2);

        System.out.println(sol);
        return sol;
    }

    public static int[] solution(int n, int[] info) {
        int[] answer = {};
        g_info = info.clone();
        for (int i = 0; i < info.length; i++) {
            g_info[i] = info[(info.length-1) - i];
        }
        int sol = 0 + dfs(1, n-(g_info[0] + 1));
        int sol2 = dfs(1, n);

        System.out.println(Math.max(sol,sol2));
        return answer;
    }

    public static void main(String[] args) {
        int n = 1;
        int[] info = {
                1,0,0
        };
        int[] result = {
                0, 2, 2, 0, 1, 0, 0, 0, 0, 0, 0
        };
        solution(n, info);
    }
}
