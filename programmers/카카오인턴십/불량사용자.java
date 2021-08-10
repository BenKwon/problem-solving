package programmers.카카오인턴십;

import java.util.ArrayList;

public class 불량사용자 {
    public static ArrayList<ArrayList<Integer>> candidate = new ArrayList<>();
    public static int[] visit;
    public static int n;
    static int sol = 0;
    static public int dfs(int ban_idx, int take) { // init : 1, take
        if (ban_idx == n ) {
            if (visit[take] == 0) {
                sol++;
                visit[take] = 1;
            }
            return 0;
        }
        ArrayList<Integer> candidates = candidate.get(ban_idx);
        for (int c = 0; c < candidates.size(); c++) {
            int idx = candidates.get(c);
            int idx_mask = (1 << idx);
            if ((idx_mask & take) != 0) {
                continue;
            } else {
                dfs(ban_idx + 1, take | idx_mask);
            }
        }
        return 0;
    }

    static public int solution(String[] user_id, String[] banned_id) {
        candidate = new ArrayList<>();
        n = banned_id.length;
        visit = new int[(1 << 8)];
        for (int i = 0; i < banned_id.length; i++) {
            String ban = banned_id[i];
            candidate.add(new ArrayList<>());
            for (int j = 0; j < user_id.length; j++) {
                String target = user_id[j];
                if (target.length() != ban.length()) continue;
                boolean same = true;
                for (int t = 0; t < target.length(); t++) {
                    if (ban.charAt(t) == '*') continue;
                    if (ban.charAt(t) != target.charAt(t)) {
                        same = false;
                        break;
                    }
                }
                if (same) {
                    candidate.get(i).add(j);
                }
            }
        }
        for (int i = 0; i < candidate.get(0).size(); i++) {
//            System.out.println("=========================");
            int idx = candidate.get(0).get(i);
//            System.out.println("idx = " + idx);
            int take = (1 << idx);
            dfs(1, take);
        }
//
//        for (int i = 0; i < candidate.size(); i++) {
//            System.out.println();
//            for (int j = 0; j < candidate.get(i).size(); j++) {
//                System.out.printf("%d ", candidate.get(i).get(j));
//            }
//        }
        int answer = sol;
        return answer;
    }

    public static void main(String[] args) {
        String[] user_id = new String[]{
                "frodo", "fradi", "crodo", "abc123", "frodoc"
        };
        String[] banned_id = new String[]{
                "fr*d*", "*rodo", "******", "******"
        };
//        System.out.println();
        System.out.println(solution(user_id, banned_id));
    }
}
