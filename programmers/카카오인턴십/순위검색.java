package programmers.카카오인턴십;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

//정확성검사 2/4 ㅠㅠ...마지막을 이분탐색??
public class 순위검색 {
    // cpp : 0 , java 1 : , python :2 , - : 3, 직군 : backend =
    static int[][][][][] graph = new int[4][3][3][3][50002]; //[개발언어][직군][경력][소울푸드][점수]
    static int[] way = new int[5];

    public static int dfs(int level,int lan,int part, int career, int food) {
        if (level ==5){
            int sum = 0;

            for (int i = 0; i < graph[lan][part][career][food][50001]; i++) {
                if(graph[lan][part][career][food][i] >= way[4]){
                    sum++;
                }
            }
            return sum;
        }

        int select = way[level-1];
        int sum = 0;
        if (level == 1) { //언어
            switch (select){
                case 0:
                    sum += dfs(level+1,0,part,career,food);
                    break;
                case 1:
                    sum += dfs(level+1,1,part,career,food);
                    break;
                case 2:
                    sum += dfs(level+1,2,part,career,food);
                    break;
                default: // 3
                    sum += dfs(level+1,0,part,career,food);
                    sum += dfs(level+1,1,part,career,food);
                    sum += dfs(level+1,2,part,career,food);
                    break;
            }
        } else if (level == 2) { // 직종
            switch (select){
                case 0:
                    sum += dfs(level+1,lan,0,career,food);
                    break;
                case 1:
                    sum += dfs(level+1,lan,1,career,food);
                    break;
                default: // 2
                    sum += dfs(level+1,lan,0,career,food);
                    sum += dfs(level+1,lan,1,career,food);
                    break;
            }
        } else if (level == 3) { // 경력
            switch (select){
                case 0:
                    sum += dfs(level+1,lan,part,0,food);
                    break;
                case 1:
                    sum += dfs(level+1,lan,part,1,food);
                    break;
                default: // 2
                    sum += dfs(level+1,lan,part,0,food);
                    sum += dfs(level+1,lan,part,1,food);
                    break;
            }
        } else { //음식
            switch (select){
                case 0:
                    sum += dfs(level+1,lan,part,career,0);
                    break;
                case 1:
                    sum += dfs(level+1,lan,part,career,1);
                    break;
                default: // 2
                    sum += dfs(level+1,lan,part,career,0);
                    sum += dfs(level+1,lan,part,career,1);
                    break;
            }
        }
        return sum;
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        for (int i = 0; i < info.length; i++) {
            StringTokenizer st = new StringTokenizer(info[i]);
            int lan_idx = languageToInt(st.nextToken());
            int part_idx = partToInt(st.nextToken());
            int career_idx = careerToInt(st.nextToken());
            int food_idx = foodToInt(st.nextToken());
            graph[lan_idx][part_idx][career_idx][food_idx][graph[lan_idx][part_idx][career_idx][food_idx][50001]] = Integer.parseInt(st.nextToken());
            graph[lan_idx][part_idx][career_idx][food_idx][50001]++;
        }
        for (int i = 0; i < query.length; i++) {
//            StringTokenizer st = new StringTokenizer(query[i],"and");
            String[] split = query[i].split("and");
            int language = languageToInt(split[0].trim());
            int part = partToInt(split[1].trim());
            int career = careerToInt(split[2].trim());
            String foodAndScore = split[3].trim();
            int food = foodToInt(foodAndScore.split(" ")[0]);
            int score = Integer.parseInt(foodAndScore.split(" ")[1]);
            way = new int[]{language, part, career, food, score};
            answer[i] = dfs(1, -1, -1, -1, -1);
            int[] ints = graph[language][part][career][food];
        }


        return answer;
    }

    public static int languageToInt(String lan) {
        if (lan.compareTo("cpp") == 0) {
            return 0;
        } else if (lan.compareTo("java") == 0) {
            return 1;
        } else if (lan.compareTo("python") == 0) {
            return 2;
        } else {// '-'
            return 3;
        }
    }

    public static int partToInt(String part) {
        if (part.compareTo("backend") == 0) {
            return 0;
        } else if (part.compareTo("frontend") == 0) {
            return 1;
        } else { // '-'
            return 2;
        }
    }

    public static int careerToInt(String career) {
        if (career.compareTo("junior") == 0) {
            return 0;
        } else if (career.compareTo("senior") == 0) {
            return 1;
        } else { // '-'
            return 2;
        }
    }

    public static int foodToInt(String food) {
        if (food.compareTo("chicken") == 0) {
            return 0;
        } else if (food.compareTo("pizza") == 0) {
            return 1;
        } else { // '-'
            return 2;
        }
    }

    public static void main(String[] args) {
        String[] info = new String[]{
                "java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"
        };
        String[] query = new String[]{
                "java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"
        };

//       query = new String[]{
//               "python and frontend and senior and chicken 150"};
        int[] solution = solution(info, query);
        for (int i : solution) {
            System.out.printf("%d ", i);

        }
    }
}
