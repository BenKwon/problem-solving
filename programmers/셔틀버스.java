package programmers;
import java.util.*;
public class 셔틀버스 {
    static ArrayList<Integer> arriveTimes = new ArrayList<>();
    static int k;
    static int bus = 540; // 60 * 9
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        k = timetable.length; // 4
        for(int i = 0 ; i < k ; i++){
            String[] time = timetable[i].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);
            int convert = minute + (hour *  60);
            arriveTimes.add(convert);
        }
        Collections.sort(arriveTimes);
        int lastIdx = 0;
        for(int i = 0; i < n - 1; i++){
            if(i > 0) bus += t;
            int count =  0;
            for(int j = lastIdx; j < k; j++){
                if(count + 1 >  m)break;
                if(arriveTimes.get(j) <= bus){
                    count++;
                    lastIdx++;
                }
                else break;
            }
        }

        //마지막 버스를 타자
        if(n > 1) bus += t;
        int korn = bus;
        int count = 0;
        for(int j = lastIdx; j < k; j++){
            if(count + 1 > m) break;
            if(arriveTimes.get(j)<=bus){
                count++;
                lastIdx = j + 1;
            }
            else break;
        }
        // System.out.printf("count : %d, lastIdx:  %d\n",count,lastIdx);
        if(count == m){
            // System.out.println("@@");
            int lastTime = arriveTimes.get(lastIdx - 1);
            korn = lastTime - 1;
        }
        String ansHour = korn/60 +"";
        String ansMin = korn % 60 + "";
        if(ansHour.length() == 1) ansHour = 0 + ansHour;
        if(ansMin.length() == 1) ansMin = 0 + ansMin;
        answer = ansHour +":" + ansMin;
        System.out.println(korn);
        return answer;
    }
}
