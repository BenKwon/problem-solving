package programmers.카카오인턴십;

import java.util.*;

/**
 * 2021 KAKAO BLIND RECRUITMENT
 *
 * 푼 방법
 * 사용 알고리즘 : 비트 마스킹, 해쉬맵, 완전 탐색
 *
 * 의견 : 프로그래머스 레벨 2였지만 까다로웠던 문제..
 * course배열에 들어있는 숫자 길이 별로 orders에 들어있는 각 문자열에서 모든 조합을 검색해봐야한다.
 * 처음에 단순 문자열 조합의 순서를 신경쓰지 않아서 그냥 dfs로 모든 조합을 조회한다음 Map에 최종 조합해서 나온 문자열과 그 갯수를 계속해서 업데이트 하였다.
 * 하지만 이렇게 할경우 AD와 DA가 서로다른 경우가 되기 때문에
 * 비트마스킹을 통해서 순서 상관없이 AD 와 DA가 같은 경우의 수로 처리되게 하였다.
 * 이 방법으로 할때
 *  HashMap<Integer, HashMap<Integer,Integer>> map  이러한 자료구조를 사용하였는데 KEY값인 첫 정수는 문자열의 길이고
 *  그다음 value값에 있는 또다른 해쉬맵의 키는 비트마스킹으로 만든 조합의 값 그리고 value는 그 조합의 갯수이다.
 *  처음에 HashMap<Integer, new int[(1<<26) -1]> 로 풀려했지만 그러면 메모리 초과가 난다.
 *  어쨋든 맞았긴 했지만 n이 커지면 메모리 초과가 그대로 날 것 같다.
 */
public class 메뉴리뉴얼 {
    static int n;
    static String target;
    static HashMap<Integer, HashMap<Integer,Integer>> map = new HashMap<>();
    static int[] visit;
//    static HashMap<String, Integer> visit = new HashMap<>();

    public static void dfs(int level, int cur_index, int comb) {
        if (level == n) {
            //방문한적이 없을때
            if(map.get(n).get(comb) == null){
                map.get(n).put(comb,0);
            }
            map.get(n).put(comb, map.get(n).get(comb) + 1);
            visit[comb] = 1;
//            }
        }
        for (int i = cur_index + 1; i < target.length(); i++) {
            int n_comb = (comb) | (1 << (target.charAt(i) - 'A'));
            dfs(level + 1, i, n_comb);
        }
    }

    public static String[] solution(String[] orders, int[] course) {
        String[] answer = {};
//        visit = new int[(1 << 26) -1];
        visit = new int[(1 << 26) - 1];
        for (int i = 0; i < orders.length; i++) {
            String order = orders[i];
            for (int j = 0; j < course.length; j++) {
                n = course[j];
                if (map.get(n) == null)
                    map.put(n, new HashMap<>());
                target = order;
                for (int k = 0; k < target.length(); k++) {
                    int comb = (1 << (target.charAt(k) - 'A'));
                    dfs(1, k, comb);

                }
            }
        }

        ArrayList<String> sol = new ArrayList<>();
        Set<Integer> key = map.keySet();
        for (Integer n : key) {
            HashMap<Integer, Integer> tmp = map.get(n);
            ArrayList<Integer> max_idx = new ArrayList<>();
            int max = Integer.MIN_VALUE;
            for (Integer value : tmp.values()) {
                max = Math.max(max, value);
            }
            if(max < 2) continue;
            for (Integer integer : tmp.keySet()) {
                if(tmp.get(integer) == max){
                    max_idx.add(integer);
                }
            }
            for (int m = 0; m < max_idx.size(); m++) {
                int value = max_idx.get(m);
                String s = "";
                for (int i = 0; i <= 25; i++) {
                    if((value & (1 << i)) != 0){
                        char next = (char) (i + 'A');
                        s += next;
                    }
                }
                sol.add(s);
            }
        }

        answer = sol.toArray(new String[sol.size()]);
        Arrays.sort(answer);
        for (String s : answer) {
            System.out.println(s);

        }
        return answer;
    }

    public static void main(String[] args) {
        String[] orders = new String[]{
                "ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"
        };
        int[] courses = new int[]{2, 3, 4};
//        solution(orders, courses);
        solution(orders, courses);

        orders = new String[]{
                "ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"
        };
        courses = new int[]{2, 3, 5};

//        solution(orders, courses);
        orders = new String[]{
                "XYZ", "XWY", "WXA"
        };
        courses = new int[]{2, 3, 4};


    }
}
