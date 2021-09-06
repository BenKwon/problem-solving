package programmers.카카오인턴십;

import java.util.*;

public class 메뉴리뉴얼 {
    static int n;
    static String target;
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<String, Integer> visit = new HashMap<>();

    public static void dfs(int level, int cur_index, String str) {
        if (level == n) {
            if (visit.get(str) == null) {
                if (map.get(str) == null) map.put(str, 1);
                else map.put(str, map.get(str) + 1);
                visit.put(str, 1);
            }
        }
        for (int i = cur_index + 1; i < target.length(); i++) {
            dfs(level + 1, i, str + target.charAt(i));
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            visit = new HashMap<>();
            for (int j = 0; j < course.length; j++) {
                n = course[j];
                target = order;
                for (int k = 0; k < target.length(); k++) {
                    dfs(1, k, "" + target.charAt(k));

                }
            }
        }
        ArrayList<String> sol = new ArrayList<>();
        for (int i = 0; i < course.length; i++) {
            Set<String> strings = map.keySet();
            Iterator<String> iterator = strings.iterator();
            int max = Integer.MIN_VALUE;
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (next.length() == course[i] && map.get(next) > 1)
                    max = Math.max(max, map.get(next));
            }
            iterator = strings.iterator();
            while (iterator.hasNext()) {
                String next = iterator.next();
                if (next.length() == course[i] && map.get(next) == max)
                    sol.add(next);
            }
        }
//        for (String s : sol) {
//            System.out.println(s);
//        }
        answer = sol.toArray(new String[sol.size()]);
        Arrays.sort(answer);
        return answer;
    }

    public static void main(String[] args) {
        String[] orders = new String[]{
                "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"
        };
        int[] courses = new int[]{2, 3, 4};
//        solution(orders, courses);

        orders = new String[]{
                "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"
        };
        courses = new int[]{2, 3, 5};

//        solution(orders, courses);
        orders = new String[]{
                "XYZ", "XWY", "WXA"
        };
        courses = new int[]{2, 3, 4};
        solution(orders, courses);

    }
}
