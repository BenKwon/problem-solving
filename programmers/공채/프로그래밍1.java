package programmers.공채;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 신고를 받고 신고를 2번이상 받은 유저를 신고한 신고자들에게 알림을 보내는문제
 */
public class 프로그래밍1 {
    public static int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        HashMap<String, Integer> index = new HashMap<>();
        HashMap<String, Integer> reported_time = new HashMap<>();
        HashMap<String, ArrayList<String>> report_list = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            index.put(id_list[i], i);
            reported_time.put(id_list[i], 0);
            report_list.put(id_list[i], new ArrayList<>());
        }
        for (int i = 0; i < report.length; i++) {
            String info = report[i];
            StringTokenizer st = new StringTokenizer(info);
            String reporter = st.nextToken();
            String target = st.nextToken();
            if(report_list.get(target).contains(reporter)) continue;
            reported_time.put(target, reported_time.get(target) + 1);
            report_list.get(target).add(reporter);
        }

        Set<String> keys = reported_time.keySet();
        for (String key : keys) {
            if(reported_time.get(key) >= k){
                ArrayList<String> list_alert = report_list.get(key);
                for (String s : list_alert) {
                    int alert_idx = index.get(s);
                    answer[alert_idx] += 1;
                }
            }

        }
        for (int i : answer) {
            System.out.printf("%d ", i);

        }
        return answer;
    }
    public static void main(String[] args) {
        String[] a = {
                "con", "ryan"
        };
        String[] b ={
                "ryan con", "ryan con", "ryan con", "ryan con"
        };
        solution(a, b,3);
    }
}
